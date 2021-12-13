package br.com.community.controller.rest;
/**
 *
 * @author rian
 */
import br.com.community.entity.Produto;
import br.com.community.service.ProdutoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoCadastroRestController {

    private final ProdutoService produtoService;

    public ProdutoCadastroRestController() {
        produtoService = new ProdutoService();
    }

    @PostMapping("/produto/salvar")
    @ResponseBody
    public String saveProduto(@RequestBody String produtoJson) {
        Gson g = new GsonBuilder().create();
        Produto produto = g.fromJson(produtoJson, Produto.class);
        
        produtoService.salvarProduto(produto);
        
        return produtoJson;
    }
}
