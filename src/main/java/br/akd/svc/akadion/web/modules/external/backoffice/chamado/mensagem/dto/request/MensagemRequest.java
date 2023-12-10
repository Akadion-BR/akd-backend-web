package br.akd.svc.akadion.web.modules.external.backoffice.chamado.mensagem.dto.request;

import br.akd.svc.akadion.web.modules.external.backoffice.chamado.mensagem.anexo.dto.request.AnexoMensagemRequest;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MensagemRequest {

    @NotEmpty(message = "O corpo da mensagem não pode ser vazio")
    @Size(message = "O corpo da mensagem não pode possuir mais do que {max} caracteres", max = 150)
    private String conteudo;

    @Valid
    private List<AnexoMensagemRequest> anexos = new ArrayList<>();
}
