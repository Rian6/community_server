package br.com.community.controller.rest;

/**
 *
 * @author rian
 */
import br.com.community.commons.JsonCommons;
import br.com.community.dto.PdfDTO;
import br.com.community.entity.Pedido;
import br.com.community.entity.PedidoItem;
import br.com.community.entity.Produto;
import br.com.community.helper.RelatorioPedidoHelper;
import br.com.community.service.PedidoService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoRestController {

    private final PedidoService pedidoService;

    private List<PedidoItem> listaPedidoItem = new ArrayList();

    public PedidoRestController() {
        pedidoService = new PedidoService();
    }

    @GetMapping("/pedidos")
    @ResponseBody
    public String getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();

        Gson g = new GsonBuilder().create();

        String json = g.toJson(pedidos);
        return json;
    }

    @PostMapping("/pedido/salvar")
    @ResponseBody
    public String saveProduto(@RequestBody String pedidoJson) {
        Gson g = new GsonBuilder().create();

        String jsonPedidoNovo = formatarJsonPedido(pedidoJson);

        pedidoJson = pedidoJson.replace(jsonPedidoNovo + "|", "");

        Pedido pedido = g.fromJson(jsonPedidoNovo, Pedido.class);

        JsonCommons jsonCommons = new JsonCommons();

        List<Produto> produtos = jsonCommons.converterJsonToList(pedidoJson);

        listaPedidoItem = new ArrayList();
        produtos.forEach(produto -> {
            PedidoItem pedidoItem = new PedidoItem();

            pedidoItem.setProduto(produto);
            listaPedidoItem.add(pedidoItem);
        });

        pedidoService.salvarPedido(pedido, listaPedidoItem);

        return pedidoJson;
    }

    @PostMapping("/pedido/relatorio")
    @ResponseBody
    public String gerarRelatorio(@RequestBody String pedidoJson) {
        Gson g = new GsonBuilder().create();

        String jsonPedidoNovo = formatarJsonPedido(pedidoJson);

        pedidoJson = pedidoJson.replace(jsonPedidoNovo + "|", "");

        Pedido pedido = g.fromJson(jsonPedidoNovo, Pedido.class);

        JsonCommons jsonCommons = new JsonCommons();

        List<Produto> produtos = jsonCommons.converterJsonToList(pedidoJson);
        
        
        System.out.println(pedidoJson);
        RelatorioPedidoHelper rel = new RelatorioPedidoHelper();
        String blob = rel.gerarRelatorio(pedido, produtos);

        PdfDTO pdfdto = new PdfDTO();
        pdfdto.setBloob(blob);
        String json = g.toJson(pdfdto);
        return json;
    }

    private String formatarJsonPedido(String json) {

        String jsonFormatado = "";

        int i = 0;
        while (!(json.charAt(i) + "").equals("|")) {
            jsonFormatado = jsonFormatado + json.charAt(i);
            i++;
        }

        System.out.print(jsonFormatado);
        return jsonFormatado;
    }
}
