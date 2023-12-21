package br.akd.svc.akadion.web.globals.cpfcnpj.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CnpjRequest {
    @NotEmpty(message = "O CNPJ não pode ser vazio")
    @Size(message = "O campo CNPJ deve ter no máximo {max} caracteres", max = 18)
    @Pattern(regexp = "(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})", message = "O CNPJ informado é inválido")
    private String cnpj;
}

