/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author devmat
 */
public class Usuarios {
    private int idUsuario;
    private String nome;
    private String email;
    private String nomeDeUsuario;
    private String senha;
    private int isAdmin; // Coluna correspondente ao campo IsAdmin
    private String codigoUsuario;

    // Criação do 1º construtor sem o idUsuario
    public Usuarios(String nome, String email, String nomeDeUsuario, String senha, int isAdmin, String codigoUsuario) {
        this.nome = nome;
        this.email = email;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.isAdmin = isAdmin;
        this.codigoUsuario = codigoUsuario;
    }// fim 1º construtor
    
    // Criação do 2º construtor com o idUsuario
    public Usuarios(int idUsuario, String nome, String email, String nomeDeUsuario, String senha, int isAdmin, String codigoUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.isAdmin = isAdmin;
        this.codigoUsuario = codigoUsuario;
    }//fim 2º construtor
    
    // criação do 3º construtor vazio
    public Usuarios() {
    }//fim 3º construtor

    public Usuarios(String nome, String email, String nomeDeUsuario, String senha, int isAdmin) {
        this.nome = nome;
        this.email = email;
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }


    
    

}
