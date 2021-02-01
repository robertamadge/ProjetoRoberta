package br.com.controller;

import br.com.model.Usuario;
import br.com.service.UsuarioService;
import br.com.util.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static br.com.util.JSFUtil.fecharDialog;
import static br.com.util.VerficadorUtil.*;

@ViewScoped
@ManagedBean(name = "usuarioMB")
public class UsuarioController {
    private UsuarioService usuarioService;
    private Usuario usuario;
    private Usuario usuarioLogin;
    private String erroNoLogin;
    private String confirmacaoSenha;
    private List<Usuario> listaUsuario;
    private Usuario usuarioCadastro;
    private String mensagemErroCadastro;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
        this.usuarioLogin = new Usuario();
        this.erroNoLogin = null;
        this.confirmacaoSenha = null;
        this.usuarioCadastro = new Usuario(" ", " ", " ");
        this.mensagemErroCadastro = null;
        this.usuarioCadastro = new Usuario();

    }

    public void logar() {
        if (validarValoresLogin()) {
            boolean logado = this.usuarioService.autenticar(usuarioLogin);
            if (logado) {
                fecharDialog("dlgLogin");
                SessionUtil.getInstance().gravarUsuario(this.usuarioService.consultarEmail(usuarioLogin));
            } else {
                this.erroNoLogin = "Email ou senha incorretos";
            }
        } else {
            this.erroNoLogin = "E-mail e senha obrigatórios.";
        }
    }

    private boolean validarValoresLogin() {
        return naoEstaNuloOuVazio(this.usuarioLogin.getEmail()) && naoEstaNuloOuVazio(this.usuarioLogin.getSenha());
    }

    private boolean validarDadosCadastro() {
        return validarValoresLogin() && naoEstaNuloOuVazio(this.usuarioCadastro.getNome()) && naoEstaNuloOuVazio(this.confirmacaoSenha);
    }

    public void cadastrar() {
        if (validarDadosCadastro()) {
            this.mensagemErroCadastro = "Preencher todos os campos!";
        } else if (!this.usuarioCadastro.getSenha().equals(this.confirmacaoSenha)) {
            this.mensagemErroCadastro = "Senha e confirmaão devem ser iguais!";
        } else {
            Boolean retorno = this.usuarioService.cadastrar(usuarioCadastro);
            if (retorno) {
                SessionUtil.getInstance().gravarUsuario((this.usuarioService.consultarEmail(usuarioCadastro)));
                fecharDialog("dlgLogin");
                fecharDialog("dlgCadastrarUsuario");
            } else {
                this.mensagemErroCadastro = "Erro ao tentar realizar cadastro!";
            }

        }
    }

    public void limparUsuario(){
        this.usuario = new Usuario();
    }

    public void limparMensagem() {
        this.erroNoLogin = null;
        this.mensagemErroCadastro = null;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getErroNoLogin() {
        return erroNoLogin;
    }

    public void setErroNoLogin(String erroNoLogin) {
        this.erroNoLogin = erroNoLogin;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public String getMensagemErroCadastro() {
        return mensagemErroCadastro;
    }

    public void setMensagemErroCadastro(String mensagemErroCadastro) {
        this.mensagemErroCadastro = mensagemErroCadastro;
    }

    public Boolean getLogado() {
        return SessionUtil.getInstance().getLogado();
    }

    public String getNomeUsuarioLogado() {
        return SessionUtil.getInstance().getUserSession().getNome();
    }
}





