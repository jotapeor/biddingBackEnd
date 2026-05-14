package com.bidding.system.bidding.controller;

import com.bidding.system.bidding.model.EditalDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.service.EditalService;
import com.bidding.system.bidding.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/editais")
public class EditalController {

    @Autowired
    private EditalService editalService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/criar")
    public String criarEdital(@RequestBody EditalDTO edital, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        UserDTO usuarioLogado = tokenService.extrairClaim(token);
        editalService.novoEdital(edital, usuarioLogado);
        return "Edital criado com sucesso";
    }

    @GetMapping
    public List<EditalDTO> listaEdital(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (tokenService.validarToken(token)) {
            return editalService.listaEdital();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
}