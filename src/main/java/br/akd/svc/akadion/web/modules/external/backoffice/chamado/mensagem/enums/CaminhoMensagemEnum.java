package br.akd.svc.akadion.web.modules.external.backoffice.chamado.mensagem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CaminhoMensagemEnum {

    SUPORTE_PARA_CLIENTE (0, "Suporte para cliente"),
    CLIENTE_PARA_SUPORTE (1, "Cliente para suporte");

    private final int code;
    private final String desc;

}
