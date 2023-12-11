package br.akd.svc.akadion.web.config;

import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.cliente.repository.ClienteSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClienteSistemaRepository clienteSistemaRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Optional<ClienteSistemaEntity> cliente = clienteSistemaRepository.findByCpf(cpf);
        if (cliente.isPresent()) {
            return new UserSS(
                    cliente.get().getId(),
                    cliente.get().getCpf(),
                    cliente.get().getSenha());
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
