package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.response;

import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.response.nfce.NfceConfigResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.response.nfe.NfeConfigResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.response.nfse.NfseConfigResponse;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.ConfigFiscalEmpresaEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfigFiscalEmpresaResponse {
    private Boolean discriminaImpostos;
    private Boolean habilitaNfe;
    private Boolean habilitaNfce;
    private Boolean habilitaNfse;
    private Boolean habilitaEnvioEmailDestinatario;
    private String cnpjContabilidade;
    private String regimeTributarioEnum;
    private NfeConfigResponse nfeConfig;
    private NfceConfigResponse nfceConfig;
    private NfseConfigResponse nfseConfig;

    public ConfigFiscalEmpresaResponse buildFromEntity(ConfigFiscalEmpresaEntity configFiscalEmpresaEntity) {
        return configFiscalEmpresaEntity != null
                ? ConfigFiscalEmpresaResponse.builder()
                .discriminaImpostos(configFiscalEmpresaEntity.getDiscriminaImpostos())
                .habilitaNfe(configFiscalEmpresaEntity.getHabilitaNfe())
                .habilitaNfce(configFiscalEmpresaEntity.getHabilitaNfce())
                .habilitaNfse(configFiscalEmpresaEntity.getHabilitaNfse())
                .habilitaEnvioEmailDestinatario(configFiscalEmpresaEntity.getHabilitaEnvioEmailDestinatario())
                .cnpjContabilidade(configFiscalEmpresaEntity.getCnpjContabilidade())
                .regimeTributarioEnum(configFiscalEmpresaEntity.getRegimeTributarioEnum().getDesc())
                .nfeConfig(new NfeConfigResponse().buildFromEntity(configFiscalEmpresaEntity.getNfeConfig()))
                .nfceConfig(new NfceConfigResponse().buildFromEntity(configFiscalEmpresaEntity.getNfceConfig()))
                .nfseConfig(new NfseConfigResponse().buildFromEntity(configFiscalEmpresaEntity.getNfseConfig()))
                .build()
                : null;
    }
}
