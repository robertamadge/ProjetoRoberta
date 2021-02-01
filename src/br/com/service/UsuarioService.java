package br.com.service;

import br.com.DAO.UsuarioDAO;
import br.com.model.Usuario;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService(){
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticar(Usuario usuario){
        return this.usuarioDAO.autenticar(usuario);
    }
    public Usuario consultarEmail(Usuario usuario){
        return this.usuarioDAO.consultarEmail(usuario);
    }
    public boolean cadastrar(Usuario usuario){
        return this.usuarioDAO.cadastrar(usuario);
    }
}
