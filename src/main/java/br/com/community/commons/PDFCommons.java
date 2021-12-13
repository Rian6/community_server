/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.community.commons;

import com.lowagie.text.DocumentException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Base64;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Mendes
 */
public class PDFCommons {

    public String criarRelatorioHtml(String nomeRelatorio, String html) {
        String outputFile = "C:\\Users\\Mendes\\Desktop\\Relatorios\\" + nomeRelatorio + ".pdf";

        Boolean verificador = generatePDF(outputFile, html);

        String base64 = "data:application/pdf;base64,"+convertBase64(outputFile);
        String retorno = verificador
                ? "Relatorio gerado com sucesso!"
                : "Erro ao gerar relatorio!";

        return base64;
    }

    private String convertBase64(String caminho) {
        String b64 = "";

        try {
            File file = new File(caminho);
            byte[] bytes = Files.readAllBytes(file.toPath());

            b64 = Base64.getEncoder().encodeToString(bytes);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b64;
    }

    private Boolean generatePDF(String outputPdfPath, String html) {
        try {
            OutputStream out = new FileOutputStream(outputPdfPath);

            //Flying Saucer part
            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(out);

            out.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
