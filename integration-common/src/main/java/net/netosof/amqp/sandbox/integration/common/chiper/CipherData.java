package net.netosof.amqp.sandbox.integration.common.chiper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import net.netosof.amqp.sandbox.integration.common.exceptions.IntegrationCommonException;

/**
 *
 * @author ernesto
 */
public class CipherData{

	private PublicKey publicKey;
	private PrivateKey privateKey;

	public CipherData(String publicKeyPath, String privateKeyPath)
			throws IOException, GeneralSecurityException{
		initKeys(publicKeyPath, privateKeyPath);
	}
	
	private void initKeys(String publicKeyPath, String privateKeyPath)
			throws IOException, GeneralSecurityException{
		
		System.out.printf("Public key:\n\t%s\nPrivate key:\n\t%s\n", publicKeyPath, privateKeyPath);
		
		byte[] bytesPublicKey = Files.readAllBytes(Paths.get(publicKeyPath));
		byte[] bytesPrivateKey = Files.readAllBytes(Paths.get(privateKeyPath));

		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(bytesPublicKey);
		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(bytesPrivateKey);

		KeyFactory kFactory = KeyFactory.getInstance("RSA");
		publicKey = kFactory.generatePublic(publicSpec);
		privateKey = kFactory.generatePrivate(privateSpec);
	}
	
	/**
	 * @param word message to be encrypted.
	 * @return encrypted message.
	 * @throws IntegrationCommonException
	 */
	public String encrypt(String word) throws IntegrationCommonException{
		String result;
		try{
			Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte cipherText[] = encryptCipher.doFinal(word.getBytes(StandardCharsets.UTF_8));
			result = Base64.getEncoder().encodeToString(cipherText);
		}catch(GeneralSecurityException e){
			throw new IntegrationCommonException(e.getMessage(), e);
		}

		return result;
	}

	/**
	 * @param word message to be decrypted.
	 * @return decrypted message.
	 * @throws IntegrationCommonException
	 */
	public String decrypt(String word) throws IntegrationCommonException{
		String result;
		try{
			Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte encryptedMessage[] = Base64.getDecoder().decode(word);
			result = new String(decryptCipher.doFinal(encryptedMessage), StandardCharsets.UTF_8);
		}catch(GeneralSecurityException e){
//			e.printStackTrace(System.err);
			throw new IntegrationCommonException(e.getMessage(), e);
		}
		
		return result;
	}
}
