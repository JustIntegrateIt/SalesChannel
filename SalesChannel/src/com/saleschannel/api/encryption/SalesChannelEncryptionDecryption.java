package com.saleschannel.api.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.saleschannel.api.utility.SalesChannelPropertyLoader;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/**
 * The Class SalesChannelEncryptDecrypt.
 */
public class SalesChannelEncryptionDecryption {
	
	/** The loggers. */
	private final static Logger LOGGERS = Logger.getLogger(SalesChannelEncryptionDecryption.class);
	
	/** The initialization vector. */
	private final String initializationVector;
	
	/** The crypt algorithm. */
	private final String cryptAlgorithm;
	
	/** The crypt mode. */
	private final String cryptMode;
	
	/** The crypt padding. */
	private final String cryptPadding;
	
	/** The hashing algorithm. */
	private final String hashingAlgorithm;
	
	/** The hash size. */
	private final Integer hashSize;
	
	/** The secret key. */
	private SecretKeySpec secretKey;
	
	/** The decrypted string. */
	private String decryptedString;
	
	/** The encrypted string. */
	private String encryptedString;
	
	/** The hashed string. */
	private String hashedString;
	
	/** The iv parameter spec. */
	private IvParameterSpec ivParameterSpec;
	
	/** The properties. */
	final Properties properties = SalesChannelPropertyLoader.salesChannelProperties;
	
	/**
	 * Instantiates a new MarketPlace encrypt decrypt.
	 *
	 * @param passPhrase the pass phrase
	 */
	public SalesChannelEncryptionDecryption(final String passPhrase) {
		initializationVector = properties.getProperty("ivParameterSpec");
		cryptAlgorithm = properties.getProperty("cryptAlgorithm");
		cryptMode = properties.getProperty("cryptMode");
		cryptPadding = properties.getProperty("cryptPadding");
		hashingAlgorithm = properties.getProperty("hashingAlgorithm");
		hashSize = Integer.parseInt(properties.getProperty("hashSize"));
		try {
			final byte[] iv = initializationVector.getBytes();
			ivParameterSpec = new IvParameterSpec(iv);
			byte[] sharedSecret = passPhrase.getBytes("UTF-8");
			sharedSecret = MessageDigest.getInstance(hashingAlgorithm).digest(sharedSecret);
			sharedSecret = Arrays.copyOf(sharedSecret, 16); /* use only first 128 bit */
			secretKey = new SecretKeySpec(sharedSecret, cryptAlgorithm);
		} catch (final NoSuchAlgorithmException e) {
			LOGGERS.error("No Such Encryption Algorithm Exists.");
		} catch (final UnsupportedEncodingException e) {
			LOGGERS.error("Unsupported Encoding Exception.");
		}
	}
	
	/**
	 * Encrypt.
	 *
	 * @param strToEncrypt the str to encrypt
	 * @return the string
	 */
	public String encrypt(final String strToEncrypt) {
		if (strToEncrypt != null && !("").equals(strToEncrypt)) {
			try {
				final Cipher cipher = Cipher.getInstance(cryptAlgorithm + "/" + cryptMode + "/" + cryptPadding);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
				setHashedString(strToEncrypt);
				setEncryptedString(Base64.encodeBase64String(cipher.doFinal((strToEncrypt).getBytes("UTF-8"))));
			} catch (final Exception e) {
				LOGGERS.error("Error while encrypting: " + e.toString());
			}
			return getEncryptedString();
		}
		return null;
	}
	
	/**
	 * Gets the encrypted string.
	 *
	 * @return the encrypted string
	 */
	public String getEncryptedString() {
		return encryptedString;
	}
	
	/**
	 * Sets the encrypted string.
	 *
	 * @param encryptedString the new encrypted string
	 */
	public void setEncryptedString(final String encryptedString) {
		this.encryptedString = encryptedString;
	}
	
	/**
	 * Decrypt.
	 *
	 * @param strToDecrypt the str to decrypt
	 * @return the string
	 */
	public String decrypt(final String strToDecrypt) {
		if (strToDecrypt != null && !("").equals(strToDecrypt)) {
			try {
				final Cipher cipher = Cipher.getInstance(cryptAlgorithm + "/" + cryptMode + "/" + cryptPadding);
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
				setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))), false);
			} catch (final Exception e) {
				LOGGERS.error("Error while decrypting: " + e.toString());
			}
			return getDecryptedString();
		}
		return null;
	}
	
	/**
	 * Gets the decrypted string.
	 *
	 * @return the decrypted string
	 */
	public String getDecryptedString() {
		return decryptedString;
	}
	
	/**
	 * Sets the decrypted string.
	 *
	 * @param decryptedString the decrypted string
	 * @param isWithOutHash the is with out hash
	 */
	public void setDecryptedString(final String decryptedString, final boolean isWithOutHash) {
		if (isWithOutHash) {
			this.decryptedString = decryptedString.substring(0, decryptedString.length() - hashSize);
		} else {
			this.decryptedString = decryptedString;
		}
	}
	
	/**
	 * Gets the hashed string.
	 *
	 * @return the hashed string
	 */
	public String getHashedString() {
		return hashedString;
	}
	
	/**
	 * Sets the hashed string.
	 *
	 * @param hashedString the new hashed string
	 */
	public void setHashedString(final String hashedString) {
		try {
			this.hashedString = new String(Hex.encodeHex(MessageDigest.getInstance(hashingAlgorithm).digest(hashedString.getBytes())));
		} catch (final NoSuchAlgorithmException e) {
			LOGGERS.error("No Such Encryption Algorithm Exists.");
		}
	}
	
	/**
	 * Encrypt with hash.
	 *
	 * @param str the str
	 * @return the string
	 */
	public String encryptWithHash(final String str) {
		if (str != null && !("").equals(str)) {
			try {
				final Cipher cipher = Cipher.getInstance(cryptAlgorithm + "/" + cryptMode + "/" + cryptPadding);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
				setHashedString(str);
				setEncryptedString(Base64.encodeBase64String(cipher.doFinal((str + getHashedString()).getBytes("UTF-8"))));
			} catch (final Exception e) {
				LOGGERS.error("Error while encrypting: " + e.toString());
			}
			return getEncryptedString();
		}
		return null;
	}
	
	/**
	 * Decrypt with out hash.
	 *
	 * @param str the str
	 * @return the string
	 */
	public String decryptWithOutHash(final String str) {
		if (str != null && !("").equals(str)) {
			try {
				final Cipher cipher = Cipher.getInstance(cryptAlgorithm + "/" + cryptMode + "/" + cryptPadding);
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
				setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(str))), true);
			} catch (final Exception e) {
				LOGGERS.error("Error while decrypting: " + e.toString());
			}
			return getDecryptedString();
		}
		return null;
	}
	
	/**
	 * Checks if is authenticated data.
	 *
	 * @param data the data
	 * @return true, if is authenticated data
	 */
	public boolean isAuthenticatedData(final String data) {
		final String decryptedStr = decrypt(data);
		final String hashData = decryptedStr.substring(decryptedStr.length() - hashSize, decryptedStr.length());
		final String rawData = decryptWithOutHash(data);
		setHashedString(rawData);
		if (hashData.equals(getHashedString())) {
			return true;
		}
		return false;
	}
	
}
