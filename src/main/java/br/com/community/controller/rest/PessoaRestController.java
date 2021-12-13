package br.com.community.controller.rest;

/**
 *
 * @author rian
 */
import br.com.community.entity.Pessoa;
import br.com.community.entity.Produto;
import br.com.community.service.PessoaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaRestController {

    private final PessoaService pessoaService;

    public PessoaRestController() {
        pessoaService = new PessoaService();
    }

    @GetMapping("/pessoas")
    @ResponseBody
    public String getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.getAllPessoas();

        Gson g = new GsonBuilder().create();

        String json = g.toJson(pessoas);
        return json;
    }

    @PostMapping("/pessoa/filtrarPorNome")
    @ResponseBody
    public String filtrarPorNome(@RequestBody String nome) {
        Gson g = new GsonBuilder().create();
        String nomePessoa = g.fromJson(nome, String.class);
        List<Pessoa> pessoas = pessoaService.filtrarPessoaPorNome(nomePessoa);

        String json = g.toJson(pessoas);
        return json;
    }
}
