package br.akd.svc.akadion.web.modules.external.backoffice.chamado.avaliacao.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequest {
    private Integer nota;
    private String descricao;
}
