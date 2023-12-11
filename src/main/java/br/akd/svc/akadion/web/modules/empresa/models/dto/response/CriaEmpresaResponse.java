package br.akd.svc.akadion.web.modules.empresa.models.dto.response;

import br.akd.svc.akadion.web.modules.external.erp.colaborador.CriacaoColaboradorResponse;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CriaEmpresaResponse {
    private UUID uuidClienteResponsavelEmpresa;
    private CriacaoColaboradorResponse colaboradorCriado;
    private EmpresaResponse empresaCriada;
}
