package apirest.mmm.model;

/** Esta clase representa la identificaci�n o cabecera de una empresa */
public class IdCompany {
	
	/** Identificador interno de la empresa */
	protected int id = 0;
	/** Nombre de la empresa */	
	protected String name = null;
	
	/** Crea el objeto identificaci�n de una empresa
	 */
	public IdCompany() {
		super();
	}
	/** Crea el objeto identificaci�n de una empresa, seg�n los par�metros
	 */
	public IdCompany(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}		
	/** Genera la identificaci�n de emprea visible para el usuario 
	 * @return Texto con el nombre de la empresa  
	 */		
	@Override
	public String toString() {
		return name;
	}
	/** Genera una representaci�n textual del objeto IdCompany 
	 * @return Texto con la representaci�n del objeto  
	 */
	public String toStringBasico() {
		return String.format("id=%s, name=%s", id, name);
	}
	/** Obtiene el identificador interno de la empresa
	 * @return Entero con el identificador
	 */
	public int getId(){
		return id;
	}
	/** Obtiene el nombre de la empresa
	 * @return Texto con el nombre de la empresa
	 */
	public String getName(){
		return name;
	}


}
