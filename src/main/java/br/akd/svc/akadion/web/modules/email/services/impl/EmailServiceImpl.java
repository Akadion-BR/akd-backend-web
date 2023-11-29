package br.akd.svc.akadion.web.modules.email.services.impl;

import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.email.services.EmailService;
import br.akd.svc.akadion.web.modules.email.services.impl.utils.EmailServiceUtil;
import br.akd.svc.akadion.web.modules.email.utils.ConstantesEmail;
import br.akd.svc.akadion.web.modules.email.utils.GeradorBodyHtmlEmail;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.pagamento.models.entity.PagamentoSistemaEntity;
import br.akd.svc.akadion.web.modules.plano.models.entity.PlanoEntity;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Value("${EMAIL_SUBSY_AWS}")
    String emailSubsyAws;

    @Autowired
    EmailServiceUtil emailServiceUtil;

    @Autowired
    GeradorBodyHtmlEmail geradorBodyHtmlEmail;

    @Async
    @Override
    public void enviarEmailCobranca(PagamentoSistemaEntity pagamento,
                                    PlanoEntity planoEntity,
                                    ClienteSistemaEntity clienteEntity) {
        log.info("Método responsável por enviar e-mail de cobrança acessado");
        try {
            log.info(ConstantesEmail.INICIANDO_ACESSO_OBTENCAO_CLIENT_AWS_SES);
            AmazonSimpleEmailService client = emailServiceUtil.obtemClientAwsSes();

            log.info(ConstantesEmail.INICIANDO_CONSTRUCAO_OBJETO_REQUEST_EMAIL);
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(clienteEntity.getEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData(geradorBodyHtmlEmail.
                                                    geraBodyHtmlParaNovaCobranca(pagamento, planoEntity, clienteEntity)))
                                    .withText(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData("Fatura")))
                            .withSubject(new Content()
                                    .withCharset(ConstantesEmail.UTF_8).withData("A fatura da sua assinatura já " +
                                            "está disponível - " + planoEntity.getTipoPlanoEnum().getDesc())))
                    .withSource(emailSubsyAws);
            log.info(ConstantesEmail.CORPO_EMAIL_CRIADO);

            log.info(ConstantesEmail.INICIANDO_ENVIO_EMAIL);
            client.sendEmail(request);

            log.info("E-mail de cobrança enviado com sucesso");
        } catch (Exception ex) {
            log.error("Ocorreu um erro no envio do e-mail de cobrança: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Async
    @Override
    public void enviarEmailAtrasoPagamento(PagamentoSistemaEntity pagamento,
                                           PlanoEntity planoEntity,
                                           ClienteSistemaEntity clienteEntity) {
        log.info("Método responsável por enviar e-mail de atraso de pagamento acessado");
        try {
            log.info(ConstantesEmail.INICIANDO_ACESSO_OBTENCAO_CLIENT_AWS_SES);
            AmazonSimpleEmailService client = emailServiceUtil.obtemClientAwsSes();

            log.info(ConstantesEmail.INICIANDO_CONSTRUCAO_OBJETO_REQUEST_EMAIL);
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(clienteEntity.getEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData(geradorBodyHtmlEmail.
                                                    geraBodyHtmlParaAtrasoPagamento(pagamento, planoEntity, clienteEntity)))
                                    .withText(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData("Atraso")))
                            .withSubject(new Content()
                                    .withCharset(ConstantesEmail.UTF_8).withData("Não registramos o pagamento " +
                                            "da sua assinatura - " + planoEntity.getTipoPlanoEnum().getDesc())))
                    .withSource(emailSubsyAws);
            log.info(ConstantesEmail.CORPO_EMAIL_CRIADO);

            log.info(ConstantesEmail.INICIANDO_ENVIO_EMAIL);
            client.sendEmail(request);

            log.info("E-mail de atraso enviado com sucesso");
        } catch (Exception ex) {
            log.error("Ocorreu um erro no envio do e-mail de atraso: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Async
    @Override
    public void enviarEmailSucessoPagamento(PagamentoSistemaEntity pagamento,
                                            PlanoEntity planoEntity,
                                            ClienteSistemaEntity clienteEntity,
                                            EmpresaEntity empresaEntity) {
        log.info("Método responsável por enviar e-mail de pagamento confirmado acessado");
        try {
            log.info(ConstantesEmail.INICIANDO_ACESSO_OBTENCAO_CLIENT_AWS_SES);
            AmazonSimpleEmailService client = emailServiceUtil.obtemClientAwsSes();

            log.info(ConstantesEmail.INICIANDO_CONSTRUCAO_OBJETO_REQUEST_EMAIL);
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(clienteEntity.getEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData(geradorBodyHtmlEmail.
                                                    geraBodyHtmlParaSucessoPagamento(pagamento, planoEntity, clienteEntity, empresaEntity)))
                                    .withText(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData("Pagamento com sucesso")))
                            .withSubject(new Content()
                                    .withCharset(ConstantesEmail.UTF_8).withData("Seu pagamento foi confirmado - "
                                            + planoEntity.getTipoPlanoEnum().getDesc())))
                    .withSource(emailSubsyAws);
            log.info(ConstantesEmail.CORPO_EMAIL_CRIADO);

            log.info(ConstantesEmail.INICIANDO_ENVIO_EMAIL);
            client.sendEmail(request);

            log.info("E-mail de pagamento confirmado enviado com sucesso");
        } catch (Exception ex) {
            log.error("Ocorreu um erro no envio do e-mail de atraso: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Async
    @Override
    public void enviarEmailBoasVindasNovoCliente(ClienteSistemaEntity clienteSistema) {
        log.info("Método responsável por enviar e-mail de boas vindas para novos clientes acessado");
        try {
            log.info(ConstantesEmail.INICIANDO_ACESSO_OBTENCAO_CLIENT_AWS_SES);
            AmazonSimpleEmailService client = emailServiceUtil.obtemClientAwsSes();

            log.info(ConstantesEmail.INICIANDO_CONSTRUCAO_OBJETO_REQUEST_EMAIL);
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(clienteSistema.getEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData(geradorBodyHtmlEmail.
                                                    geraBodyHtmlParaBoasVindasNovoCliente(clienteSistema)))
                                    .withText(new Content()
                                            .withCharset(ConstantesEmail.UTF_8).withData("Novo cliente")))
                            .withSubject(new Content()
                                    .withCharset(ConstantesEmail.UTF_8).withData("Seja bem-vindo ao Subsy")))
                    .withSource(emailSubsyAws);
            log.info(ConstantesEmail.CORPO_EMAIL_CRIADO);

            log.info(ConstantesEmail.INICIANDO_ENVIO_EMAIL);
            client.sendEmail(request);

            log.info("E-mail de boas vindas para novo cliente enviado com sucesso");
        } catch (Exception ex) {
            log.error("Ocorreu um erro no envio do e-mail de boas-vindas: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Async
    @Override
    public void enviarEmailVencimentoProximo(ClienteSistemaEntity clienteSistema) {
        //TODO IMPLEMENTAR MÉTODO
    }

    @Async
    @Override
    public void enviarEmailAssinaturaEncerrada(ClienteSistemaEntity clienteSistema) {
        //TODO IMPLEMENTAR MÉTODO
    }

}
