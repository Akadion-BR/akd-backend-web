package br.akd.svc.akadion.web.modules.empresa.services.crud.impl;

import br.akd.svc.akadion.web.globals.exclusao.entity.ExclusaoEntity;
import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.cliente.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadion.web.modules.cliente.services.validator.ClienteSistemaValidationService;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.CriaEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import br.akd.svc.akadion.web.modules.empresa.repository.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadion.web.modules.empresa.services.crud.EmpresaService;
import br.akd.svc.akadion.web.modules.empresa.services.validator.EmpresaValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class EmpresaServiceImpl implements EmpresaService {

    // TODO ADICIONAR ANNOTATION OVERRIDE NOS MÉTODOS SOBRESCRITOS

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Autowired
    EmpresaValidationService empresaValidationService;

    @Autowired
    ClienteSistemaValidationService clienteSistemaValidationService;

    public CriaEmpresaResponse criaNovaEmpresa(UUID idClienteSistemaSessao,
                                               EmpresaRequest empresaRequest) {

        log.debug("Método de serviço responsável pelo tratamento do objeto recebido e criação de nova empresa acessado");
        log.debug("Empresa recebida: {} | Id do cliente: {}", empresaRequest, idClienteSistemaSessao);

        log.debug("Iniciando acesso ao método de implementação de busca de cliente por id...");
        ClienteSistemaEntity clienteSistema = clienteSistemaRepositoryImpl
                .implementaBuscaPorId(idClienteSistemaSessao);

        log.debug("Iniciando acesso ao método de validação de quantidade limite de empresas por cliente...");
        clienteSistemaValidationService.validaSeCadastroDeNovaEmpresaIraExcederLimiteDeEmpresasPorCliente(clienteSistema);

        log.debug("Iniciando acesso ao método de validação de variáveis de chave única para empresa...");
        empresaValidationService.validacaoDeChaveUnicaParaNovaEmpresa(empresaRequest);

        log.debug("Iniciando construção do objeto EmpresaEntity...");
        EmpresaEntity empresaEntity = new EmpresaEntity().buildFromRequest(clienteSistema, empresaRequest);
        log.debug("Construção de objeto EmpresaEntity realizado com sucesso");

        log.debug("Adicionando a empresa à lista de empresas do cliente...");
        clienteSistema.addEmpresa(empresaEntity);
        log.info("Empresa adicionada à lista de empresas do cliente com sucesso");

        log.debug("Iniciando acesso ao método de implementação de persistência do cliente sistêmico...");
        ClienteSistemaEntity clienteSistemaEntity =
                clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
        log.info("Persistência do cliente sistêmico realizada com sucesso");

        log.debug("Obtendo empresa criada da lista das empresas do cliente persistido...");
        EmpresaEntity empresaCriada = clienteSistemaEntity
                .obtemEmpresaPorRazaoSocial(empresaRequest.getRazaoSocial());
        log.info("Empresa persistida obtida com sucesso");

//        log.debug("Iniciando acesso ao método de criação de novo colaborador admin para empresa...");
//        ColaboradorEntity colaborador = criaColaboradorAdminParaNovaEmpresa(empresaCriada);     //TODO AJUSTAR - INTEGRAÇÃO DE MS
//        log.info("Colaborador criado com sucesso para nova empresa");

//        log.info("Iniciando objeto do tipo CriaEmpresaResponse..."); //TODO AJUSTAR - INTEGRAÇÃO DE MS
//        CriaEmpresaResponse criaEmpresaResponse = new CriaEmpresaResponse(
//                clienteSistemaEntity.getId(),
//                new ColaboradorResponse().buildFromEntity(colaborador),
//                new EmpresaResponse().buildFromEntity(empresaCriada));
//        log.info("Objeto criado com sucesso");
//
//        log.info("Método responsável por realizar a criação da empresa executado com sucesso. Retornando response...");
//        return criaEmpresaResponse;
        return null;
    }

    public EmpresaResponse atualizaEmpresa(UUID idClienteSistemaSessao,
                                           UUID uuidEmpresa,
                                           EmpresaRequest empresaRequest) {

        log.debug("Método de serviço de atualização de empresa acessado");

        log.debug("Iniciando acesso ao método de implementação de busca de empresa por id...");
        EmpresaEntity empresaPreAtualizacao = empresaRepositoryImpl
                .implementaBuscaPorId(new EmpresaId(idClienteSistemaSessao, uuidEmpresa));

        log.debug("Iniciando validação se empresa a ser alterada foi deletada anteriormente...");
        empresaValidationService.validaSeEmpresaJaEstaExcluida(empresaPreAtualizacao,
                "A empresa selecionada não pode ser alterada, pois foi excluída");

        log.debug("Iniciando acesso ao método de validação de chaves únicas para a atualização da empresa...");
        empresaValidationService.validacaoDeChaveUnicaParaAtualizacaoDeEmpresa(empresaRequest, empresaPreAtualizacao);

        log.debug("Iniciando setagem de atributos atualizados da empresa...");
        EmpresaEntity empresaAtualizada = new EmpresaEntity()
                .updateFromRequest(empresaPreAtualizacao, empresaRequest);
        log.debug("Setagem de atributos finalizada com sucesso");

        log.debug("Iniciando acesso ao método de implementação de persistência de empresa...");
        EmpresaEntity empresaPersistida = empresaRepositoryImpl.implementaPersistencia(empresaAtualizada);
        log.info("Empresa atualizada com sucesso");

        log.info("Iniciando conversão da empresa persistida para objeto do tipo response...");
        EmpresaResponse empresaResponse = new EmpresaResponse().buildFromEntity(empresaPersistida);
        log.info("Conversão para objeto do tipo EmpresaResponse realizada com sucesso. Retornando objeto...");
        return empresaResponse;
    }

    public EmpresaResponse removeEmpresa(UUID idClienteSistemaSessao,
                                         UUID uuidEmpresa) {

        log.debug("Método de serviço de remoção de empresa acessado");

        log.debug("Iniciando acesso ao método de implementação de busca de empresa por id...");
        EmpresaEntity empresaEncontrada = empresaRepositoryImpl
                .implementaBuscaPorId(new EmpresaId(idClienteSistemaSessao, uuidEmpresa));

        log.debug("Iniciando acesso ao método de validação de exclusão de empresa que já foi excluída...");
        empresaValidationService.validaSeEmpresaJaEstaExcluida(
                empresaEncontrada, "A empresa selecionada já foi excluída");

        log.debug("Atualizando objeto Exclusao da empresa com dados referentes à sua exclusão...");
        ExclusaoEntity exclusaoEntity = new ExclusaoEntity().constroiObjetoExclusao();

        empresaEncontrada.setExclusao(exclusaoEntity);
        log.debug("Objeto Exclusao da empresa de id {} setado com sucesso", uuidEmpresa);

        log.debug("Persistindo empresa excluída no banco de dados...");
        EmpresaEntity empresaExcluida = empresaRepositoryImpl.implementaPersistencia(empresaEncontrada);
        log.info("Remoção da empresa realizada com sucesso");

        log.info("Iniciando conversão da empresa excluída para objeto do tipo EmpresaResponse...");
        EmpresaResponse empresaResponse = new EmpresaResponse().buildFromEntity(empresaExcluida);

        log.info("Objeto EmpresaResponse criado com sucesso. Retornando objeto...");
        return empresaResponse;
    }
}
