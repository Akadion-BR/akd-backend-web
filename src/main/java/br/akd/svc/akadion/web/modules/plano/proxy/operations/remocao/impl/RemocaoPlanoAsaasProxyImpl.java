package br.akd.svc.akadion.web.modules.plano.proxy.operations.remocao.impl;

import br.akd.svc.akadion.web.exceptions.custom.InternalErrorException;
import br.akd.svc.akadion.web.globals.proxy.AsaasProxyUtils;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyModuleEnum;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyOperationEnum;
import br.akd.svc.akadion.web.modules.plano.proxy.PlanoAsaasProxy;
import br.akd.svc.akadion.web.modules.plano.proxy.operations.remocao.response.CancelamentoAssinaturaResponse;
import br.akd.svc.akadion.web.utils.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RemocaoPlanoAsaasProxyImpl {

    @Autowired
    PlanoAsaasProxy planoAsaasProxy;

    @Autowired
    AsaasProxyUtils proxyUtils;

    public void realizaCancelamentoDePlanoDeAssinaturaNaIntegradoraAsaas(String asaasId) throws JsonProcessingException {

        log.debug("Método de serviço responsável pela cancelamento de assinatura na integradora ASAAS acessado");
        try {
            log.info("Realizando envio de requisição de cancelamento de assinatura para a integradora ASAAS...");
            ResponseEntity<CancelamentoAssinaturaResponse> responseAsaas = planoAsaasProxy.cancelarAssinatura(
                    asaasId, System.getenv(Constantes.TOKEN_ASAAS));

            log.info("Realizando validações referente à resposta da integradora...");
            proxyUtils.realizaValidacaoResponseAsaas(
                    responseAsaas, ProxyModuleEnum.PLANO, ProxyOperationEnum.REMOCAO);
            log.info("Validações realizadas com sucesso. Plano cancelado no ASAAS com sucesso.");
        } catch (FeignException feignException) {
            log.error("Ocorreu um erro durante a integração com o ASAAS para cancelamento de assinatura: {}",
                    feignException.getMessage());

            log.info("Iniciando acesso ao método responsável por realizar o tratamento do erro retornado pelo " +
                    "feign client...");
            throw proxyUtils.realizaTratamentoRetornoErroFeignException(
                    feignException, ProxyModuleEnum.PLANO, ProxyOperationEnum.REMOCAO);
        } catch (Exception e) {
            log.error("Ocorreu um erro interno durante a remoção do plano na integradora de " +
                    "pagamentos ASAAS: {}", e.getMessage());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }

    }
}
