package br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.entity.id;

import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoId implements Serializable {
    @Type(type = "uuid-char")
    private UUID empresa;
    private UUID id;
}
