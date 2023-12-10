package br.akd.svc.akadion.web.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDto {
    private String cpf;
    private String senha;
}
