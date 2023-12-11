package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfse;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfseConfigRequest {
    //TODO JAVAX VALIDATOR
    private Long proximoNumeroProducao;
    private Long proximoNumeroHomologacao;
    private Integer serieProducao;
    private Integer serieHomologacao;
}
