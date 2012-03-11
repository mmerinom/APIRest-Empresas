package apirest.mmm.model;

import java.util.Arrays;

/** Esta clase representa los datos de contacto de una empresa */
public class InfoCompany {
	
	/** Identificador interno de la empresa */	
	protected int id = 0;
	/** Nombre de la empresa */	
	protected String name = null;
	/** Imagen logotipo de la empresa */
	protected String logo = null;
	/** Sitio web de la empresa */
	protected String website = null;
	/** Direcciones asociadas a la empresa */
	protected String[] addressLines = null;
	/** C�digo postal de la empresa */
	protected int zipCode = 0;
	/** Ciudad de la empresa */
	protected String city  = null;
	/** Pa�s de la empresa */
	protected String country = null;
	/** Tel�fonos internacionales asociados a la empresa */
	protected Phone[] phones = null;
	
	/** Crea el objeto datos de contacto de una empresa
	 */
	public InfoCompany() {
		super();
	}		
	/** Genera una representaci�n textual del objeto InfoCompany 
	 * @return Texto con la representaci�n del objeto  
	 */
	@Override
	public String toString() {
		return String
				.format("identificador=%s, nombre=%s, logo=%s, website=%s, direcciones=%s, c�digo postal=%s, ciudad=%s, pais=%s, tel�fonos=%s",
						id, name, logo, website, Arrays.toString(addressLines),zipCode, city, country, Arrays.toString(phones));
	}	
	/** Obtiene el identificador interno de la empresa
	 * @return Entero con el identificador
	 */
	public int getId() {
		return id;
	}	
	/** Obtiene el nombre de la empresa
	 * @return Texto con el nombre
	 */	
	public String getName() {
		return name;
	}	
	/** Obtiene la imagen logotipo de la empresa
	 * @return Texto con la URL
	 */	
	public String getLogo() {
		return logo;
	}	
	/** Obtiene el sitio web de la empresa 
	 * @return Texto con la URL
	 */
	public String getWebsite() {
		return website;
	}
	/** Obtiene las direcciones asociadas a la empresa
	 *  Estas direcciones no incluyen c�digo postal, ciudad ni pa�s. Los cuales son �nicos para la empresa
	 * @return Un array de Strings, uno por cada direcci�n asociada
	 */	
	public String[] getAddressLines() {
		return addressLines;
	}
	/**Obtiene el c�digo postal asociado a la empresa 
	 * @return Entero con el c�digo postal 
	 */	
	public int getZipCode() {
		return zipCode;
	}
	/**Obtiene la ciudad asociada a la empresa
	 * @return Texto con el nombre de la ciudad  
	 */			
	public String getCity() {
		return city;
	}
	/**Obtiene el pa�s asociado a la empresa
	 * @return Texto con el nombre de pa�s
	 */		
	public String getCountry() {
		return country;
	}
	/**Obtiene los tel�fonos internacionales asociados a la empresa
	 * @return Un array de {@link Phone} , uno por cada tel�fono asociado
	 */
	public Phone[] getPhones() {
		return phones;
	}

	

	
}
