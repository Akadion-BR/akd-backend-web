package br.akd.svc.akadion.web.exceptions.models.proxy.focus;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FocusErrorResponseErros {
    private String codigo;
    private String mensagem;
    private String campo;
}
