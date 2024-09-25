package com.mycompany.criptografiadecezar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cryptography {

    public void encrypt(File fileToEncrypt, int key) {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(fileToEncrypt));
            
            StringBuilder srBuilder = new StringBuilder();
            String lineReadText;
            while ((lineReadText = bufferReader.readLine()) != null) {
                srBuilder.append(lineReadText);
                srBuilder.append("\n");
            }
            String completeMessage = srBuilder.toString();
            srBuilder.setLength(0);
            
            char encryptedChar;
            int ecryptedCode;
            
            for (int i = 0; i < completeMessage.length(); i++) {
                ecryptedCode = (int) completeMessage.charAt(i);
                ecryptedCode += key;
                if (ecryptedCode > 127) {
                    ecryptedCode = (ecryptedCode - 127) - 1;
                }
                encryptedChar = (char) ecryptedCode;
                srBuilder.append(encryptedChar);
            }
            
            String encryptedMessage = srBuilder.toString();
            saveEncryptedFile(fileToEncrypt.getName(), encryptedMessage);
            
            //closing resources
            bufferReader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void decrypt(File fileToDecrypt, int key) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToDecrypt));
            StringBuilder stringBuilder = new StringBuilder();

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(readLine);
            }

            String encryptedMessageComplete = stringBuilder.toString();
            stringBuilder.setLength(0);

            char[] messageChars = encryptedMessageComplete.toCharArray();
            int[] asciiCodesConverted = convertAsciiCodeByTheKey(messageChars, key);
            
            for (int code : asciiCodesConverted) {
                char messageChar = (char) code;
                stringBuilder.append(messageChar);
            }
            
            String decryptedMsgComplete = stringBuilder.toString();
            
            saveDescryptFile(fileToDecrypt.getName(), decryptedMsgComplete);
            
            //closing resources
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    public void saveEncryptedFile(String encryptedFileName, String encryptedMessage) {
        FileWriter fileWriter = null;
        String currentDir = System.getProperty("user.dir");
        try {
            String encryptedMsgPath = currentDir + "\\encryptMessages\\"
                    + encryptedFileName;
            fileWriter = new FileWriter(encryptedMsgPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(encryptedMessage);
            
            System.out.println("File encrypted and saved successfully!\n");
            
            //closing resources
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void saveDescryptFile(String descryptedFileName, String descryptedMessage) {
        String currentDir = System.getProperty("user.dir");
        try {
            String descryptedMsgPath = currentDir + "\\decryptMessages\\"
                    + descryptedFileName;
            FileWriter fileWriter = new FileWriter(descryptedMsgPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(descryptedMessage);
            
            System.out.println("File decrypted and saved successfully!\n");
            
            //closing resources
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private int[] convertAsciiCodeByTheKey(char[] msgCharacters, int key){
        //pegar o caractere correto
        int[] asciiCodesConverted = new int[msgCharacters.length];
        for (int i = 0; i < msgCharacters.length; i++) {
            int charAsciiCode = (int) msgCharacters[i];
            int realAscii = charAsciiCode - key;
            if ( realAscii < 0){
                realAscii = 127 + (realAscii + 1);
            }
            asciiCodesConverted[i] = realAscii;
        }
        
        return asciiCodesConverted;
    }
    
    

}
