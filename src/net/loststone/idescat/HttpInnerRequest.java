package net.loststone.idescat;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Classe que utilitza commons-httpclient per realitzar les peticions
 * HTTP a l'API.
 * @author marc
 */
public class HttpInnerRequest implements InnerRequest {
		
	HttpClient httpClient; 
	HttpMethod method; 
	int statusCode; 
	boolean executada; 
	
	/**
	 * Crea una HttpInnerRequest nova (no executada).
	 */
	public HttpInnerRequest() {
		httpClient = new HttpClient();
		executada = false; 
	}
	
	/**
	 * Invoca el m�tode HTTP Get a la URL que es pasa per par�metre.
	 * @param url URL a la que es far� el GET.
	 */
	public void get(String url) throws HttpException, IOException {
		method = new GetMethod(url);
		executada = true;
		httpClient.executeMethod(method);
	}

	/**
	 * Retorna el resultat de la petici� HTTP/GET.
	 * @return El resultat en la codificaci� i format especificat en cas de que la petici� 
	 * HTTP hagi funcionat, null altrament.
	 */
	public String getResult() throws IOException {
		if (executada)
			return new String(method.getResponseBody());
		else 
			return null;
	}

	/**
	 * Retorna cert sii la petici� s'ha executat en �xit. Fals altrament. 
	 */
	public boolean getSuccess() {
		if (executada)
			return (statusCode == HttpStatus.SC_OK);
		else
			return false; 
	}

}
