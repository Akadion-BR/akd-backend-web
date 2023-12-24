package br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrientacaoDanfeEnum {

    PORTRAIT(1, "retrato"),
    LANDSCAPE(2, "paisagem");

    private final int code;
    private final String desc;
}
