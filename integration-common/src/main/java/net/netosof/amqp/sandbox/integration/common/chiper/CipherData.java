package net.netosof.amqp.sandbox.integration.common.chiper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import net.netosof.amqp.sandbox.integration.common.exceptions.IntegrationCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ernesto
 */
public class CipherData{
	private static final Logger LOGGER =
			LoggerFactory.getLogger(CipherData.class);
	
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public CipherData(String publicKeyPath, String privateKeyPath)
			throws IOException, GeneralSecurityException{
		initKeys(publicKeyPath, privateKeyPath);
	}
	
	private void initKeys(String publicKeyPath, String privateKeyPath)
			throws IOException, GeneralSecurityException{
		
		LOGGER.debug("Public key:\n\t{}\nPrivate key:\n\t{}\n",
				publicKeyPath, privateKeyPath);
		
		byte[] bytesPublicKey = Files.readAllBytes(Paths.get(publicKeyPath));
		byte[] bytesPrivateKey = Files.readAllBytes(Paths.get(privateKeyPath));
		
		KeySpec publicSpec = new X509EncodedKeySpec(bytesPublicKey);
		KeySpec privateSpec = new PKCS8EncodedKeySpec(bytesPrivateKey);

		KeyFactory kFactory = KeyFactory.getInstance("RSA");
		publicKey = kFactory.generatePublic(publicSpec);
		privateKey = kFactory.generatePrivate(privateSpec);
	}
	
	private byte[] getBytes(String resource) throws IOException{
		byte[] bytes = null;
		try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			BufferedInputStream reader = new BufferedInputStream(is);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1024)){
			
			byte[] buff = new byte[1024];
			for(int bs = reader.read(buff); bs != -1; bs = reader.read(buff)){
				baos.write(buff, 0, bs);
			}
		}
		
		return bytes;
	}
	
	/**
	 * @param word message to be encrypted.
	 * @return encrypted message.
	 * @throws IntegrationCommonException
	 */
	public String encrypt(String word) throws IntegrationCommonException{
		String result;
		try{
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte cipherText[] = cipher.doFinal(word.getBytes(CHARSET));
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
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte encryptedMsg[] = Base64.getDecoder().decode(word);
			result = new String(cipher.doFinal(encryptedMsg), CHARSET);
		}catch(GeneralSecurityException e){
			throw new IntegrationCommonException(e.getMessage(), e);
		}
		
		return result;
	}
}
