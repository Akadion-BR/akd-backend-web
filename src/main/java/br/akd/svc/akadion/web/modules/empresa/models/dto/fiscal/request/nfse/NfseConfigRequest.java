package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfse;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfseConfigRequest {
    private Long proximoNumeroProducao;
    private Integer serieProducao;
}
