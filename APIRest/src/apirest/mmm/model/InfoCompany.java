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
	/** Código postal de la empresa */
	protected int zipCode = 0;
	/** Ciudad de la empresa */
	protected String city  = null;
	/** País de la empresa */
	protected String country = null;
	/** Teléfonos internacionales asociados a la empresa */
	protected Phone[] phones = null;
	
	/** Crea el objeto datos de contacto de una empresa
	 */
	public InfoCompany() {
		super();
	}		
	/** Genera una representación textual del objeto InfoCompany 
	 * @return Texto con la representación del objeto  
	 */
	@Override
	public String toString() {
		return String
				.format("identificador=%s, nombre=%s, logo=%s, website=%s, direcciones=%s, código postal=%s, ciudad=%s, pais=%s, teléfonos=%s",
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
	 *  Estas direcciones no incluyen código postal, ciudad ni país. Los cuales son únicos para la empresa
	 * @return Un array de Strings, uno por cada dirección asociada
	 */	
	public String[] getAddressLines() {
		return addressLines;
	}
	/**Obtiene el código postal asociado a la empresa 
	 * @return Entero con el código postal 
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
	/**Obtiene el país asociado a la empresa
	 * @return Texto con el nombre de país
	 */		
	public String getCountry() {
		return country;
	}
	/**Obtiene los teléfonos internacionales asociados a la empresa
	 * @return Un array de {@link Phone} , uno por cada teléfono asociado
	 */
	public Phone[] getPhones() {
		return phones;
	}

	

	
}
