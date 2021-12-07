/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.community.helper;

import br.com.community.commons.PDFCommons;
import br.com.community.entity.Pedido;
import br.com.community.entity.Produto;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mendes
 */
public class RelatorioPedidoHelper {

    private Pedido pedido;
    private List<Produto> produtos;
    private Integer qtdTotalProdutos;
    private Double valorTotalProdutos = 0.00;

    public String gerarRelatorio(Pedido pedido, List<Produto> produtos) {
        PDFCommons pdfCommons = new PDFCommons();

        this.pedido = pedido;
        this.produtos = produtos;
        this.qtdTotalProdutos = produtos.size();

        String htmlFormatado = "";

        try {
            htmlFormatado = formatarHtml();
        } catch (ParseException ex) {
            Logger.getLogger(RelatorioPedidoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pdfCommons.criarRelatorioHtml("Reladorio_Pedido", htmlFormatado);
    }

    private String formatarHtml() throws ParseException {

        StringBuilder b = new StringBuilder();

        String html;

        DateFormat dateFormatada = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dataFormatada = dateFormatada.format(date);

        b.append("<!DOCTYPE html>\n");
        b.append("<html>\n");
        b.append("<head>\n");
        b.append("    <meta charset=\"UTF-8\" />\n");
        b.append("    <title></title>\n");
        b.append("</head>\n");
        b.append("<body>\n");
        b.append("    <div style=\"display: flex;\n");
        b.append("    justify-content: center;\n");
        b.append("    align-items: center;\">\n");
        b.append("        <div style=\"\n");
        b.append("        padding: 15px;\n");
        b.append("        border-radius: 10px; \n");
        b.append("        font-family: Gill Sans, sans-serif;\">\n");
        b.append("            <div style=\"display: flex;\n");
        b.append("    flex-direction: row;\n");
        b.append("    justify-content: center;\n");
        b.append("    align-items: center;\">\n");
        b.append("                <a target=\"_blank\">\n");
        b.append("                    <img src=\"./images/icone_relatorio_pedido.png\"\n");
        b.append("                        style=\"display: block;\" width=\"150\" />\n");
        b.append("                </a>\n");
        b.append("                <h1 style=\"font-size: 46px\">\n");
        b.append("                    <strong>Pedido</strong>\n");
        b.append("                </h1>\n");
        b.append("            </div>\n");
        b.append("            <div style=\"\n");
        b.append("                color: rgb(245, 238, 238);\n");
        b.append("                text-align: left;\n");
        b.append("                padding: 5px;\n");
        b.append("                border-radius: 10px;\n");
        b.append("                background-color: #587493;\">\n");
        b.append("                <p><strong>Dados do pedido:</strong> </p>\n");
        b.append("                <p>Cliente:&nbsp;<strong>Rian Mendes dos Santos</strong> </p>\n");
        b.append("                <p>Gerado em:&nbsp;\n");
        b.append("                    <strong>" + dataFormatada + "</strong>\n");
        b.append("                </p>\n");
        b.append("            </div>\n");
        b.append("            <div style=\"\n");
        b.append("            padding: 15px;\">\n");
        b.append("            <table \n");
        b.append("            cellpadding=\"0\" \n");
        b.append("            cellspacing=\"15\" \n");
        b.append("            align=\"center\">\n");
        b.append("                <tr align=\"center\">\n");
        b.append("                    <td align=\"left\">\n");
        b.append("                    </td>\n");
        b.append("                    <td align=\"left\">\n");
        b.append("                        <p><strong>Código</strong></p>\n");
        b.append("                    </td>\n");
        b.append("                    <td align=\"left\">\n");
        b.append("                        <p><strong>Produto</strong></p>\n");
        b.append("                    </td>\n");
        b.append("                    <td align=\"left\">\n");
        b.append("                        <p><strong>Descrição</strong></p>\n");
        b.append("                    </td>\n");
        b.append("                    <td align=\"left\">\n");
        b.append("                        <p><strong>QTD.</strong></p>\n");
        b.append("                    </td>\n");
        b.append("                    <td align=\"right\">\n");
        b.append("                        <p><strong>SubTotal</strong></p>\n");
        b.append("                    </td>\n");
        b.append("                </tr>\n");

        produtos.forEach(produto -> {
            b.append("                <tr align=\"center\">\n");
            b.append("                    <td align=\"left\">\n");
            b.append("                         <img src=\"" + (produto.getProdutoImagem() != null ? produto.getProdutoImagem().getArquivoBase64() : "") + "\" \n");
            b.append("                        style=\"display: block;\" width=\"100\" /> \n");
            b.append("                    </td>\n");
            b.append("                    <td align=\"left\">\n");
            b.append("                        <p>" + produto.getCodigoBarra() + "</p>\n");
            b.append("                    </td>\n");
            b.append("                    <td align=\"left\">\n");
            b.append("                        <p>" + produto.getNome() + "</p>\n");
            b.append("                    </td>\n");
            b.append("                    <td align=\"left\">\n");
            b.append("                        <p>" + produto.getDescricao() + "</p>\n");
            b.append("                    </td>\n");
            b.append("                    <td align=\"right\">\n");
            b.append("                        <p>1</p>\n");
            b.append("                    </td>\n");
            b.append("                    <td align=\"right\">\n");
            b.append("                        <p>R$" + produto.getPreco().doubleValue() + "</p>\n");
            b.append("                    </td>\n");
            b.append("                </tr>\n");
            valorTotalProdutos = produto.getPreco().doubleValue() + valorTotalProdutos;
        });

        b.append("                <tr>\n");
        b.append("                    <td align=\"left\">\n");
        b.append("                        Total:&nbsp;<strong>R$" + valorTotalProdutos.toString().replace(".", ",") + "</strong><br /><br />\n");
        b.append("                    </td>\n");
        b.append("                </tr>\n");
        b.append("            </table>\n");
        b.append("        </div>\n");
        b.append("        </div>\n");
        b.append("    </div>\n");
        b.append("</body>\n");
        b.append("\n");
        b.append("</html>");

        html = b.toString();
        return html;
    }

}
