package net.loststone.idescat;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

/**
 * Aquesta interficie �s la que han d'implementar les classes que fan el request
 * a la API en si. Han de gestionar els errors de connexi� i altres per separar-ho
 * del domini. 
 * @author lant
 *
 */
public interface InnerRequest {
	
	/**
	 * M�tode que realitza la connexi� al servidor i invoca el m�tode GET donada una url.
	 * @param url URL a la qual es far� l'HTTP GET
	 * @throws HttpException
	 * @throws IOException
	 */
	void get(String url) throws HttpException, IOException;
	
	/**
	 * M�tode que retorna el resultat del m�tode GET.
	 * @return El resultat en format String.
	 * @throws IOException
	 */
	String getResult() throws IOException;
	
	/**
	 * M�tode que ens diu si la connexi� s'ha realizat correctament.
	 * @return cert en cas de que tot hagi anat b�, fals altrament.
	 */
	boolean getSuccess();
}

