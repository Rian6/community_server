/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.community.controller.rest;

/**
 *
 * @author rian
 */
import br.com.community.entity.Pedido;
import br.com.community.entity.PedidoItem;
import br.com.community.entity.Pessoa;
import br.com.community.service.PedidoItemService;
import br.com.community.service.PedidoService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoItemCadastroRest {

    private final PedidoItemService pedidoItemService;

    public PedidoItemCadastroRest() {
        pedidoItemService = new PedidoItemService();
    }

    @PostMapping("/pedidoItem/salvar")
    @ResponseBody
    public String saveProduto(@RequestBody String itensPedidoJson) {
        Gson g = new GsonBuilder().create();
        
        List<PedidoItem> itens = g.fromJson(itensPedidoJson,new TypeToken<List<PedidoItem>>(){}.getType());
        Pedido pedido = g.fromJson(itensPedidoJson, Pedido.class);

        pedidoItemService.salvarPedidoItemPedido(itens, pedido);

        return "{isSalvo: true}";
    }
}
