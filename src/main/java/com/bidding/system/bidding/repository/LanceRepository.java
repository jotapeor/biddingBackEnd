package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.LanceDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class LanceRepository {

    public int novoLance(LanceDTO lance) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("insert into lances (valor, data_lance, id_edital, id_usuario) values (?, ?, ?, ?)");
            stmt.setDouble(1, lance.getValor());
            stmt.setDate(2, lance.getData_lance());
            stmt.setLong(3, lance.getId_edital());
            stmt.setLong(4, lance.getId_usuario());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}