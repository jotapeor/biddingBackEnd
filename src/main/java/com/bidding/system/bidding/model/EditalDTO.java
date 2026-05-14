package com.bidding.system.bidding.model;

import java.sql.Date;

public class EditalDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Date data_fechamento;
    private String status;

    public EditalDTO() {
    }

    public EditalDTO(Long id, String titulo, String descricao, Date data_fechamento, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_fechamento = data_fechamento;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(Date data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
