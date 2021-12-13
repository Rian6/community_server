package br.com.community.commons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mendes
 */
public class QRcodeCommons extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String gerarQrCode(String path, String nomeQrCodeGerado , String texto) {
        try {
            File myFile = new File(path + "/" + nomeQrCodeGerado + "." + "png");
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, 1000, 1000, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, "png", myFile);

            String b64 = "data:image/png;base64,"+convertBase64(myFile.getAbsolutePath());
            
            System.out.println("QRCode gerado em: " + myFile.getAbsolutePath());
            
            return b64;

        } catch (WriterException ex) {
            Logger.getLogger(QRcodeCommons.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QRcodeCommons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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

}
