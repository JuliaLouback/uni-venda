package main.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Criptografia {

	public String CriptografiaSenha(String senha ) {
	
		try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
              hexString.append(String.format("%02X", 0xFF & b));
            }
            
            String senhahex = hexString.toString();

            return senhahex ;
            
		}catch (NoSuchAlgorithmException e) {
			System.err.println("Não foi possível localizar o algorítmo de criptografia!" + e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {			
			System.err.println("O mecanismo de preenchimento solicitado não existe no ambiente (Sistema Operacional)!" + e.getMessage());			
			e.printStackTrace();
		} 
		
		return "";
	}
}
