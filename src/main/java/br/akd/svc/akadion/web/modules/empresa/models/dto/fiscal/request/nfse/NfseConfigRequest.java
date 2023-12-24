package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfse;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfseConfigRequest {

    @Min(message = "O valor mínimo para o próximo número da NFSe é {min}", value = 1)
    @Max(message = "O valor máximo para o próximo número da NFSe é {max}", value = 9999999)
    private Long proximoNumeroProducao;

    @Min(message = "O valor mínimo para o número de série da NFSe é {min}", value = 1)
    @Max(message = "O valor máximo para o número de série da NFSe é {max}", value = 999)
    private Integer serieProducao;
}
