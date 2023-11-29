package br.akd.svc.akadion.web.globals.imagem.utils;

import br.akd.svc.akadion.web.exceptions.InvalidRequestException;
import br.akd.svc.akadion.web.globals.imagem.enums.TipoImagemEnum;

public class ImagemUtils {

    ImagemUtils() {
    }

    public static TipoImagemEnum realizaTratamentoTipoDeImagem(String tipoImagem) {
        if (tipoImagem.equals("image/png")) {
            return TipoImagemEnum.PNG;
        } else if (tipoImagem.equals("image/jpeg")) {
            return TipoImagemEnum.JPG;
        } else {
            throw new InvalidRequestException("O arquivo enviado deve ser uma imagem");
        }

    }
}
