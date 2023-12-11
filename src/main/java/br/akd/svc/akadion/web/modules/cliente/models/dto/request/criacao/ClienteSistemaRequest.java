package br.akd.svc.akadion.web.modules.cliente.models.dto.request.criacao;

import br.akd.svc.akadion.web.globals.endereco.dto.request.EnderecoRequest;
import br.akd.svc.akadion.web.globals.telefone.request.TelefoneRequest;
import br.akd.svc.akadion.web.modules.plano.models.dto.request.PlanoRequest;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSistemaRequest {

    @Pattern(regexp = "((19|20)\\d\\d)-(0\\d|1[0-2])-([0-2]\\d|3[0-1])",
            message = "A data de nascimento informada é inválida")
    private String dataNascimento;

    @NotEmpty(message = "O campo e-mail não pode estar vazio")
    @Email(message = "O e-mail informado é inválido")
    @Size(max = 70, message = "O campo e-mail deve conter no máximo {max} caracteres")
    private String email;

    @NotEmpty(message = "O campo nome não pode estar vazio")
    @Size(max = 70, message = "O campo nome deve conter no máximo {max} caracteres")
    private String nome;

    @NotEmpty(message = "O campo senha não pode estar vazio")
    @Size(max = 25, message = "O campo senha deve conter no máximo {max} caracteres")
    private String senha;

    @NotEmpty(message = "O campo CPF não pode estar vazio")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF informado é inválido")
    @Size(message = "O campo CPF deve ter no máximo {max} caracteres", max = 14)
    private String cpf;

    @Valid
    @NotNull(message = "O plano deve ser preenchido")
    private PlanoRequest plano;

    @Valid
    @NotNull(message = "O telefone deve ser preenchido")
    private TelefoneRequest telefone;

    @Valid
    @NotNull(message = "O endereço deve ser preenchido")
    private EnderecoRequest endereco;
}
