package apirest.mmm.conexiones.test;

import java.util.concurrent.ExecutionException;

import android.graphics.Bitmap;
import apirest.mmm.conexiones.ServicioImagenUrl;
import junit.framework.TestCase;

/** Prueba el servicio de descarga de imagen */

public class ServicioImagenUrlTest extends TestCase {

	/** Referencia a un objeto ServicioImagenUrl */
	protected ServicioImagenUrl servicioImagenUrl;	
	
	/** Constructor que establece el test a probar 
	 * @param testname nombre dado al método de prueba
	 */
	public ServicioImagenUrlTest(String name) {
		super(name);
	}

    /**
     * Acciones a realizar antes de cada Test
     */   
	@Override
	protected void setUp() throws Exception {
		servicioImagenUrl = new ServicioImagenUrl();
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
     * Comprueba si hay respuesta a la petición de descarga para el ServicioImagenUrl   
     */  	
	public void testPeticionUrlImagen()
	{
		servicioImagenUrl.execute("http://www.nomasystems.es/logo.png");
		
			//Se comprueba si hay respuesta a la petición
			try {
				assertTrue((Bitmap) servicioImagenUrl.get() != null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
	}	
}
