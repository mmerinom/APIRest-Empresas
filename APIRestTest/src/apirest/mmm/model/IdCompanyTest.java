
package apirest.mmm.model;
import junit.framework.TestCase;

/** Prueba de acceso a los datos en la clase apirest.mmm.model.IdCompany;
 */
public class IdCompanyTest extends TestCase {

	/** Identificador interno de la empresa */
	protected int id = 0;
	/** Nombre de la empresa */	
	protected String name = null;

	/** Constructor que establece el test a probar 
	 * @param testname nombre dado al método de prueba
	 */
	public IdCompanyTest(String testName){
		super(testName);
	}

    /**
     * Acciones a realizar antes de cada Test
     */  	
	@Override
	protected void setUp() throws Exception
	{   
		id = 1;
		name = "Nomasystems,S.L.";
		super.setUp();
	}

    /**
     * Acciones a realizat después de cada Test
     */  		
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

    /**
     * Comprueba el valor y formato devuelto por el el método toStringBasico()
     */  	
	public void testToStringBasico()
	{
		IdCompany idcompany = new IdCompany(1,"Nomasystems,S.L.");
		assertEquals(idcompany.toStringBasico(), "id=1, name=Nomasystems,S.L.");
	}
}
