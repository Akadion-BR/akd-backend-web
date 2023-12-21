package br.akd.svc.akadion.web.modules.empresa.models.dto.request;

import br.akd.svc.akadion.web.globals.endereco.dto.request.EnderecoRequest;
import br.akd.svc.akadion.web.globals.telefone.request.TelefoneRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.ConfigFiscalEmpresaRequest;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {

    @NotEmpty(message = "A razão social não pode estar vazia")
    @Size(max = 70, message = "A razão social deve conter no máximo {max} caracteres")
    private String razaoSocial;

    @NotEmpty(message = "O campo CNPJ não pode estar vazio")
    @Size(message = "O CNPJ deve possuir {max} caracteres", max = 18, min = 18)
    @Pattern(regexp = "(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})", message = "O CNPJ informado é inválido")
    private String cnpj;

    @NotEmpty(message = "O endpoint não pode estar vazio")
    @Size(max = 30, message = "O endpoint deverá conter no máximo {} caracteres")
    @Pattern(regexp = "^[a-z]*$", message = "O endpoint deve possuir apenas letras")
    private String endpoint;

    @Email(message = "O e-mail informado é inválido")
    @NotNull(message = "O e-mail não pode estar vazio")
    @Size(max = 70, message = "O e-mail deve conter no máximo {max} caracteres")
    private String email;

    @NotEmpty(message = "O nome fantasia não pode estar vazio")
    @Size(max = 70, message = "O nome fantasia deve conter no máximo {max} caracteres")
    private String nomeFantasia;

    @Pattern(regexp = "\\d{12}", message = "A inscrição estadual informada está inválida")
    private String inscricaoEstadual;

    @Pattern(regexp = "\\d{12}", message = "A inscrição municipal informada está inválida")
    private String inscricaoMunicipal;

    @Valid
    @NotNull(message = "Telefone da empresa não informado")
    private TelefoneRequest telefone;

    @Valid
    @NotNull(message = "Endereço da empresa não informado")
    private EnderecoRequest endereco;

    @Valid
    @NotNull(message = "Configuração fiscal da empresa não informada")
    private ConfigFiscalEmpresaRequest configFiscal;
}
