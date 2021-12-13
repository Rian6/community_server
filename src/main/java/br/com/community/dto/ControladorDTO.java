/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.community.dto;

/**
 *
 * @author Mendes
 */
public class ControladorDTO {
    private String console = "";

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = this.console+"\n"+console;
    }
}
