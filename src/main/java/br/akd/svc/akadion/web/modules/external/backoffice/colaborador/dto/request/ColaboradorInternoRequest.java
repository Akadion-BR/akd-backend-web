package br.akd.svc.akadion.web.modules.external.backoffice.colaborador.dto.request;

import br.akd.svc.akadion.web.globals.telefone.request.TelefoneRequest;
import br.akd.svc.akadion.web.modules.external.backoffice.colaborador.enums.CargoInternoEnum;
import br.akd.svc.akadion.web.modules.external.backoffice.colaborador.enums.StatusAtividadeEnum;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorInternoRequest {
    @NotEmpty(message = "O campo nome deve ser informado")
    @Size(max = 70, message = "O campo nome deve conter no máximo {max} caracteres")
    private String nome;

    @Email(message = "O e-mail informado é inválido")
    @NotEmpty(message = "O campo e-mail deve ser informado")
    @Size(max = 70, message = "O campo e-mail deve possuir no máximo {max} caracteres")
    private String email;

    @NotEmpty(message = "O campo CPF deve ser informado")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF informado é inválido")
    @Size(message = "O campo CPF deve possuir no máximo {max} caracteres", max = 14)
    private String cpf;

    @NotNull(message = "O acesso ao sistema liberado deve ser informado")
    private Boolean acessoSistemaLiberado = true;

    @NotEmpty(message = "A data de nascimento deve ser informada")
    @Pattern(regexp = "((19|20)\\d\\d)-(0\\d|1[0-2])-([0-2]\\d|3[0-1])",
            message = "A data de nascimento informada é inválida")
    private String dataNascimento;

    @NotNull(message = "O cargo deve ser informado")
    private CargoInternoEnum cargoEnum;

    @NotNull(message = "O status da atividade deve ser informado")
    private StatusAtividadeEnum statusAtividadeEnum;

    @Valid
    @NotNull(message = "O telefone deve ser informado")
    private TelefoneRequest telefone;
}
