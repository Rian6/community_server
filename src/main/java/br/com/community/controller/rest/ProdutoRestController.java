package br.com.community.controller.rest;

/**
 *
 * @author rian
 */
import br.com.community.entity.Produto;
import br.com.community.service.ProdutoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoRestController {

    private final ProdutoService produtoService;

    private List<Produto> produtoFormatado;

    public ProdutoRestController() {
        produtoService = new ProdutoService();
    }

    @GetMapping("/produtos")
    @ResponseBody
    public String getAllProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();

        Gson g = new GsonBuilder().create();

        String json = g.toJson(produtos);
        return json;
    }

    @PostMapping("/produto/remover")
    @ResponseBody
    public String removerProduto(@RequestBody String produtoJson) {
        Gson g = new GsonBuilder().create();
        Produto produto = g.fromJson(produtoJson, Produto.class);

        produtoService.deletarProduto(produto);

        return produtoJson;
    }

    @PostMapping("/produto/filtrarPorNome")
    @ResponseBody
    public String filtrarPorNome(@RequestBody String nome) {
        Gson g = new GsonBuilder().create();
        String nomeProduto = g.fromJson(nome, String.class);
        List<Produto> produtos = produtoService.filtrarProdutos(nomeProduto);

        String json = g.toJson(produtos);
        return json;
    }

    @PostMapping("/produto/buscarPorCodigo")
    @ResponseBody
    public String buscarPorCodigo(@RequestBody String codigo) {
        Gson g = new GsonBuilder().create();
        String codigoProduto = g.fromJson(codigo, String.class);
        Produto produto = produtoService.buscarPorCodigo(codigoProduto);

        String json = g.toJson(produto);
        return json;
    }
}
