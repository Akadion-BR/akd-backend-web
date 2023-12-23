package br.akd.svc.akadion.web.exceptions.models.proxy.focus;

import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FocusErrorResponse {
    private String codigo;
    private String mensagem;
    private List<FocusErrorResponseErros> erros;

    public String retornaListaDeErrosComoUnicaString() {
        String mensagemErro = "";
        for (FocusErrorResponseErros feignError : this.erros) {
            if (!ObjectUtils.isEmpty(mensagemErro))
                mensagemErro = mensagemErro + ", ";
            mensagemErro = mensagemErro + feignError.getMensagem();
        }
        return mensagemErro;
    }
}
