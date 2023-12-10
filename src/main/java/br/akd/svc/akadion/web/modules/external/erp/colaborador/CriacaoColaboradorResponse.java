package br.akd.svc.akadion.web.modules.external.erp.colaborador;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CriacaoColaboradorResponse {
    private String matricula;
    private String senhaAcesso;
}
