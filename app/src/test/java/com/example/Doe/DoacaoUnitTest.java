package com.example.Doe;

import com.example.Doe.models.Doacao;
import com.example.Doe.models.Usuario;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.assertEquals;

public class DoacaoUnitTest {
    Doacao doacao = new Doacao();


    @Test
    @DisplayName("Teste de inserção de titulo de nome no objeto do modelo Usuario")
    @Order(1)
    public void testSetTitulo() {
        String tituloTesteInsert = "Feijão";
        this.doacao.setTitulo(tituloTesteInsert);
        assertEquals(tituloTesteInsert, this.doacao.getTitulo());
    }

    @Test
    @DisplayName("Teste de inserção de campo de descricao no objeto do modelo Usuario")
    @Order(2)
    public void testSetDescricao() {
        final String descricaoTesteInsert = "Cesta basica com 10kg de feijão";
        this.doacao.setDescricao(descricaoTesteInsert);
        assertEquals(descricaoTesteInsert, this.doacao.getDescricao());
    }

    @Test
    @DisplayName("Teste de inserção de campo de etiqueta no objeto do modelo Usuario")
    @Order(3)
    public void testSetEtiqueta() {
        final String etiquetaTesteInsert = "Comida";
        this.doacao.setEtiqueta(etiquetaTesteInsert);
        assertEquals(etiquetaTesteInsert, this.doacao.getEtiqueta());
    }

    @Test
    @DisplayName("Teste de inserção de campo de longitude no objeto do modelo Usuario")
    @Order(4)
    public void testSetLongitude() {
        final String longitudeTesteInsert = "1111";
        this.doacao.setLongitude(longitudeTesteInsert);
        assertEquals(longitudeTesteInsert, this.doacao.getLongitude());
    }

    @Test
    @DisplayName("Teste de inserção de campo de latitude no objeto do modelo Usuario")
    @Order(5)
    public void testSetLatitude() {
        final String latitudeTesteInsert = "-2222";
        this.doacao.setLatitude(latitudeTesteInsert);
        assertEquals(latitudeTesteInsert, this.doacao.getLatitude());
    }

    @Test
    @DisplayName("Teste de inserção de campo de idUsuario no objeto do modelo Usuario")
    @Order(6)
    public void testSetIdUsuario() {
        final String idUsuarioTesteInsert = "sjdhasjhfashfj";
        this.doacao.setIdUsuario(idUsuarioTesteInsert);
        assertEquals(idUsuarioTesteInsert, this.doacao.getIdUsuario());
    }

    @Test
    @DisplayName("Teste de inserção de campo de idDoacao no objeto do modelo Usuario")
    @Order(7)
    public void testSetIdDoacao() {
        final String idDoacaoTesteInsert = "dsahdsajhfjasjfhsafsajfkashf";
        this.doacao.setIdDoacao(idDoacaoTesteInsert);
        assertEquals(idDoacaoTesteInsert, this.doacao.getIdDoacao());
    }


    @Test
    @DisplayName("Método de teste toString no objeto do modelo Usuario")
    @Order(8)
    public void testToString() {
        final String titulo = "Arroz";
        final String descricao = "Cesta basica de arroz";
        final String etiqueta = "Comida";
        final String longitude = "-1111";
        final String latitude = "2344";
        final String idUsuario = "dsadsafa";
        final String idDoacao = "asddasdfa";

        this.doacao.setTitulo(titulo);
        this.doacao.setDescricao(descricao);
        this.doacao.setEtiqueta(etiqueta);
        this.doacao.setLongitude(longitude);
        this.doacao.setLatitude(latitude);
        this.doacao.setIdDoacao(idDoacao);
        this.doacao.setIdUsuario(idUsuario);

        final String toStringCompare = "Doacao{" +
                "titulo='" + this.doacao.getTitulo() + '\'' +
                ", descricao='" + this.doacao.getDescricao() + '\'' +
                ", etiqueta='" + this.doacao.getEtiqueta() + '\'' +
                ", longitude='" + this.doacao.getLongitude() + '\'' +
                ", latitude='" + this.doacao.getLatitude() + '\'' +
                ", idUsuario='" +  this.doacao.getIdUsuario() + '\'' +
                ", idDoacao='" + this.doacao.getIdDoacao() + '\'' +
                "}";

        assertEquals(toStringCompare, this.doacao.toString());
    }

    @Test
    @DisplayName("Construtor do método de teste do usuario é inicializado o objeto do modelo Usuario preechendo o construto e ver testar o toString")
    @Order(9)
    public void testeConstrutor() {
        final String titulo = "Arroz";
        final String descricao = "Cesta basica de arroz";
        final String etiqueta = "Comida";
        final String longitude = "-1111";
        final String latitude = "2344";
        final String idUsuario = "dsadsafa";
        final String idDoacao = "asddasdfa";

        this.doacao = new Doacao(titulo, descricao, etiqueta, longitude, latitude, idUsuario);
        this.doacao.setIdDoacao(idDoacao);

        final String toStringCompare = "Doacao{" +
                "titulo='" + this.doacao.getTitulo() + '\'' +
                ", descricao='" + this.doacao.getDescricao() + '\'' +
                ", etiqueta='" + this.doacao.getEtiqueta() + '\'' +
                ", longitude='" + this.doacao.getLongitude() + '\'' +
                ", latitude='" + this.doacao.getLatitude() + '\'' +
                ", idUsuario='" +  this.doacao.getIdUsuario() + '\'' +
                ", idDoacao='" + this.doacao.getIdDoacao() + '\'' +
                "}";

        assertEquals(toStringCompare, this.doacao.toString());
    }

}
