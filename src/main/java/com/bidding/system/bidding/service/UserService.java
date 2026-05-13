package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.model.UserRequestDTO;
import com.bidding.system.bidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void register(UserDTO user) {
        String message = "";
        if (user.getNome().equals("")) {
            message = "Nome não preenchido";
        } else if (user.getEmail().equals("")) {
            message = "E-mail não preenchido";
        } else if (user.getSenha().equals("")) {
            message = "Senha não preenchida";
        } else if (user.getRole().equals("")) {
            user.setRole("FORNECEDOR");
        }

        if (!message.equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }

        repository.register(user);
    }

    public UserDTO logar(UserRequestDTO user) {
        String message = "";
        if (user.getEmail().equals("")) {
            message = "E-mail não preenchido";
        } else if (user.getSenha().equals("")) {
            message = "Senha não preenchida";
        }

        if (!message.equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }

        return repository.login(user.getEmail(), user.getSenha());
    }
}
