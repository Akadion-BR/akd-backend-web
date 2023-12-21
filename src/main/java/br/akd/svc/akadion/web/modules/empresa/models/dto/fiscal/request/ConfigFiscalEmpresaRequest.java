package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request;

import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.certificado.CertificadoDigitalRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfce.NfceConfigRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfe.NfeConfigRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfse.NfseConfigRequest;
import br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal.RegimeTributarioEnum;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConfigFiscalEmpresaRequest {
    @NotNull(message = "Campo discrimina impostos não informado")
    private Boolean discriminaImpostos = true;

    @NotNull(message = "Campo 'NFE habilitado' não informado")
    private Boolean habilitaNfe = false;

    @NotNull(message = "Campo 'NFCE habilitado' não informado")
    private Boolean habilitaNfce = false;

    @NotNull(message = "Campo 'NFE habilitado' não informado")
    private Boolean habilitaNfse = false;

    @NotNull(message = "Campo 'Envio de e-mails para o destinatário' não informado")
    private Boolean habilitaEnvioEmailDestinatario = false;

    @Size(message = "O CNPJ da contabilidade deve possuir {max} caracteres", max = 18, min = 18)
    @Pattern(regexp = "(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})",
            message = "O CNPJ da contabilidade informado é inválido")
    private String cnpjContabilidade;

    @Size(message = "A senha do certificado digital deve possuir no máximo {max} caracteres", max = 30)
    private String senhaCertificadoDigital;

    @NotNull(message = "O campo 'Regime tributário' não pode ser nulo")
    private RegimeTributarioEnum regimeTributario = RegimeTributarioEnum.SIMPLES_NACIONAL;

    @Valid
    private NfeConfigRequest nfeConfig;

    @Valid
    private NfceConfigRequest nfceConfig;

    @Valid
    private NfseConfigRequest nfseConfig;

    @Valid
    private CertificadoDigitalRequest certificadoDigital;
}
