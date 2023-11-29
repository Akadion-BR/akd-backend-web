package br.akd.svc.akadion.web.modules.email.services.impl.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailServiceUtil {

    @Value("${AWS_ACCESS_KEY}")
    String awsAccessKey;

    @Value("${AWS_SECRET_KEY}")
    String awsSecretKey;

    public AWSCredentialsProvider obtemCredenciaisAwsSes() {

        log.debug("Método responsável por obter as credenciais AWS acessado");

        log.debug("Realizando obtenção das credenciais AWS através do objeto awsCredentialsProvider...");
        AWSCredentialsProvider awsCredentialsProvider = new AWSCredentialsProvider() {
            @Override
            public void refresh() {
            }

            @Override
            public AWSCredentials getCredentials() {
                return new AWSCredentials() {
                    @Override
                    public String getAWSSecretKey() {
                        return awsSecretKey;
                    }

                    @Override
                    public String getAWSAccessKeyId() {
                        return awsAccessKey;
                    }
                };
            }
        };
        log.debug("Credenciais AWS obtidas com sucesso. Retornando...");
        return awsCredentialsProvider;
    }

    public AmazonSimpleEmailService obtemClientAwsSes() {
        log.debug("Método de obtenção do cliente AWS SES Acessado");

        log.debug("Iniciando construção do objeto AmazonSimpleEmailService...");
        AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard()
                        .withCredentials(obtemCredenciaisAwsSes())
                        .withRegion(Regions.US_EAST_1)
                        .build();
        log.debug("Objeto amazonSimpleEmailService buildado com sucesso. Retornando...");
        return client;
    }

}
