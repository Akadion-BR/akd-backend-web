package br.akd.svc.akadion.web.modules.external.erp.colaborador.proxy;

import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.external.erp.colaborador.CriacaoColaboradorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ERP-COLABORADOR", url = "${URL_MS_ERP}")
public interface ColaboradorProxy {
    @PostMapping(value = "/api/sistema/v1/colaborador/cria-colaborador-raiz")
    ResponseEntity<CriacaoColaboradorResponse> solicitaCriacaoDeColaboradorRaizParaNovaEmpresa(
            @RequestBody EmpresaEntity empresaEntity);
}
