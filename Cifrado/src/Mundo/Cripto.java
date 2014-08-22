package Mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Cripto {

	public String clave;
	public String llaveGenerada;
	public String rutaLectura;
	public String rutaEscritura;
	public String hash;
	public String hash2;




	public Cripto() {

		this.clave="";
		this.llaveGenerada="";
		this.rutaEscritura="";
		this.rutaLectura="";
		this.hash="";
		this.hash2="";
	}


	public void generarClaves(char[] ingresadaPorUsuario, String rutaLectura, String rutaEscritura){

		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ingresadaPorUsuario.length; i++) {
				sb.append(ingresadaPorUsuario[i]);
			}
			this.clave= sb.toString();
			llaveGenerada = generarLLave128();
			this.rutaEscritura=rutaEscritura;
			this.rutaLectura=rutaLectura;
			String archivo = leerArchivo(rutaLectura);
			System.out.println(archivo);
			//String cifrado =cifradoAES(archivo);
			String cifrado = cifradoDES(archivo);
			System.out.println(this.rutaEscritura);
			this.hash= hash(archivo.getBytes());

			escribirArchivo(this.rutaEscritura, cifrado);


		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	public void desencriptar(String rutaLectura, String rutaEscritura){
		try {
			this.rutaEscritura=rutaEscritura;
			this.rutaLectura=rutaLectura;
			String encriptado = leerArchivo(rutaLectura);
			System.out.println(encriptado);
			//String descifrado =descifradoAES(encriptado);
			String descifrado = descifradoDES(encriptado);
			System.out.println(this.rutaEscritura);
			this.hash2= hash(descifrado.getBytes());


		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	


	private void escribirArchivo(String rutaEscritur, String cifrado) {

		try{

			File archivoEncriptado;
			archivoEncriptado = new File(rutaEscritur);
			FileWriter escritorFichero = new FileWriter(archivoEncriptado);
			BufferedWriter escritorBuffer = new BufferedWriter(
					escritorFichero);
			PrintWriter imprimir = new PrintWriter(escritorBuffer);

			imprimir.write(cifrado);
			imprimir.close();
		} catch(Exception e){
			e.printStackTrace();
		}


	}


	public String leerArchivo(String ruta) throws IOException{

		FileInputStream archivo = new FileInputStream(ruta);
		DataInputStream entrada = new DataInputStream(archivo);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				entrada));
		String lectura;
		String infoLectura="";

		while ((lectura = buffer.readLine()) != null) {
			infoLectura +=lectura;
		}
		buffer.close();

		return infoLectura;

	}





	public String generarLLave128(){

		String llave = "";

		try {
			MessageDigest msg = MessageDigest.getInstance("MD5");
			msg.update(clave.getBytes(), 0, clave.length());
			llave = new BigInteger(1, msg.digest()).toString(16);
			llave+="A";
			System.out.println("MD5: " + llave);
		} catch (NoSuchAlgorithmException ex) {

		}

		return llave;
	}

	public static byte[] hexToBytes(String str) {
		if (str==null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i=0; i<len; i++) {
				buffer[i] = (byte) Integer.parseInt(
						str.substring(i*2,i*2+2),16);
			}
			return buffer;
		}

	}
	public static String bytesToHex(byte[] data) {
		if (data==null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i=0; i<len; i++) {
				if ((data[i]&0xFF)<16) str = str + "0" 
				+ java.lang.Integer.toHexString(data[i]&0xFF);
				else str = str
				+ java.lang.Integer.toHexString(data[i]&0xFF);
			}
			return str.toUpperCase();
		}
	}  

	public String hash(byte[] msg) {

		try {

			/* Se crea el objeto MessageDigest instanciándolo con el tipo de algoritmo que se usará en este caso SHA1 */
			MessageDigest algorithm = MessageDigest.getInstance("SHA1");

			/* se limpia el objeto creado por medio del método reset() */
			algorithm.reset();

			/* se carga por medio del método update() el arreglo de bytes al cual se desea obtener la función hash */
			algorithm.update(msg);

			/* mediante el método digest() se obtiene un nuevo arreglo de bytes que contiene el hash */
			byte messageDigest[] = algorithm.digest();

			/*se convierte el arreglo de bytes a hexadecimal y se retorna como una cadena */
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException i) {
			return null;
		}
	}

	public String cifradoAES(String archivo) throws Exception{

		byte[] theKey = null;
		byte[] Mensaje = null; 
		byte[] cifrado = null; 

		try {

			theKey = hexToBytes(llaveGenerada);
			Mensaje = hexToBytes("6bc1bee22e409f96e93d7e117393172a");

			Cipher cf;
			cf = Cipher.getInstance("AES");

			SecretKeySpec skeyspec = new SecretKeySpec(theKey, "AES");
			cf.init(Cipher.ENCRYPT_MODE, skeyspec);

			cifrado = cf.doFinal(Mensaje);

			System.out.println("Key     : "+bytesToHex(theKey));
			System.out.println("Message : "+bytesToHex(Mensaje));
			System.out.println("Cipher  : "+bytesToHex(cifrado).substring(0,32));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytesToHex(cifrado);
	}

	private String descifradoAES(String archivo) {

		return null;
	}
	
	public String descifradoDES(String encriptado) throws Exception{

		byte[] theKey = null;
		byte[] MensajeEncriptado = null;  

		theKey = hexToBytes(asciiToHex(llaveGenerada));
		MensajeEncriptado = hexToBytes("6bc1bee22e409f96e93d7e117393172a");
		MensajeEncriptado = hexToBytes(encriptado);

		KeySpec ks = new DESKeySpec(theKey);
		SecretKeyFactory kf 
		= SecretKeyFactory.getInstance("DES");
		SecretKey ky = kf.generateSecret(ks);
		Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
		cf.init(Cipher.DECRYPT_MODE,ky);
		byte[] theCph = cf.doFinal(MensajeEncriptado);

		return bytesToHex(theCph);
	}

	public String cifradoDES(String archivo) throws Exception{

		byte[] theKey = null;
		byte[] mensaje = null;  
		System.out.println(asciiToHex(llaveGenerada));

		theKey = hexToBytes(asciiToHex(llaveGenerada));
		mensaje = hexToBytes(archivo);

		KeySpec ks = new DESKeySpec(theKey);
		SecretKeyFactory kf 
		= SecretKeyFactory.getInstance("DES");
		SecretKey ky = kf.generateSecret(ks);
		Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
		cf.init(Cipher.ENCRYPT_MODE,ky);
		byte[] theCph = cf.doFinal(mensaje);

		return bytesToHex(theCph);
	}
	
	public static String asciiToHex(String ascii){
        StringBuilder hex = new StringBuilder();
        
        for (int i=0; i < ascii.length(); i++) {
            hex.append(Integer.toHexString(ascii.charAt(i)));
        }       
        return hex.toString();
    } 




}
