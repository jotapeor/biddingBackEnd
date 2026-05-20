package com.bidding.system.bidding.model;

import java.sql.Date;

public class LanceDTO {

    private Long id;
    private double valor;
    private Date data_lance;
    private Long id_edital;
    private Long id_usuario;

    public LanceDTO() {
    }

    public LanceDTO(Long id, double valor, Date data_lance, Long id_edital, Long id_usuario) {
        this.id = id;
        this.valor = valor;
        this.data_lance = data_lance;
        this.id_edital = id_edital;
        this.id_usuario = id_usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_lance() {
        return data_lance;
    }

    public void setData_lance(Date data_lance) {
        this.data_lance = data_lance;
    }

    public Long getId_edital() {
        return id_edital;
    }

    public void setId_edital(Long id_edital) {
        this.id_edital = id_edital;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
