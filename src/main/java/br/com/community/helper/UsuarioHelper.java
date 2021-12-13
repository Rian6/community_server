/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.helper;

import br.com.community.commons.QRcodeCommons;
import br.com.community.dto.AutenticarDTO;
import br.com.community.entity.Usuario;
import br.com.community.service.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author rian
 */
public class UsuarioHelper {

    private final UsuarioService usuarioService;

    public UsuarioHelper() {
        usuarioService = new UsuarioService();
    }

    public String autenticar(Usuario usuario) {

        AutenticarDTO autenticar = new AutenticarDTO();
        Gson g = new GsonBuilder().create();

        if (usuario == null) {
            autenticar.setErro("Digite um usuario!");
            autenticar.setIsAutenticado(false);

            String json = g.toJson(autenticar);

            return json;
        }

        Usuario usuarioResponse = usuarioService.autenticar(usuario.getLogin(), usuario.getSenha());

        if (usuarioResponse == null) {
            autenticar.setErro("Usuario ou login invalido!");
            autenticar.setIsAutenticado(false);

            String json = g.toJson(autenticar);

            return json;
        }

        String qrcode = "";
        
        QRcodeCommons qm = new QRcodeCommons();
        

        
        autenticar.setIdUsuario(usuarioResponse.getId());
        autenticar.setIsAutenticado(true);
        autenticar.setNome(usuarioResponse.getNome());
        autenticar.setLogin(usuarioResponse.getLogin());
        autenticar.setSenha(usuarioResponse.getSenha());
        autenticar.setToken("305460");

        qrcode = qm.gerarQrCode("./tmp/user/qrcode", autenticar.getLogin()+autenticar.getSenha(), g.toJson(autenticar));

        autenticar.setQrcode(qrcode);
        String json = g.toJson(autenticar);

        return json;
    }

}
