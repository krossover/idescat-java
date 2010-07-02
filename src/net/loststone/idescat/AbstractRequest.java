package net.loststone.idescat;

import java.io.IOException;

import net.loststone.idescat.common.Codificacio;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.common.Idioma;
import net.loststone.idescat.common.Versio;

import org.apache.commons.httpclient.HttpException;

/**
 * Classe base de les Requests. Cont� la URL de la petici� i permet configurar els 
 * par�metres comuns de tots els m�todes d'Idescat.
 */
public abstract class AbstractRequest {

	// URL d'idescat.
	protected String BASE = "http://api.idescat.cat/"; 
	// per defecte retorna el resultat en catala.
	protected String PARAM_LANG = "?lang=cat";
	// per defecte retorna el resultat en codificaci� UTF-8
	protected String PARAM_CODIFICACIO = "&enc=UTF-8";
	protected String AMPERSAND="&";
	
	/**
	 * Aquest objecte �s el que fa la petici� en si, ja sigui HTTP o 
	 * qualsevol altre.
	 */
	protected InnerRequest innerRequest; 
	
	protected String lang = "";
	protected String versio; 
	protected String format; 
	protected String codifcacio ="";
	
	public AbstractRequest(InnerRequest innerRequest) {
		this.innerRequest = innerRequest;
		this.format = ".xml";
		this.versio = "v1";
	}
	
	/**
	 * Configura la petici� perqu� retorni el resultat en l'idioma especificat.
	 * @param idioma Idioma en el que es retornar� el resultat.
	 */
	public void setLang(Idioma idioma) {
		this.lang =idioma.indicador();
	}
	
	/**
	 * Retorna el par�metre HTTP que s'utilitzar� per especificar l'idioma.
	 * @return
	 */
	public String getLang() {
		return  PARAM_LANG+this.lang;
	}
	
	/**
	 * Configura la versi� de l'API d'Idescat a utilitzar.
	 * @param versio
	 */
	public void setVersio(Versio versio) {
		this.versio = versio.nom();
	}
	
	/**
	 * Configura el format en el qual es retornaran les dades.
	 * @param format Format en el que es retornaran les dades. 
	 */
	public void setFormat(Format format) {
		this.format = format.nom();
	}
	
	/**
	 * Configura la codificaci� en que es retornaran les dades. 
	 * @param codificacio
	 */
	public void setCodificacio(Codificacio codificacio) {
		this.codifcacio = codificacio.nom();
	}
	
	/**
	 * Retorna el par�metre HTTP que s'utilitzar� per la codificaci� del resultat.
	 * @return
	 */
	public String getCodificacio() {
		return PARAM_CODIFICACIO + this.codifcacio;
	}
	
	/**
	 * M�tode que realitza la connexi� i consulta a la API.
	 * @throws HttpException En cas de que hi hagi algun error en la consulta HTTP.
	 * @throws IOException
	 */
	public void get() throws HttpException, IOException {
		this.innerRequest.get(this.getUrl());
	}
	
	/**
	 * Retorna el resultat de l'operaci� 'get' 
	 * @return El resultat, ja sigui en xml, json o qualsevol altre format sempre i quant
	 * s'hagi realitzat la consulta. null altrament. 
	 * @throws IOException
	 */
	public String getResult() throws IOException {
		return this.innerRequest.getResult();
	}
	
	/**
	 * Mostra si la consulta s'ha realitzat correctament.
	 * @return Cert si la consulta ha funcionat, fals altrament. 
	 */
	public boolean getSuccess() {
		return this.innerRequest.getSuccess();
	}
	
	/**
	 * M�tode que retorna la URL que s'utilitzar� per realitzar la consulta. 
	 * @return La url que s'utilitzar� per fer la consulta a l'API.
	 */
	public abstract String getUrl();
}
