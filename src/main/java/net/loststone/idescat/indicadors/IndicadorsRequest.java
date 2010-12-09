package net.loststone.idescat.indicadors;

import java.util.ArrayList;
import java.util.List;

import net.loststone.idescat.AbstractRequest;
import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.InvalidUrlException;
import net.loststone.idescat.common.Format;

/**
 * Petici� a l'API d'indicadors: 
 * http://www.idescat.cat/api/indicadors/
 * 
 * Aquesta classe permet gestionar els par�metres que s'utilitzaran a l'hora de fer la petici�
 * espec�fica a l'API d'indicadors. 
 * 
 * @author marc
 *
 */
public class IndicadorsRequest extends AbstractRequest {

	public enum operacio { nodes, dades }
	
	// el servei �s indicadors.
	private final String servei = "indicadors";

	// lista d'indicadors a mostrar.
	private List<Integer> indicadors;
	// n�mero m�xim d'indicadors que volem.
	private int max=6; 
	// n�mero m�nim d'indicadors que volem.
	private int min=6; 
	// temps transcorregut des de la seva publicaci�. 
	private int tt=0; 
	private operacio op; 
	
	
	public IndicadorsRequest(InnerRequest innerRequest) {
		super(innerRequest);
		indicadors = new ArrayList<Integer>();
	}

	@Override
	public String getUrl() throws InvalidUrlException {
		StringBuffer resultat = new StringBuffer();
		
		// crear la petici� base amb els par�metres comuns.
		this.inicialitzarParametres(resultat);
		
		// par�metres espec�fics del servei 'Indicadors'
		if (!this.getIndicadors().isEmpty()) {
			resultat.append(this.AMPERSAND);
			boolean first = true; 
			for (Integer _indicador : this.getIndicadors()) {
				if (!first) {
					resultat.append(",");
				}
				resultat.append(_indicador);
				first = false;
			}
		}
		
		// en cas de que sigui l'operacio dades mirem si hi ha els 
		// demes par�metres.
		if (this.op == operacio.dades) {
			resultat.append(this.AMPERSAND);
			resultat.append(getMax());
			resultat.append(this.AMPERSAND);
			resultat.append(getMin());
			resultat.append(this.AMPERSAND);
			resultat.append(getTt());
		}
		
		return resultat.toString();
	}

	/**
	 * Retorna la llista d'indicadors a utilitzar.
	 * @return
	 */
	public List<Integer> getIndicadors() {
		return indicadors;
	}

	/**
	 * Inserta una llista d'indicarods a mostrar.
	 * @param indicadors
	 */
	public void setIndicadors(List<Integer> indicadors) {
		this.indicadors = indicadors;
	}
	
	/**
	 * Afegeix un indicador a la llista d'indicadors que s'utilitzar� per par�metre.
	 * @param indicador
	 */
	public void addIndicador(Integer indicador) {
		this.indicadors.add(indicador);
	}

	/**
	 * Retorna en n�mero m�xim d'indicadors que ens retornar� l'API.
	 * @return
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Posa en n�mero m�xim d'indicadors que ens retornar� l'API.
	 * @param max
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * Retorna el n�mero m�nim d'indicadors que ens retornar� l'API.
	 * @return
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Posa el n�mero m�nim d'indicadors que ens retornar� l'API.
	 * @param min
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Retorna el par�metre que especifica el temps transcorregut des de la publicaci�
	 * de les dades.
	 * @return
	 */
	public int getTt() {
		return tt;
	}

	/**
	 * Especifica el par�metre qe indica el temps transcorregut des de la publicaci� de 
	 * les dades.
	 * @param tt
	 */
	public void setTt(int tt) {
		this.tt = tt;
	}
	
	
	public void setOperacio(operacio op) {
		this.op = op; 
	}
	
	public operacio getOperacio() {
		return op; 
	}

	@Override
	protected boolean formatPermes(Format format) {
		if ((format == Format.JSON) || (format == Format.PHP) || (format == Format.XML))
			return true;
		else 
			return false;
	}

	@Override
	protected String getOperacioString() {
		if (this.op != null)
			return this.op.toString();
		else 
			return null;
	}

	@Override
	protected String getServei() {
		return this.servei;
	}
	
}
