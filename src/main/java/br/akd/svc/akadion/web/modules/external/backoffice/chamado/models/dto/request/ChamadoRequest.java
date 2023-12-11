package br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.dto.request;

import br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.enums.CategoriaChamadoEnum;
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
public class ChamadoRequest {

    @NotEmpty(message = "O título do chamado deverá ser informado")
    @Size(message = "O título do chamado deve possuir no máximo {max} caracteres", max = 50)
    private String titulo;

    @NotEmpty(message = "A descrição do chamado deverá ser informada")
    @Size(message = "A descrição do chamado deve possuir no máximo {max} caracteres", max = 500)
    private String descricao;

    @NotNull(message = "A categoria do chamado deve ser informada")
    private CategoriaChamadoEnum categoriaChamadoEnum;
}
