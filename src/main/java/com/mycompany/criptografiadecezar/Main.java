package com.mycompany.criptografiadecezar;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        
        File fileToEncrypt = new File("yourdirectory");
        
        File fileToDecrypt = new File("yourdirectory");
        
        Cryptography cryptography = new Cryptography();
        
        cryptography.encrypt(fileToEncrypt, 10);
        
        cryptography.decrypt(fileToDecrypt, 10);
        
    }
}
