package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.EditalDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<EditalDTO> listaEdital() {
        List<EditalDTO> listaEdital = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("select * from editais");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EditalDTO edital = new EditalDTO();
                edital.setId(rs.getLong("id"));
                edital.setTitulo(rs.getString("titulo"));
                edital.setDescricao(rs.getString("descricao"));
                edital.setData_fechamento(rs.getDate("data_fechamento"));
                edital.setStatus(rs.getString("status"));
                listaEdital.add(edital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEdital;
    }
}
