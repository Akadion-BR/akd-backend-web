package br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.impl;

import br.akd.svc.akadion.web.exceptions.InternalErrorException;
import br.akd.svc.akadion.web.globals.proxy.ProxyUtils;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyModuleEnum;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyOperationEnum;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.proxy.EmpresaProxy;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.request.CriaEmpresaFocusRequest;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.response.CriaEmpresaFocusResponse;
import br.akd.svc.akadion.web.utils.Constantes;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CriacaoEmpresaFocusProxyImpl {

    @Autowired
    EmpresaProxy empresaProxy;

    @Autowired
    ProxyUtils proxyUtils;

    public CriaEmpresaFocusResponse realizaCriacaoDeEmpresaNaIntegradoraFocusNfe(Boolean homologacao,
                                                                                 EmpresaRequest empresaRequest) {

        log.info("Método de serviço responsável pela criação de empresa na integradora Focus NFE acessado");

        log.info("Iniciando construção do objeto CriaEmpresaFocusRequest...");
        CriaEmpresaFocusRequest criaEmpresaFocusRequest =
                new CriaEmpresaFocusRequest().buildFromEmpresaRequest(empresaRequest);

        try {
            log.info("Realizando envio de requisição de criação de empresa para a integradora FOCUS NFE...");
            ResponseEntity<CriaEmpresaFocusResponse> responseFocus = empresaProxy.cadastraNovaEmpresa(
                    Boolean.TRUE.equals(homologacao) ? 1 : null,
                    criaEmpresaFocusRequest);

            log.info("Realizando validações referente à resposta da integradora...");
            proxyUtils.realizaValidacaoResponseFocusNfe(
                    responseFocus, ProxyModuleEnum.EMPRESA, ProxyOperationEnum.CRIACAO);
            log.info("Validações realizadas com sucesso. Plano criado no ASAAS com sucesso.");

            log.info("Retornando id da assinatura gerado");
            return Objects.requireNonNull(responseFocus.getBody());
        } catch (FeignException feignException) {
            log.error("Ocorreu um erro durante a integração com o FOCUS NFE para criação de empresa: {} | {} | {}",
                    feignException.getMessage(), feignException.getCause(), feignException.getStackTrace());

            log.info("Iniciando acesso ao método responsável por realizar o tratamento do erro retornado pelo " +
                    "feign client...");
            throw proxyUtils.realizaTratamentoRetornoErroFeignException(
                    feignException, ProxyModuleEnum.EMPRESA, ProxyOperationEnum.CRIACAO);
        } catch (Exception e) {
            log.error("Ocorreu um erro interno durante a criação do plano na integradora Focus NFE: {}", e.getMessage());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

}
