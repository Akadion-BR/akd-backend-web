package br.akd.svc.akadion.web.modules.empresa.proxy;

import br.akd.svc.akadion.web.modules.empresa.proxy.configuration.FocusProxyConfiguration;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.request.CriaEmpresaFocusRequest;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.response.CriaEmpresaFocusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "FOCUS-EMPRESA", url = "https://api.focusnfe.com.br", configuration = FocusProxyConfiguration.class)
public interface EmpresaProxy {
    @PostMapping(value = "/v2/empresas")
    ResponseEntity<CriaEmpresaFocusResponse> cadastraNovaEmpresa(@RequestParam(value = "dry_run", required = false) Integer dryRun,
                                                                 @RequestBody CriaEmpresaFocusRequest criaEmpresaFocusRequest);
}