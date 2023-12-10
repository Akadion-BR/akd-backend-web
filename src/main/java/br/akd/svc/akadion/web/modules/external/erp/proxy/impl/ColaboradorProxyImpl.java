package br.akd.svc.akadion.web.modules.external.erp.proxy.impl;

import br.akd.svc.akadion.web.exceptions.InternalErrorException;
import br.akd.svc.akadion.web.globals.proxy.ProxyUtils;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyModuleEnum;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyOperationEnum;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.external.erp.colaborador.CriacaoColaboradorResponse;
import br.akd.svc.akadion.web.modules.external.erp.proxy.ColaboradorProxy;
import br.akd.svc.akadion.web.utils.Constantes;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ColaboradorProxyImpl {

    @Autowired
    ColaboradorProxy colaboradorProxy;

    @Autowired
    ProxyUtils proxyUtils;

    public CriacaoColaboradorResponse realizaCriacaoDeColaboradorNoErp(EmpresaEntity empresa) {

        log.info("Método de serviço responsável pela integração com o ERP para solicitar a criação do colaborador " +
                "raiz da empresa acessado");
        try {
            log.info("Realizando envio de requisição de criação de colaborador raiz no ERP...");
            ResponseEntity<CriacaoColaboradorResponse> responseAsaas =
                    colaboradorProxy.solicitaCriacaoDeColaboradorRaizParaNovaEmpresa(empresa);

            log.info("Realizando validações referente à resposta do ERP...");
            proxyUtils.realizaValidacaoResponseErp(
                    responseAsaas, ProxyModuleEnum.COLABORADOR_RAIZ, ProxyOperationEnum.CRIACAO);
            log.info("Validações realizadas com sucesso. Colaborador raiz criado na empresa com sucesso.");

            log.info("Retornando body da requisição...");
            return responseAsaas.getBody();
        } catch (FeignException feignException) {
            log.error("Ocorreu um erro durante a integração com o ERP para criação de colaborador raiz: {}",
                    feignException.getMessage());

            log.info("Iniciando acesso ao método responsável por realizar o tratamento do erro retornado pelo " +
                    "feign client...");
            throw proxyUtils.realizaTratamentoRetornoErroFeignException(
                    feignException, ProxyModuleEnum.COLABORADOR_RAIZ, ProxyOperationEnum.CRIACAO);
        } catch (Exception e) {
            log.error("Ocorreu um erro interno durante a criação do colaborador raiz no ERP: {}", e.getMessage());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }

    }
}
