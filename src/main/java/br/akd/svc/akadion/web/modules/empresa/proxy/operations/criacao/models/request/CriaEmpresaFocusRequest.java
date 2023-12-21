package br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.request;

import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CriaEmpresaFocusRequest {

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "nome_fantasia")
    private String nomeFantasia;

    @JsonProperty(value = "inscricao_estadual")
    private String inscricaoEstadual;

    @JsonProperty(value = "inscricao_municipal")
    private String inscricaoMunicipal;

    @JsonProperty(value = "cnpj")
    private String cnpj;

    @JsonProperty(value = "regime_tributario")
    private String regimeTributario;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "logradouro")
    private String logradouro;

    @JsonProperty(value = "numero")
    private String numero;

    @JsonProperty(value = "complemento")
    private String complemento;

    @JsonProperty(value = "bairro")
    private String bairro;

    @JsonProperty(value = "cep")
    private String cep;

    @JsonProperty(value = "municipio")
    private String municipio;

    @JsonProperty(value = "uf")
    private String uf;

    @JsonProperty(value = "enviar_email_destinatario")
    private Boolean enviarEmailDestinatario;

    @JsonProperty(value = "discrimina_impostos")
    private Boolean discriminaImpostos;

    @JsonProperty(value = "cpf_cnpj_contabilidade")
    private String cnpjContabilidade;

    @JsonProperty(value = "habilita_nfe")
    private Boolean habilitaNfe;

    @JsonProperty(value = "habilita_nfce")
    private Boolean habilitaNfce;

    @JsonProperty(value = "habilita_nfse")
    private Boolean habilitaNfse;

    //TODO REINSERIR
//    @JsonProperty(value = "orientacao_danfe")
//    private OrientacaoDanfeEnum orientacaoDanfe;

    @JsonProperty(value = "recibo_danfe")
    private Boolean reciboDanfe;

    @JsonProperty(value = "exibe_sempre_ipi_danfe")
    private Boolean exibeSempreIpiDanfe;

    @JsonProperty(value = "exibe_issqn_danfe")
    private Boolean exibeIssqnDanfe;

    @JsonProperty(value = "exibe_impostos_adicionais_danfe")
    private Boolean exibeImpostosAdicionaisDanfe;

    @JsonProperty(value = "exibe_sempre_volumes_danfe")
    private Boolean exibeSempreVolumesDanfe;

    @JsonProperty(value = "enviar_email_homologacao")
    private Boolean enviarEmailHomologacao;

    @JsonProperty(value = "csc_nfce_producao")
    private String cscNfceProducao;

    @JsonProperty(value = "id_token_nfce_producao")
    private String idTokenNfceProducao;

    @JsonProperty(value = "csc_nfce_homologacao")
    private String cscNfceHomologacao;

    @JsonProperty(value = "id_token_nfce_homologacao")
    private String idTokenNfceHomologacao;

    @JsonProperty(value = "proximo_numero_nfe_producao")
    private Long proximoNumeroNfeProducao;

    @JsonProperty(value = "proximo_numero_nfe_homologacao")
    private Long proximoNumeroNfeHomologacao;

    @JsonProperty(value = "serie_nfe_producao")
    private String serieNfeProducao;

    @JsonProperty(value = "serie_nfe_homologacao")
    private String serieNfeHomologacao;

    @JsonProperty(value = "proximo_numero_nfce_producao")
    private Long proximoNumeroNfceProducao;

    @JsonProperty(value = "proximo_numero_nfce_homologacao")
    private Long proximoNumeroNfceHomologacao;

    @JsonProperty(value = "serie_nfce_producao")
    private String serieNfceProducao;

    @JsonProperty(value = "serie_nfce_homologacao")
    private String serieNfceHomologacao;

    @JsonProperty(value = "proximo_numero_nfse_producao")
    private Long proximoNumeroNfseProducao;

    @JsonProperty(value = "proximo_numero_nfse_homologacao")
    private Long proximoNumeroNfseHomologacao;

    @JsonProperty(value = "serie_nfse_producao")
    private String serieNfseProducao;

    @JsonProperty(value = "serie_nfse_homologacao")
    private String serieNfseHomologacao;

    @JsonProperty(value = "arquivo_certificado_base64")
    private String arquivoCertificadoBase64;

    @JsonProperty(value = "senha_certificado")
    private String senhaCertificado;

    public CriaEmpresaFocusRequest buildFromEmpresaRequest(EmpresaRequest empresaRequest) {
        return CriaEmpresaFocusRequest.builder()
                .nome(empresaRequest.getNomeFantasia())
                .nomeFantasia(empresaRequest.getNomeFantasia())
                .inscricaoEstadual(empresaRequest.getInscricaoEstadual())
                .inscricaoMunicipal(empresaRequest.getInscricaoMunicipal())
                .cnpj(empresaRequest.getCnpj())
                .regimeTributario(String.valueOf(empresaRequest.getConfigFiscal().getRegimeTributario().getCode()))
                .email(empresaRequest.getEmail())
                .telefone(empresaRequest.getTelefone().getPrefixo().toString()
                        + empresaRequest.getTelefone().getNumero())
                .logradouro(empresaRequest.getEndereco().getLogradouro())
                .numero(empresaRequest.getEndereco().getNumero().toString())
                .complemento(empresaRequest.getEndereco().getComplemento())
                .bairro(empresaRequest.getEndereco().getBairro())
                .cep(empresaRequest.getEndereco().getCodigoPostal())
                .municipio(empresaRequest.getEndereco().getCidade())
                .uf(empresaRequest.getEndereco().getEstado().toString())
                .enviarEmailDestinatario(empresaRequest.getConfigFiscal().getHabilitaEnvioEmailDestinatario())
                .discriminaImpostos(empresaRequest.getConfigFiscal().getDiscriminaImpostos())
                .cnpjContabilidade(empresaRequest.getConfigFiscal().getCnpjContabilidade())
                .habilitaNfe(empresaRequest.getConfigFiscal().getHabilitaNfe())
                .habilitaNfce(empresaRequest.getConfigFiscal().getHabilitaNfce())
                .habilitaNfse(empresaRequest.getConfigFiscal().getHabilitaNfse())
//                .orientacaoDanfe(empresaRequest.getConfigFiscal().getNfeConfig() != null
//                        ? empresaRequest.getConfigFiscal().getNfeConfig().getOrientacaoDanfe()
//                        : null)
                .reciboDanfe(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        && empresaRequest.getConfigFiscal().getNfeConfig().getExibirReciboNaDanfe())
                .exibeSempreIpiDanfe(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        && empresaRequest.getConfigFiscal().getNfeConfig().getImprimirColunasDoIpi())
                .exibeIssqnDanfe(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        && empresaRequest.getConfigFiscal().getNfeConfig().getMostraDadosDoIssqn())
                .exibeImpostosAdicionaisDanfe(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        && empresaRequest.getConfigFiscal().getNfeConfig().getImprimirImpostosAdicionaisNaDanfe())
                .exibeSempreVolumesDanfe(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        && empresaRequest.getConfigFiscal().getNfeConfig().getSempreMostrarVolumesNaDanfe())
                .enviarEmailHomologacao(false)
                .cscNfceProducao(empresaRequest.getConfigFiscal().getNfceConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfceConfig().getCscProducao()
                        : null)
                .idTokenNfceProducao(empresaRequest.getConfigFiscal().getNfceConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfceConfig().getIdTokenProducao().toString()
                        : null)
                .cscNfceHomologacao(null)
                .idTokenNfceHomologacao(null)
                .proximoNumeroNfeProducao(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfeConfig().getProximoNumeroProducao()
                        : null)
                .proximoNumeroNfeHomologacao(null)
                .serieNfeProducao(empresaRequest.getConfigFiscal().getNfeConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfeConfig().getProximoNumeroProducao().toString()
                        : null)
                .serieNfeHomologacao(null)
                .proximoNumeroNfceProducao(empresaRequest.getConfigFiscal().getNfceConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfceConfig().getProximoNumeroProducao()
                        : null)
                .proximoNumeroNfceHomologacao(null)
                .serieNfceProducao(empresaRequest.getConfigFiscal().getNfceConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfceConfig().getSerieProducao().toString()
                        : null)
                .serieNfceHomologacao(null)
                .proximoNumeroNfseProducao(empresaRequest.getConfigFiscal().getNfseConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfseConfig().getProximoNumeroProducao()
                        : null)
                .proximoNumeroNfseHomologacao(null)
                .serieNfseProducao(empresaRequest.getConfigFiscal().getNfseConfig() != null
                        ? empresaRequest.getConfigFiscal().getNfseConfig().getProximoNumeroProducao().toString()
                        : null)
                .serieNfseHomologacao(null)
                .arquivoCertificadoBase64(empresaRequest.getConfigFiscal().getCertificadoDigital() != null
                        ? empresaRequest.getConfigFiscal().getCertificadoDigital().getBase64()
                        : null)
                .senhaCertificado(empresaRequest.getConfigFiscal().getCertificadoDigital() != null
                        ? empresaRequest.getConfigFiscal().getSenhaCertificadoDigital()
                        : null)
                .build();
    }
}
