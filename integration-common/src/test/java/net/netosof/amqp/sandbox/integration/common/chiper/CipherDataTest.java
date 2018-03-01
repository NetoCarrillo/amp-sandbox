package net.netosof.amqp.sandbox.integration.common.chiper;

import java.io.IOException;
import java.security.GeneralSecurityException;
import net.netosof.amqp.sandbox.integration.common.exceptions.IntegrationCommonException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author ernesto
 */
@RunWith(JUnit4.class)
public class CipherDataTest{
	
	private static final String CLIENT_PUBLIC_KEY =
			"/Users/ernesto/workspace_vault/keys/espacia_public.der";
	private static final String CLIENT_PRIVATE_KEY =
			"/Users/ernesto/workspace_vault/keys/espacia_private.der";
	
	private static final String SERVER_PUBLIC_KEY =
			"/Users/ernesto/workspace_vault/keys/vault_espacia_public.der";
	private static final String SERVER_PRIVATE_KEY =
			"/Users/ernesto/workspace_vault/keys/vault_espacia_private.der";
	
	private final CipherData clientCipher;
	private final CipherData serverCipher;
	
	public CipherDataTest() throws GeneralSecurityException, IOException{
		clientCipher = new CipherData(SERVER_PUBLIC_KEY, CLIENT_PRIVATE_KEY);
		serverCipher = new CipherData(CLIENT_PUBLIC_KEY, SERVER_PRIVATE_KEY);
	}
	
//	@Test
//	public void keyBytes() throws IOException, GeneralSecurityException, IntegrationCommonException{
//		String chain = "QBFIgbWsIEpaYcSYq2DsP5Zo6EXBJ3q3InuCBpvdQAsVc8r64ddFtoLuAFsKHuTpCiYY/S2uywlKGt2SGsvPI21KVxHeqG4gs+QNSdcZ0emp9FGg+ZwMCnvaLPzXav/UQXiH77rNikUjIo5F9NtyUFopKDqv8SUdDe25TIj2aXUu79UwFYxs6fmoY+1hkNQBKkNHa2nHsufULWdNvQvwly3icxTymZG9NFEyShu4DahYiBgqCx3J0GhmPMKdGVmJWGEm3iueIuU6OWtImPz981M30OWslLxVl0VSeDC2tlzZzgOQv+Rdi4NqKPsZqeiPh2wHkDDXRr1RpokrWsp4gg==";
//		
//		String pbl = "/Users/ernesto/workspace_espacia/espacia_services/EspaciaAPI/config/espaciaPublic_key.der";
//		String prv = "/Users/ernesto/workspace_espacia/espacia_services/EspaciaAPI/config/espaciaPrivate_key.der";
//		
//		CipherData cipher = new CipherData(pbl, prv);
//		
//		System.out.println(cipher.decrypt(chain));
//	}
	
	@Test
	public void decryptFromServer() throws IntegrationCommonException{
//		String message =
//			"uCVSZyYUh1nW8kbpT1UVXsZHO6MW/U5P2ds7FfopQlj6TIQmbVS0+Kr6KWD3fQkm25Dwpt1DT1j7MntsaFAo3t" +
//			"E/mNOE5XcMLChO5NqelP3Tb5CkWfEp83ohdIaOH1rZC5EbpRFupexIUFvYSeKYZazx7xM9wvxOMs7GcL9nezIQ" +
//			"bY9XHLhtCD4iZsJ/PwBdZLQqB1qwjdqlu5usa2TI3/cMtfxR15fkqEdF0HFzQ1MOrmZQAsUZENgT9iZ6TcPLP/" +
//			"mB48cMp3vUGh5x2482+r/OUCV34PDwrPXa7gg3zSYGiIaT3gc6pba8XCJtlYY/FhiWRIt5zcVaV1UjYb2qjQ==";
//		String message =
//			"LWiyPdxIETYwhvBlis12/fv2rsG0rsVQbjoerXlgEbromUu7/1IdCM8NpPOP0NfVkkXAdQbXzLWXhP5a0vP7l6" +
//			"EZNYg/oGEe61+XYNaVgqggp3tNKOYw4gIpZ9VoYoq15SVUIpagrOBjKD3DAH7J1c1C8oG7jGoGHWO1HnUHFqT1" +
//			"mk8ftSrAdhUdeQn7NlJIXjd7yOL+4ZV6FgZg0AyyF2lJfkYm8eLQk19IZfeTWW2pyjvYTPacRt6+lqGR1GOI5E" +
//			"6TnpA/qm+gl84Ts6Tc6oOJtlNTYg+INLHpmNpiyKwvu18Nl5cSrcLgwOYSAs2b3oSTbq7s0AsRLIYJ+izMHw==";
		String tokenMsg =
			"AY3huRanijW4z2BIu6H2wr22h1P2gulJK4I/dXdnrwv0aDAGen4eREEyWjZBtFkVVG/PjRlB3B3hInt9GkQLPV" +
			"ykzitm2eVDCxAemtWrBNbc79u1pgoYrsVmcHwgsKvg356WXbeZp93Y+CkupsHYV5gDC2mqs/AzLqviYft0OQsc" +
			"KCAV6Pn9zxm6mfoFv8b4FZVEx8RHPCB5bEPjYjL6gma+hzVYD07z0XTwih0olquu6ophY++UcyENBls5e4y36c" +
			"+2PSS3f3kjD0ywX7zHEaun1QA69WwAeIU5fcsaLQK+I86OlhejEk38Qm9vFDRO4EP7WNC9MQNE6Arxt00t/w==";
		
		String token = clientCipher.decrypt(tokenMsg);
		System.out.printf("Decrypted: %s\n", token);
		
		String cardMsg = "WWHGcEau+0EgMecD3H/51soJ5ibqKoCFtl8cEsgFfH0UkhxQnn3rcNhCVtsNBIvCBhV8o1FXRga/2nQ3S7d9xwE8katicdQKAhO3CcHzVg2C5mVYih5qJk4ZXa7/YpP+BiI7omIzQn1gkVSehY4XGSe18bhRpO2kTKFtilgJDMM23vT92or4JL5M04Ip2xuWPrTT8bY/WQqFIu0EuJkXiss49uaa7W/tx2m0kzE1Z8LlhIUF2hng/21sqQ10UjXpDqnD4nXzf5fBp4WOeOucHLIySGFwIgz6dozDgdkvqQ7VFSg/3ZsuhICc24WxCfwt/4F5Qyf9pd1ZrtDCVkC0yg==";
		String ownerMsg = "Lt87DUNVjYBM5AMgD76K6sZKRAfotgHqaoWctDbWpvylQ1E4Tv+ggz/vKgsLLq1XzhKpupaROAN78Ewoie3hX5lF9S7VRSO/CSZKRBhkZgjTjVRwRBarb3KX+qJ08IjzYQO/CZxW8xReATtcePzRiPDW5iVWOxn2hGBiEIwiO5O2jJnK1XEIchTIirFKEgy4+gTUFqaVTP8h/iiYQCBbPNsHXoWMCCVtDQTv3xPxdI7kmkz2n17kiqiGTSg4eD/TT8AVKpXFsBkBnQ22J1W4FAc3KIpqDqHI8QaBVvzgvfX/ni/mjxLF8r8rQDdNwT6UYuUWTAmakW6fSZhziPGcMA==";
		String expMsg = "FgU3egDAW9x4Ub7Cq3g1vNsY/l5N9m9Mojnpugn4CjcW/osLn4C5U2U8v8FzHyvWKW7ZGFIi7MPxcjhbeWJIe1T96yQ7fOr6ODYNdmYTB4QbmrAt/7KIOSu3UzSreIvtlc/xQYTJxp5FVcmgzsIo+7ky/KsqEmYZITJJIFIRh/UqJviWyvtZI7eqEuVCWzLp544BCI1oR0sDu+E0H+ctV9kQOxTBk8qWkNs22PL9vbV2oCxQXTUnTsbuBwYN3vqW2bDGXxcAIY6b4LXfN7vEHv6Om1fTwGC9Kh+uKnOeB9gAjFgnQ+xm58CmTdesgX3Z+Eixz0/7Z1uGDosdvD/m6A==";
		
		System.out.printf("card number:\n\t%s\nowner:\n\t%s\nexpiration date:\n\t%s\n",
				clientCipher.decrypt(cardMsg),
				clientCipher.decrypt(ownerMsg),
				clientCipher.decrypt(expMsg));
	}
	
	@Test
	public void encryptToServer() throws IntegrationCommonException{
		String message = "CUm/b7W35Iai+IaXEGeAGTz6eiU=-8Omghqde/lCnRUH3USVnyQ==";
		String encrypted = clientCipher.encrypt(message);
		
		System.out.printf("Encrypted: %s\n", encrypted);
	}

//	@Test
	public void testEncryptDecrypt() throws Exception{
		System.out.println("encrypting");
		
		String[] words = {
			"¡Hola Mundo!",
			"¡Hola Mundo!",
			"¡Hola Mudo!",
			"¡Hola Gente!",
			"Estoy viendo la tele",
			"How I met your mother",
			"1234123412341234",
			"yomero",
			"1227"
		};
		
		for(String word : words){

			String encrypted = clientCipher.encrypt(word);

			System.out.printf("encrypted: %s\n", encrypted);

			System.out.println("decrypting");

			String decrypted = serverCipher.decrypt(encrypted);

			System.out.printf("decrypted: %s\n\n", decrypted);
			
			Assert.assertEquals(word, decrypted);
		}
	}
	
}
