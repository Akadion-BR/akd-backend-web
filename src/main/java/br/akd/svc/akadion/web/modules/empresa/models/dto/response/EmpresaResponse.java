package br.akd.svc.akadion.web.modules.empresa.models.dto.response;

import br.akd.svc.akadion.web.globals.endereco.dto.response.EnderecoResponse;
import br.akd.svc.akadion.web.globals.imagem.response.ImagemResponse;
import br.akd.svc.akadion.web.globals.telefone.response.TelefoneResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.response.ConfigFiscalEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaResponse {
    private UUID id;
    private String dataCadastro;
    private String horaCadastro;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String endpoint;
    private String email;
    private String nomeFantasia;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private Boolean ativa;
    private String segmentoEmpresaEnum;
    private ImagemResponse logo;
    private TelefoneResponse telefone;
    private EnderecoResponse endereco;
    private ConfigFiscalEmpresaResponse configFiscalEmpresa;
//    private List<ChamadoResponse> chamados = new ArrayList<>();     //TODO AJUSTAR - INTEGRAÇÃO DE MS

    public EmpresaResponse buildFromEntity(EmpresaEntity empresaEntity) {
        return empresaEntity != null
                ? EmpresaResponse.builder()
                .id(empresaEntity.getId())
                .dataCadastro(empresaEntity.getDataCadastro())
                .horaCadastro(empresaEntity.getHoraCadastro())
                .nome(empresaEntity.getNome())
                .razaoSocial(empresaEntity.getRazaoSocial())
                .cnpj(empresaEntity.getCnpj())
                .endpoint(empresaEntity.getEndpoint())
                .email(empresaEntity.getEmail())
                .nomeFantasia(empresaEntity.getNomeFantasia())
                .inscricaoEstadual(empresaEntity.getInscricaoEstadual())
                .inscricaoMunicipal(empresaEntity.getInscricaoMunicipal())
                .ativa(empresaEntity.getAtiva())
                .segmentoEmpresaEnum(empresaEntity.getSegmentoEmpresaEnum().toString())
                .logo(new ImagemResponse()
                        .buildFromEntity(empresaEntity.getLogo()))
                .telefone(new TelefoneResponse()
                        .buildFromEntity(empresaEntity.getTelefone()))
                .endereco(new EnderecoResponse()
                        .buildFromEntity(empresaEntity.getEndereco()))
                .configFiscalEmpresa(new ConfigFiscalEmpresaResponse()
                        .buildFromEntity(empresaEntity.getConfigFiscalEmpresa()))
//                .chamados(new ChamadoResponse()     //TODO AJUSTAR - INTEGRAÇÃO DE MS
//                        .buildListFromEntity(empresaEntity.getChamados()))
                .build()
                : null;
    }
}
