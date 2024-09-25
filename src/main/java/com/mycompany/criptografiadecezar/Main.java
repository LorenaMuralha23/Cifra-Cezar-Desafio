package com.mycompany.criptografiadecezar;

import java.io.File;
import javax.swing.JFileChooser;

public class Main {

    public static void main(String[] args) {

//        File fileToEncrypt = new File("yourdirectory");
//        
//        File fileToDecrypt = new File("yourdirectory");
//        
        Cryptography cryptography = new Cryptography();

        // Cria um JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Selecione um arquivo para encriptografar");

        // Define que pode selecionar apenas arquivos (pode ser modificado para pastas)
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Abre o FileChooser e captura a resposta do usuário (se clicou em "Abrir" ou "Cancelar")
        int result = fileChooser.showOpenDialog(null);

        // Se o usuário clicou em "Abrir", pegamos o arquivo selecionado
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToEncrypt = fileChooser.getSelectedFile();
            cryptography.encrypt(fileToEncrypt, 10);
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }

        fileChooser.setDialogTitle("Selecione um arquivo para descriptografar");

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Abre o FileChooser e captura a resposta do usuário (se clicou em "Abrir" ou "Cancelar")
        result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToEncrypt = fileChooser.getSelectedFile();
            cryptography.decrypt(fileToEncrypt, 10);
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }

    }
}
