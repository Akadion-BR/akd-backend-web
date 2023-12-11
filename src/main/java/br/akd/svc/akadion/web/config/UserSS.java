package br.akd.svc.akadion.web.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final UUID clienteId;
    private final String cpf;
    private final String senha;

    public UserSS(UUID idCliente,
                  String cpf,
                  String senha) {
        super();
        this.clienteId = idCliente;
        this.cpf = cpf;
        this.senha = senha;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
