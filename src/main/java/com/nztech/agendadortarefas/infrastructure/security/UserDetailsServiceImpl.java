package com.nztech.agendadortarefas.infrastructure.security;


import com.nztech.agendadortarefas.business.dto.UsuarioDTO;
import com.nztech.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token) {
        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, token);

        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();

    }


}
