package apirest.mmm.model;

/** Esta clase representa un teléfono con prefijo internacional */
public class Phone {
	
	/** Prefijo telefónico internacional*/	
	protected String countryCode;
	
	/** Número de teléfono */
	protected String subscriberNumber;
	
	/** Crea un teléfono internacional */	
	public Phone() {
		countryCode = null;
		subscriberNumber = null;
	}	
	
	/** Genera una representación textual del objeto Phone
	 * @return Texto con la representación del objeto  
	 */
	@Override
	public String toString() {
		return String.format("contryCode %s, subscriberNumber %s", countryCode, subscriberNumber);
	}
	
	/** Obtiene el prefijo telefónico internacional
	 * @return Texto con el código de país para teléfonos
	 */
	public String getCountryCode() {
		return countryCode;
	}
	
	/** Obtiene el número de teléfono 
	 * @return Texto con el número de teléfono
	 */
	public String getSubscriberNumber() {
		return subscriberNumber;
	}		
}
