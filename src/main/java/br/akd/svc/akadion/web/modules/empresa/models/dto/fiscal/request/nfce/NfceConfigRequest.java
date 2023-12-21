package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfce;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfceConfigRequest {
    private Long proximoNumeroProducao;
    private Integer serieProducao;
    private String cscProducao;
    private Long idTokenProducao;
}
