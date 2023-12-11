package br.akd.svc.akadion.web.modules.external.backoffice.chamado.mensagem.anexo.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnexoMensagemRequest {
    @NotNull(message = "O anexo não pode ser vazio")
    private byte[] dados;

    @NotEmpty(message = "O nome do anexo não pode ser vazio")
    @Size(message = "O nome do anexo não pode ser maior do que {max} caracteres", max = 50)
    private String nome;

    @NotEmpty(message = "O tipo do anexo não pode ser vazio")
    @Size(message = "O nome do tipo do anexo não pode ser maior do que {max} caracteres", max = 50)
    private String tipo;
}
