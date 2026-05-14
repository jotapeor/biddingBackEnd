package com.bidding.system.bidding.controller;

import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.model.UserRequestDTO;
import com.bidding.system.bidding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticar")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public String registrar(@RequestBody UserDTO user) {
        userService.register(user);
        return "Cadastro realizado com sucesso!";
    }

    @PostMapping("/logar")
    public String logar(@RequestBody UserRequestDTO user) {
        String token = userService.logar(user);
        return token;
    }
}
