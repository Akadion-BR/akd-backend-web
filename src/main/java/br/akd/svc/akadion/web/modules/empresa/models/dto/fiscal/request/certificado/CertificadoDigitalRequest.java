package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.certificado;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CertificadoDigitalRequest {

    @NotEmpty(message = "O nome do certificado digital deve estar preenchido")
    String nomeArquivo;

    @NotEmpty(message = "O tipo do certificado digital deve estar preenchido")
    String tipoArquivo;

    @NotEmpty(message = "A composição do arquivo deve estar preenchido")
    String base64;

    @NotEmpty(message = "O tamanho do arquivo deve estar preenchido")
    private Long tamanhoArquivo;
}
