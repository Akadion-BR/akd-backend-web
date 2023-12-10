package br.akd.svc.akadion.web.modules.external.backoffice.colaborador.dto.response;

import br.akd.svc.akadion.web.globals.telefone.response.TelefoneResponse;
import br.akd.svc.akadion.web.modules.external.backoffice.colaborador.enums.CargoInternoEnum;
import br.akd.svc.akadion.web.modules.external.backoffice.colaborador.enums.StatusAtividadeEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorInternoResponse {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private Boolean acessoSistemaLiberado = true;
    private String dataNascimento;
    private CargoInternoEnum cargoEnum;
    private StatusAtividadeEnum statusAtividadeEnum;
    private TelefoneResponse telefone;
}
