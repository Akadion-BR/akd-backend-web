package br.akd.svc.akadion.web.modules.plano.proxy;

import br.akd.svc.akadion.web.modules.plano.proxy.operations.atualizacao.models.request.AtualizaAssinaturaAsaasRequest;
import br.akd.svc.akadion.web.modules.plano.proxy.operations.atualizacao.models.response.AtualizaAssinaturaAsaasResponse;
import br.akd.svc.akadion.web.modules.plano.proxy.operations.criacao.models.request.CriaPlanoAsaasRequest;
import br.akd.svc.akadion.web.modules.plano.proxy.operations.criacao.models.response.CriaPlanoAsaasResponse;
import br.akd.svc.akadion.web.modules.plano.proxy.operations.remocao.response.CancelamentoAssinaturaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ASAAS-PLANO", url = "${URL_ASAAS}")
public interface PlanoAsaasProxy {
    @PostMapping(value = "/subscriptions")
    ResponseEntity<CriaPlanoAsaasResponse> cadastraNovaAssinatura(@RequestBody CriaPlanoAsaasRequest criaPlanoAsaasRequest,
                                                                  @RequestHeader(value = "access_token") String accessToken);

    @PostMapping(value = "/subscriptions/{idAssinatura}")
    ResponseEntity<AtualizaAssinaturaAsaasResponse> atualizaAssinatura(@PathVariable String idAssinatura,
                                                                       @RequestBody AtualizaAssinaturaAsaasRequest atualizaAssinaturaAsaasRequest,
                                                                       @RequestHeader(value = "access_token") String accessToken);

    @DeleteMapping(value = "/subscriptions/{idAssinatura}")
    ResponseEntity<CancelamentoAssinaturaResponse> cancelarAssinatura(@PathVariable(value = "idAssinatura") String idAssinatura,
                                                                      @RequestHeader(value = "access_token") String accessToken);
}
