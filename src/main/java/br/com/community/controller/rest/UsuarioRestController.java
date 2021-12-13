package br.com.community.controller.rest;

/**
 *
 * @author rian
 */
import br.com.community.dto.AutenticarDTO;
import br.com.community.dto.VerificarUsuarioExisteDTO;
import br.com.community.entity.Produto;
import br.com.community.entity.Usuario;
import br.com.community.helper.UsuarioHelper;
import br.com.community.service.ProdutoService;
import br.com.community.service.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRestController {
    
    private final UsuarioHelper usuarioHelper;
    private final UsuarioService usuarioService;
    
    public UsuarioRestController() {
        usuarioHelper = new UsuarioHelper();
        usuarioService = new UsuarioService();
    }
    
    @PostMapping("/usuario/logar")
    @ResponseBody
    public String logar(@RequestBody String usuarioJson) {
        Gson g = new GsonBuilder().create();
        Usuario usuario = g.fromJson(usuarioJson, Usuario.class);
        String resposta = usuarioHelper.autenticar(usuario);
        
        return resposta;
    }
    
    @PostMapping("/usuario/salvar")
    @ResponseBody
    public String salvar(@RequestBody String usuarioJson) {
        Gson g = new GsonBuilder().create();
        Usuario usuario = g.fromJson(usuarioJson, Usuario.class);
        
        Usuario novoUsuario = usuarioService.verificarUsuarioExiste(usuario.getLogin());
        
        VerificarUsuarioExisteDTO vue = new VerificarUsuarioExisteDTO();
        
        if (novoUsuario == null) {
            usuarioService.salvar(usuario);
            vue.setUsuarioExiste(Boolean.FALSE);
        }else{
            vue.setUsuarioExiste(Boolean.TRUE);
        }
        
        String userExiste = g.toJson(vue);
        
        return userExiste;
    }
}
