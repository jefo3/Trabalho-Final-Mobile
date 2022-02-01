package com.example.Doe.models;

public class Doacao {

    private String titulo;
    private String descricao;
    private String etiqueta;
    private String longitude;
    private String latitude;
    private String idUsuario;
    private String idDoacao;

    public Doacao(){}

    public Doacao(String titulo, String descricao, String etiqueta, String longitude, String latitude, String idUsuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.etiqueta = etiqueta;
        this.longitude = longitude;
        this.latitude = latitude;
        this.idUsuario = idUsuario;
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

    public String getEtiqueta() { return etiqueta; }

    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdUsuario(){
        return this.idUsuario;
    }

    public String getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(String idDoacao) {
        this.idDoacao = idDoacao;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", idDoacao='" + idDoacao + '\'' +
                '}';
    }

}
