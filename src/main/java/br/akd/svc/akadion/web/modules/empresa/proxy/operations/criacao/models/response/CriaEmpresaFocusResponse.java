package br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CriaEmpresaFocusResponse {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "nome_fantasia")
    private String nomeFantasia;

    @JsonProperty(value = "inscricao_estadual")
    private Long inscricaoEstadual;

    @JsonProperty(value = "inscricao_municipal")
    private Long inscricaoMunicipal;

    @JsonProperty(value = "bairro")
    private String bairro;

    @JsonProperty(value = "cargo_responsavel")
    private String cargoResponsavel;

    @JsonProperty(value = "cep")
    private String cep;

    @JsonProperty(value = "cnpj")
    private String cnpj;

    @JsonProperty(value = "cpf")
    private String cpf;

    @JsonProperty(value = "codigo_municipio")
    private String codigoMunicipio;

    @JsonProperty(value = "codigo_pais")
    private String codigoPais;

    @JsonProperty(value = "codigo_uf")
    private String codigoUf;

    @JsonProperty(value = "complemento")
    private String complemento;

    @JsonProperty(value = "cpf_cnpj_contabilidade")
    private String cnpjContabilidade;

    @JsonProperty(value = "cpf_responsavel")
    private String cpfResponsavel;

    @JsonProperty(value = "discrimina_impostos")
    private Boolean discriminaImpostos;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "enviar_email_destinatario")
    private Boolean enviarEmailDestinatario;

    @JsonProperty(value = "habilita_nfce")
    private Boolean habilitaNfce;

    @JsonProperty(value = "habilita_nfe")
    private Boolean habilitaNfe;

    @JsonProperty(value = "habilita_nfse")
    private Boolean habilitaNfse;

    @JsonProperty(value = "logradouro")
    private String logradouro;

    @JsonProperty(value = "municipio")
    private String municipio;

    @JsonProperty(value = "nome_responsavel")
    private String nomeResponsavel;

    @JsonProperty(value = "numero")
    private String numero;

    @JsonProperty(value = "pais")
    private String pais;

    @JsonProperty(value = "String")
    private String regimeTributario;

    @JsonProperty(value = "telefone")
    private String telefone;

    @JsonProperty(value = "uf")
    private String uf;

    @JsonProperty(value = "csc_nfce_producao")
    private String cscNfceProducao;

    @JsonProperty(value = "id_token_nfce_producao")
    private Long idTokenNfceProducao;

    @JsonProperty(value = "csc_nfce_homologacao")
    private String cscNfceHomologacao;

    @JsonProperty(value = "id_token_nfce_homologacao")
    private Long idTokenNfceHomologacao;

    @JsonProperty(value = "proximo_numero_nfe_producao")
    private Long proximoNumeroNfeProducao;

    @JsonProperty(value = "proximo_numero_nfe_homologacao")
    private Long proximoNumeroNfeHomologacao;

    @JsonProperty(value = "serie_nfe_producao")
    private String serieNfeProducao;

    @JsonProperty(value = "serie_nfe_homologacao")
    private String serieNfeHomologacao;

    @JsonProperty(value = "proximo_numero_nfse_producao")
    private Long proximoNumeroNfseProducao;

    @JsonProperty(value = "proximo_numero_nfse_homologacao")
    private Long proximoNumeroNfseHomologacao;

    @JsonProperty(value = "serie_nfse_producao")
    private String serieNfseProducao;

    @JsonProperty(value = "serie_nfse_homologacao")
    private String serieNfseHomologacao;

    @JsonProperty(value = "proximo_numero_nfce_producao")
    private Long proximoNumeroNfceProducao;

    @JsonProperty(value = "proximo_numero_nfce_homologacao")
    private Long proximoNumeroNfceHomologacao;

    @JsonProperty(value = "serie_nfce_producao")
    private String serieNfceProducao;

    @JsonProperty(value = "serie_nfce_homologacao")
    private String serieNfceHomologacao;

    @JsonProperty(value = "certificado_valido_ate")
    private String certificadoValidoAte;

    @JsonProperty(value = "certificado_valido_de")
    private String certificadoValidoDe;

    @JsonProperty(value = "certificado_cnpj")
    private String certificadoCnpj;

    @JsonProperty(value = "data_ultima_emissao")
    private String dataUltimaEmissao;

    @JsonProperty(value = "caminho_logo")
    private String caminhoLogo;
}
