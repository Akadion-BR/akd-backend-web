package br.akd.svc.akadion.web.modules.empresa.services.crud.impl;

import br.akd.svc.akadion.web.exceptions.custom.InternalErrorException;
import br.akd.svc.akadion.web.exceptions.custom.InvalidRequestException;
import br.akd.svc.akadion.web.globals.cpfcnpj.models.CnpjRequest;
import br.akd.svc.akadion.web.globals.cpfcnpj.service.CnpjService;
import br.akd.svc.akadion.web.globals.exclusao.entity.ExclusaoEntity;
import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.cliente.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadion.web.modules.cliente.services.validator.ClienteSistemaValidationService;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.CriaEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.page.EmpresaPageResponse;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.certificado.CertificadoDigitalEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.impl.CriacaoEmpresaFocusProxyImpl;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.response.CriaEmpresaFocusResponse;
import br.akd.svc.akadion.web.modules.empresa.repository.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadion.web.modules.empresa.services.crud.EmpresaService;
import br.akd.svc.akadion.web.modules.empresa.services.validator.EmpresaValidationService;
import br.akd.svc.akadion.web.modules.external.erp.colaborador.CriacaoColaboradorResponse;
import br.akd.svc.akadion.web.modules.external.erp.colaborador.proxy.impl.ColaboradorProxyImpl;
import br.akd.svc.akadion.web.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    EmpresaValidationService empresaValidationService;

    @Autowired
    ClienteSistemaValidationService clienteSistemaValidationService;

    @Autowired
    CnpjService cnpjService;

    @Autowired
    ColaboradorProxyImpl colaboradorProxy;

    @Autowired
    CriacaoEmpresaFocusProxyImpl criacaoEmpresaFocusProxy;

    @Override
    @Transactional
    public CriaEmpresaResponse criaNovaEmpresa(UUID idClienteSistemaSessao,
                                               EmpresaRequest empresaRequest) {

        log.info("Método de serviço responsável pelo tratamento do objeto recebido e criação de nova empresa acessado");
        log.debug("EmpresaRequest recebido: {} | Id do cliente sistêmico: {}", empresaRequest, idClienteSistemaSessao);

        log.info("Iniciando acesso ao método de implementação de busca de cliente por id...");
        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl
                .implementaBuscaPorId(idClienteSistemaSessao);

        log.info("Iniciando acesso ao método de validação de quantidade limite de empresas por cliente...");
        clienteSistemaValidationService.validaSeCadastroDeNovaEmpresaIraExcederLimiteDeEmpresasPorCliente(clienteSistema);

        log.info("Iniciando acesso ao método de validação de variáveis de chave única para empresa...");
        empresaValidationService.validacaoDeChaveUnicaParaNovaEmpresa(empresaRequest);

        CriaEmpresaFocusResponse criaEmpresaFocusResponse = criacaoEmpresaFocusProxy
                .realizaCriacaoDeEmpresaNaIntegradoraFocusNfe(true, empresaRequest);

        CertificadoDigitalEntity certificadoDigitalEntity = empresaRequest.getConfigFiscal().getCertificadoDigital() != null
                ? new CertificadoDigitalEntity().buildFromFocusResponse(empresaRequest, criaEmpresaFocusResponse)
                : null;

        log.info("Iniciando construção do objeto EmpresaEntity...");
        EmpresaEntity empresaEntity = new EmpresaEntity().buildFromRequest(
                clienteSistema, empresaRequest, certificadoDigitalEntity);
        log.info("Construção de objeto EmpresaEntity realizado com sucesso");

        log.info("Adicionando a empresa à lista de empresas do cliente...");
        clienteSistema.addEmpresa(empresaEntity);
        log.info("Empresa adicionada à lista de empresas do cliente com sucesso");

        log.info("Iniciando acesso ao método de implementação de persistência do cliente sistêmico...");
        ClienteSistemaEntity clienteSistemaEntity =
                clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
        log.info("Persistência do cliente sistêmico realizada com sucesso");

        log.info("Obtendo empresa criada da lista das empresas do cliente persistido...");
        EmpresaEntity empresaCriada = clienteSistemaEntity
                .obtemEmpresaPorRazaoSocial(empresaRequest.getRazaoSocial());
        log.info("Empresa persistida obtida com sucesso");

        log.info("Iniciando acesso ao método de criação de novo colaborador admin para empresa...");
        CriacaoColaboradorResponse criacaoColaboradorResponse =
                colaboradorProxy.realizaCriacaoDeColaboradorNoErp(new EmpresaId(empresaCriada.getClienteSistema().getId(), empresaCriada.getId()));
        log.info("Colaborador criado com sucesso para nova empresa");

        log.info("Iniciando objeto do tipo CriaEmpresaResponse...");
        CriaEmpresaResponse criaEmpresaResponse = new CriaEmpresaResponse(
                clienteSistemaEntity.getId(),
                criacaoColaboradorResponse,
                new EmpresaResponse().buildFromEntity(empresaCriada));
        log.info("Objeto criado com sucesso");

        log.info("Método responsável por realizar a criação da empresa executado com sucesso. Retornando response...");
        return criaEmpresaResponse;
    }

    @Override
    public EmpresaPageResponse obtemEmpresasClienteSistemico(Pageable pageable,
                                                             UUID idClienteSistemaSessao,
                                                             String campoBusca,
                                                             Boolean somenteEmpresasAtivas) {
        log.info("Método de serviço de obtenção paginada de empresas acessado");

        log.info("Acessando repositório de busca de empresas...");
        Page<EmpresaEntity> empresasPage = empresaRepositoryImpl.implementaBuscaPaginadaPorEmpresas(
                pageable, idClienteSistemaSessao, campoBusca, somenteEmpresasAtivas);

        log.info("Busca de empresas por paginação realizada com sucesso. Acessando método de conversão dos objetos " +
                "do tipo Entity para objetos do tipo Response...");
        EmpresaPageResponse empresaPageResponse = new EmpresaPageResponse().buildPageResponse(empresasPage);
        log.info("Conversão de tipagem realizada com sucesso");

        log.info("A busca paginada de empresas foi realizada com sucesso");
        return empresaPageResponse;
    }

    @Override
    public void realizaValidacaoCnpj(CnpjRequest cnpjRequest) {
        log.info("Método responsável por implementar a lógica de validação de CNPJ acessado");

        try {
            log.info("Iniciando validação dos dígitos verificadores do CNPJ...");
            cnpjService.realizaValidacaoCnpj(cnpjRequest.getCnpj());
            log.info("Validação dos dígitos verificadores do CNPJ realizada com sucesso");

            log.info("Iniciando acesso ao método de validação se CNPJ já existe...");
            empresaValidationService.validaSeCnpjJaExiste(cnpjRequest.getCnpj());
            log.info("Validação de duplicidade de CNPJ realizada com sucesso");
        } catch (InvalidRequestException invalidRequestException) {
            throw new InvalidRequestException(invalidRequestException.getMessage());
        } catch (Exception exception) {
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

    @Override
    @Transactional
    public EmpresaResponse atualizaEmpresa(UUID idClienteSistemaSessao,
                                           UUID uuidEmpresa,
                                           EmpresaRequest empresaRequest) {

        log.info("Método de serviço de atualização de empresa acessado");

        log.info("Iniciando acesso ao método de implementação de busca de empresa por id...");
        EmpresaEntity empresaPreAtualizacao = empresaRepositoryImpl
                .implementaBuscaPorId(new EmpresaId(idClienteSistemaSessao, uuidEmpresa));

        log.info("Iniciando validação se empresa a ser alterada foi deletada anteriormente...");
        empresaValidationService.validaSeEmpresaJaEstaExcluida(empresaPreAtualizacao,
                "A empresa selecionada não pode ser alterada, pois foi excluída");

        log.info("Iniciando acesso ao método de validação de chaves únicas para a atualização da empresa...");
        empresaValidationService.validacaoDeChaveUnicaParaAtualizacaoDeEmpresa(empresaRequest, empresaPreAtualizacao);

        log.info("Iniciando setagem de atributos atualizados da empresa...");
        EmpresaEntity empresaAtualizada = new EmpresaEntity()
                .updateFromRequest(empresaPreAtualizacao, empresaRequest);
        log.info("Setagem de atributos finalizada com sucesso");

        log.info("Iniciando acesso ao método de implementação de persistência de empresa...");
        EmpresaEntity empresaPersistida = empresaRepositoryImpl.implementaPersistencia(empresaAtualizada);
        log.info("Empresa atualizada com sucesso");

        log.info("Iniciando conversão da empresa persistida para objeto do tipo response...");
        EmpresaResponse empresaResponse = new EmpresaResponse().buildFromEntity(empresaPersistida);
        log.info("Conversão para objeto do tipo EmpresaResponse realizada com sucesso. Retornando objeto...");
        return empresaResponse;
    }

    @Override
    @Transactional
    public EmpresaResponse removeEmpresa(UUID idClienteSistemaSessao,
                                         UUID uuidEmpresa) {

        log.info("Método de serviço de remoção de empresa acessado");

        log.info("Iniciando acesso ao método de implementação de busca de empresa por id...");
        EmpresaEntity empresaEncontrada = empresaRepositoryImpl
                .implementaBuscaPorId(new EmpresaId(idClienteSistemaSessao, uuidEmpresa));

        log.info("Iniciando acesso ao método de validação de exclusão de empresa que já foi excluída...");
        empresaValidationService.validaSeEmpresaJaEstaExcluida(
                empresaEncontrada, "A empresa selecionada já foi excluída");

        log.info("Atualizando objeto Exclusao da empresa com dados referentes à sua exclusão...");
        ExclusaoEntity exclusaoEntity = new ExclusaoEntity().constroiObjetoExclusao();

        empresaEncontrada.setExclusao(exclusaoEntity);
        log.info("Objeto Exclusao da empresa de id {} setado com sucesso", uuidEmpresa);

        log.info("Persistindo empresa excluída no banco de dados...");
        EmpresaEntity empresaExcluida = empresaRepositoryImpl.implementaPersistencia(empresaEncontrada);
        log.info("Remoção da empresa realizada com sucesso");

        log.info("Iniciando conversão da empresa excluída para objeto do tipo EmpresaResponse...");
        EmpresaResponse empresaResponse = new EmpresaResponse().buildFromEntity(empresaExcluida);

        log.info("Objeto EmpresaResponse criado com sucesso. Retornando objeto...");
        return empresaResponse;
    }
}
