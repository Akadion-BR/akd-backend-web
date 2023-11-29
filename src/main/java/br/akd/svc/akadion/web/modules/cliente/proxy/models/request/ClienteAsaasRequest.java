package br.akd.svc.akadion.web.modules.cliente.proxy.models.request;

import br.akd.svc.akadion.web.globals.endereco.dto.request.EnderecoRequest;
import br.akd.svc.akadion.web.modules.cliente.models.dto.request.atualizacao.AtualizaClienteSistemaRequest;
import br.akd.svc.akadion.web.modules.cliente.models.dto.request.criacao.ClienteSistemaRequest;
import br.akd.svc.akadion.web.modules.cliente.proxy.utils.ClienteAsaasProxyUtils;
import lombok.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAsaasRequest {

    private String name;
    private String cpfCnpj;
    private String email;
    private String phone;
    private String mobilePhone;
    private String address;
    private String addressNumber;
    private String complement;
    private String province;
    private String postalCode;
    private String externalReference;
    private Boolean notificationDisabled;
    private String additionalEmails;
    private String municipalInscription;
    private String stateInscription;
    private String groupName;
    private String company;

    public ClienteAsaasRequest constroiObjetoCriaClienteAsaasRequest(ClienteSistemaRequest clienteSistemaRequest) {
        EnderecoRequest endereco = clienteSistemaRequest.getEndereco();
        return ClienteAsaasRequest.builder()
                .name(clienteSistemaRequest.getNome())
                .cpfCnpj(clienteSistemaRequest.getCpf())
                .email(clienteSistemaRequest.getEmail())
                .phone(ClienteAsaasProxyUtils.obtemTelefoneFixoCliente(clienteSistemaRequest.getTelefone()))
                .mobilePhone(ClienteAsaasProxyUtils.obtemTelefoneCelularCliente(clienteSistemaRequest.getTelefone()))
                .address(endereco != null ? endereco.getLogradouro() : null)
                .addressNumber(endereco != null ? endereco.getNumero().toString() : null)
                .complement(endereco != null ? endereco.getComplemento() : null)
                .province(endereco != null ? endereco.getBairro() : null)
                .postalCode(endereco != null ? endereco.getCodigoPostal() : null)
                .externalReference(clienteSistemaRequest.getCpf())
                .notificationDisabled(true)
                .additionalEmails(null)
                .municipalInscription(null)
                .stateInscription(null)
                .groupName("AKD")
                .company("Akadion")
                .build();
    }

    public ClienteAsaasRequest constroiObjetoCriaClienteAsaasRequestParaAtualizacao(
            AtualizaClienteSistemaRequest atualizaClienteSistemaRequest) {
        EnderecoRequest endereco = atualizaClienteSistemaRequest.getEndereco();
        return ClienteAsaasRequest.builder()
                .name(atualizaClienteSistemaRequest.getNome())
                .cpfCnpj(atualizaClienteSistemaRequest.getCpf())
                .email(atualizaClienteSistemaRequest.getEmail())
                .phone(ClienteAsaasProxyUtils.obtemTelefoneFixoCliente(atualizaClienteSistemaRequest.getTelefone()))
                .mobilePhone(ClienteAsaasProxyUtils.obtemTelefoneCelularCliente(atualizaClienteSistemaRequest.getTelefone()))
                .address(endereco != null ? endereco.getLogradouro() : null)
                .addressNumber(endereco != null ? endereco.getNumero().toString() : null)
                .complement(endereco != null ? endereco.getComplemento() : null)
                .province(endereco != null ? endereco.getBairro() : null)
                .postalCode(endereco != null ? endereco.getCodigoPostal() : null)
                .externalReference(atualizaClienteSistemaRequest.getCpf())
                .notificationDisabled(true)
                .additionalEmails(null)
                .municipalInscription(null)
                .stateInscription(null)
                .groupName("AKD")
                .company("Akadion")
                .build();
    }
}
