package br.akd.svc.akadion.web.globals.proxy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProxyModuleEnum {
    CLIENTE_SISTEMICO(0, "CLIENTE SISTÊMICO"),
    PLANO(1, "PLANO DE ASSINATURA"),
    COBRANCA(3, "COBRANÇA"),
    COLABORADOR_RAIZ(4, "COLABORADOR RAIZ");

    private final int code;
    private final String desc;
}
