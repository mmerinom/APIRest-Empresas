package apirest.mmm.conexiones.test;

import java.util.concurrent.ExecutionException;

import apirest.mmm.conexiones.ServicioJson;
import junit.framework.TestCase;

/** Pruebas para el servicio de objetos Json
 */
public class ServicioJsonTest extends TestCase {
	
	/** Referencia a un objeto ServicioJson */
	protected ServicioJson servicio;
	
	/**
	 * Constructor que establece el test a probar
	 * @param testname nombre dado al método de prueba
	 */	
	public ServicioJsonTest(String testName)
	{
		super(testName);
	}	

    /**
     * Acciones a realizar antes de cada Test
     */   
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

    /**
     * Acciones a realizar después de cada Test
     */    
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
    /**
     * Comprueba el valor y formato devuelto para un petición Rest sobre la identificación de todas las empresas disponibles.  
     */  	
	public void testPeticionRestIdEmpresa()
	{
		servicio = new ServicioJson("http://82.194.75.216:8888/SoftwareDeveloper/{version}/company");
		servicio.execute("{version}","mercury.0");
		try {
			String objetosJson = servicio.get();
			
			//Se comprueba si hay respuesta a la petición
			assertTrue(objetosJson != null);

			//Se comprueba si la respuesta es la esperada
			assertEquals(objetosJson,"[{\"id\":1,\"name\":\"Nomasystems, S.L.\"},{\"id\":2,\"name\":\"Tutuah\"}]");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}		
	}	
    /**
     * Comprueba el valor y formato devuelto para un petición Rest sobre los datos de contacto de una empresa
     */  	
	public void testPeticionRestEmpresa()
	{
		servicio = new ServicioJson("http://82.194.75.216:8888/SoftwareDeveloper/{version}/company/{companyId}");
		servicio.execute("{version}","mercury.0","{companyId}",Integer.toString(1));
		try {
			String objetosJson = servicio.get();
			
			//Se comprueba si hay respuesta a la petición
			assertTrue(objetosJson != null);

			//Se comprueba si la respuesta es la esperada
			assertEquals(objetosJson, "{\"id\":1,\"name\":\"Nomasystems, S.L.\",\"logo\":\"http://www.nomasystems.es/logo.png\"," + 
					"\"website\":\"http://www.nomasystems.es\",\"addressLines\":[\"C/ Juan Florez 8, quinto\"]," + 
					"\"zipCode\":15004,\"city\":\"A Coruna\",\"country\":\"Spain\"," + 
					"\"phones\":[{\"countryCode\":\"+34\",\"subscriberNumber\":\"981123456\"}," + 
					"{\"countryCode\":\"+34\",\"subscriberNumber\":\"902123456\"}]}");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}	
	
}
