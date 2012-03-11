package apirest.mmm.model;

/** Esta clase representa un tel�fono con prefijo internacional */
public class Phone {
	
	/** Prefijo telef�nico internacional*/	
	protected String countryCode;
	
	/** N�mero de tel�fono */
	protected String subscriberNumber;
	
	/** Crea un tel�fono internacional */	
	public Phone() {
		countryCode = null;
		subscriberNumber = null;
	}	
	
	/** Genera una representaci�n textual del objeto Phone
	 * @return Texto con la representaci�n del objeto  
	 */
	@Override
	public String toString() {
		return String.format("contryCode %s, subscriberNumber %s", countryCode, subscriberNumber);
	}
	
	/** Obtiene el prefijo telef�nico internacional
	 * @return Texto con el c�digo de pa�s para tel�fonos
	 */
	public String getCountryCode() {
		return countryCode;
	}
	
	/** Obtiene el n�mero de tel�fono 
	 * @return Texto con el n�mero de tel�fono
	 */
	public String getSubscriberNumber() {
		return subscriberNumber;
	}		
}
