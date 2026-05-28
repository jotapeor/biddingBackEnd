package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.EditalDTO;
import com.bidding.system.bidding.model.LanceDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.repository.EditalRepository;
import com.bidding.system.bidding.repository.LanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class LanceService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EditalRepository editalRepository;

    @Autowired
    private LanceRepository lanceRepository;

    public void novoLance(Long id, LanceDTO lance, String token) {
        if (tokenService.validarToken(token)) {
            UserDTO userLogado = tokenService.extrairClaim(token);
            EditalDTO edital = editalRepository.getById(id);
            if (!userLogado.getRole().equals("FORNECEDOR")) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Necessário ser fornecedor para criar novo lance!");
            }
            if (!edital.getStatus().equals("ABERTO")) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Edital fechado para lances!");
            }
            if (LocalDateTime.now().isAfter(edital.getData_fechamento())) {
                throw new ResponseStatusException(
                        HttpStatusCode.valueOf(400),
                        "Edital fechado para lances!"
                );
            }
            int rows = lanceRepository.novoLance(lance);
            if (rows == 0) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao criar lance!");
            }
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
}