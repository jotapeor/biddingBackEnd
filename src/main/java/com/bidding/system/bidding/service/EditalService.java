package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.EditalDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.repository.EditalRepository;
import com.bidding.system.bidding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EditalService {

    @Autowired
    private EditalRepository editalRepository;

    @Autowired
    private TokenService tokenService;

    public void novoEdital(EditalDTO edital, UserDTO usuarioLogado) {
        String message = "";
        if (!usuarioLogado.getRole().equals("COMPRADOR")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403),
                    "Acesso negado: apenas usuários com role COMPRADOR podem criar editais"
            );
        }
        if (edital.getTitulo().isEmpty()) {
            message += "Título não preenchido!";
        }
        if (edital.getDescricao().isEmpty()) {
            message += "Descrição não preenchida!";
        }
        if (edital.getData_fechamento() == null) {
            message += "Data não preenchida!";
        }
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }
        edital.setStatus("ABERTO");
        int rows = editalRepository.novoEdital(edital);
        if (rows == 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500),
                    "Erro ao criar edital");
        }
    }

    public List<EditalDTO> listaEdital(String authHeader) {
        if (tokenService.validarToken(authHeader)) {
            return editalRepository.listaEdital();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
}