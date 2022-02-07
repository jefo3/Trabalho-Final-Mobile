package com.example.Doe;

import com.example.Doe.models.Usuario;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.*;

public class UsuarioUnitTest {

    Usuario user = new Usuario();


    @Test
    @DisplayName("Teste de inserção de campo de nome no objeto do modelo Usuario")
    @Order(1)
    public void testSetName() {
        String nameTesteInsert = "José";
        this.user.setNome(nameTesteInsert);
        assertEquals(nameTesteInsert, this.user.getNome());
    }

    @Test
    @DisplayName("Teste de inserção de campo de email no objeto do modelo Usuario")
    @Order(2)
    public void testSetEmail() {
        final String emailTesteInsert = "Jose@gmail.com";
        this.user.setEmail(emailTesteInsert);
        assertEquals(emailTesteInsert, this.user.getEmail());
    }

    @Test
    @DisplayName("Teste de inserção de campo de senha no objeto do modelo Usuario")
    @Order(3)
    public void testSetSenha() {
        final String senhaTesteInsert = "qwerty";
        this.user.setSenha(senhaTesteInsert);
        assertEquals(senhaTesteInsert, this.user.getSenha());
    }

    @Test
    @DisplayName("Teste de inserção de campo de telefone no objeto do modelo Usuario")
    @Order(4)
    public void testSetTelefone() {
        final String telefoneTesteInsert = "00000000000";
        this.user.setTelefone(telefoneTesteInsert);
        assertEquals(telefoneTesteInsert, this.user.getTelefone());
    }

    @Test
    @DisplayName("Teste de inserção de campo de id no objeto do modelo Usuario")
    @Order(5)
    public void testSetId() {
        final String idTesteInsert = "dsahdsajhfjasjfhsafsajfkashf";
        this.user.setId(idTesteInsert);
        assertEquals(idTesteInsert, this.user.getId());
    }

    @Test
    @DisplayName("Método de teste toString no objeto do modelo Usuario")
    @Order(6)
    public void testToString() {
        final String nome = "José";
        final String email = "jose@gmail.com";
        final String telefone = "85922222222";
        final String senha = "qwerty";
        final String id = "ksdjaskjdkaskdsa";

        this.user.setNome(nome);
        this.user.setSenha(senha);
        this.user.setEmail(email);
        this.user.setTelefone(telefone);
        this.user.setId(id);

        final String toStringCompare = "Usuario{" +
                "nome='" + this.user.getNome() + '\'' +
                ", email='" + this.user.getEmail() + '\'' +
                ", telefone='" + this.user.getTelefone() + '\'' +
                ", senha='" + this.user.getSenha() + '\'' +
                ", id='" + this.user.getId() + '\'' + "}";

        assertEquals(toStringCompare, this.user.toString());
    }

    @Test
    @DisplayName("Construtor do método de teste do usuario é inicializado o objeto do modelo Usuario preechendo o construto e ver testar o toString")
    @Order(7)
    public void testeConstrutor() {
        final String nome = "José";
        final String email = "jose@gmail.com";
        final String telefone = "85922222222";
        final String senha = "qwerty";
        final String id = "ksdjaskjdkaskdsa";

        this.user = new Usuario(nome, email, telefone, senha);
        this.user.setId(id);

        final String toStringCompare = "Usuario{" +
                "nome='" + this.user.getNome() + '\'' +
                ", email='" + this.user.getEmail() + '\'' +
                ", telefone='" + this.user.getTelefone() + '\'' +
                ", senha='" + this.user.getSenha() + '\'' +
                ", id='" + this.user.getId() + '\'' + "}";

        assertEquals(toStringCompare, this.user.toString());
    }
}
