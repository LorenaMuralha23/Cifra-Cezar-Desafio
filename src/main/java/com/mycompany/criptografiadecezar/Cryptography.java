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

            for (int i = 0; i < completeMessage.length(); i++) {
                int encryptedChar = (int) completeMessage.charAt(i);
                encryptedChar += key;
                if (encryptedChar > 127) {
                    encryptedChar = (encryptedChar - 127) - 1;
                }
                srBuilder.append(encryptedChar);
                srBuilder.append(" ");
            }
            String encryptedMessage = srBuilder.toString();

            saveEncryptedFile(fileToEncrypt.getName(), encryptedMessage);
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

            String[] asciiCodes = encryptedMessageComplete.trim().split("\\s+");
            int[] asciiCodesConverted = convertStringCodeToInt(asciiCodes, key);
            for (int code : asciiCodesConverted) {
                char messageChar = (char) code;
                stringBuilder.append(messageChar);
            }
            String decryptedMsgComplete = stringBuilder.toString();
            
            saveDescryptFile(fileToDecrypt.getName(), decryptedMsgComplete);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveEncryptedFile(String encryptedFileName, String encryptedMessage) {
        FileWriter fileWriter = null;
        try {
            String encryptedMsgPath = "yourdirectory"
                    + encryptedFileName;
            fileWriter = new FileWriter(encryptedMsgPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(encryptedMessage);
            bufferedWriter.close();
            System.out.println("File encrypted and saved successfully!\n");
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
        FileWriter fileWriter = null;
        try {
            String descryptedMsgPath = "yourdirectory"
                    + descryptedFileName;
            fileWriter = new FileWriter(descryptedMsgPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(descryptedMessage);
            bufferedWriter.close();
            System.out.println("File decrypted and saved successfully!\n");
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

    private int[] convertStringCodeToInt(String[] asciiCodesString, int key) {
        int[] asciiCodesConverted = new int[asciiCodesString.length];
        int asciiCode;
        for (int i = 0; i < asciiCodesString.length; i++) {
            asciiCode = Integer.valueOf(asciiCodesString[i]) - key;
            if (asciiCode < 0) {
                asciiCode = 127 + (asciiCode + 1);
            }
            asciiCodesConverted[i] = asciiCode;
        }

        return asciiCodesConverted;
    }

    public void closeResources() {

    }

}
