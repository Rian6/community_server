package br.com.community.controller.rest;

/**
 *
 * @author rian
 */
import br.com.community.entity.Pessoa;
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
public class PessoaCadastroRestController {

    private final PessoaService pessoaService;

    public PessoaCadastroRestController() {
        pessoaService = new PessoaService();
    }

    @PostMapping("/pessoa/salvar")
    @ResponseBody
    public String saveProduto(@RequestBody String pessoaJson) {
        Gson g = new GsonBuilder().create();
        Pessoa pessoa = g.fromJson(pessoaJson, Pessoa.class);

        pessoaService.salvarPessoa(pessoa);

        return pessoaJson;
    }
}
