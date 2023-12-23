package br.akd.svc.akadion.web.globals.cpfcnpj.service;

import br.akd.svc.akadion.web.exceptions.custom.InvalidRequestException;
import br.akd.svc.akadion.web.utils.Constantes;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CnpjService {

    public void realizaValidacaoCnpj(String cnpj) {

        String cnpjSemCaracteresEspeciais = cnpj.replaceAll(
                Constantes.REGEX_CARACTERES_ESPECIAIS_CPFCNPJ, "");

        String digitosVerificadores = cnpjSemCaracteresEspeciais.substring(12, 14);
        String digitos = cnpjSemCaracteresEspeciais.substring(0, 12);

        realizaCalculoDigitoVerificador(
                6,
                String.valueOf(digitosVerificadores.charAt(0)),
                digitos
        );

        realizaCalculoDigitoVerificador(
                5,
                String.valueOf(digitosVerificadores.charAt(1)),
                digitos.concat(String.valueOf(digitosVerificadores.charAt(0)))
        );
    }

    public void realizaCalculoDigitoVerificador(int digitoCalculoIteracao,
                                                String digitoVerificador,
                                                String digitosCpf) {
        int somaValoresMultiplicacao = 0;

        for (int i = 0; i < digitosCpf.length(); i++) {
            int digitoAtual = Integer.parseInt(String.valueOf(digitosCpf.charAt(i)));
            somaValoresMultiplicacao += digitoAtual * digitoCalculoIteracao;

            if (digitoCalculoIteracao == 9)
                digitoCalculoIteracao = 2;
            else digitoCalculoIteracao++;
        }

        int restoDivisaoDaSomaPorOnze = somaValoresMultiplicacao % 11;
        if (restoDivisaoDaSomaPorOnze > 9) restoDivisaoDaSomaPorOnze = 0;

        if (!Objects.equals(digitoVerificador, String.valueOf(restoDivisaoDaSomaPorOnze)))
            throw new InvalidRequestException("O CNPJ enviado é inválido");
    }

}
