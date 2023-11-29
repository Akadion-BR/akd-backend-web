package br.akd.svc.akadion.web.modules.empresa.models.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CriaEmpresaResponse {
    private UUID uuidClienteResponsavelEmpresa;
    //    private ColaboradorResponse colaboradorCriado;     //TODO AJUSTAR - INTEGRAÇÃO DE MS
    private EmpresaResponse empresaCriada;
}
