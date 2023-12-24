package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfce;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfceConfigRequest {

    @Min(message = "O valor mínimo para o próximo número da NFCe é {min}", value = 1)
    @Max(message = "O valor máximo para o próximo número da NFCe é {max}", value = 9999999)
    private Long proximoNumeroProducao;

    @Min(message = "O valor mínimo para o número de série da NFCe é {min}", value = 1)
    @Max(message = "O valor máximo para o número de série da NFCe é {max}", value = 999)
    private Integer serieProducao;

    @NotEmpty(message = "O CSC não pode ser nulo para emissão de NFCe")
    private String cscProducao;

    @NotNull(message = "O ID Token não pode ser nulo para emissão de NFCe")
    @Min(message = "O valor mínimo para o token id do NFCe é {min}", value = 1)
    @Max(message = "O id token do NFCe deve conter no máximo {max} caracteres", value = 999999999999999L)
    private Long idTokenProducao;
}
