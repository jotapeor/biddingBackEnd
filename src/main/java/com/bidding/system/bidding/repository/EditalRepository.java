package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.EditalDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class EditalRepository {

    public int novoEdital(EditalDTO edital) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("insert into editais (titulo, descricao, data_fechamento, status) values (?, ?, ?, ?)");
            stmt.setString(1, edital.getTitulo());
            stmt.setString(2, edital.getDescricao());
            stmt.setDate(3, edital.getData_fechamento());
            stmt.setString(4, edital.getStatus());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
