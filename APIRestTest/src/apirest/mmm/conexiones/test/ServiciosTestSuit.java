
package apirest.mmm.conexiones.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/** Conjunto de pruebas para servicios con conexiones
 */
public class ServiciosTestSuit extends TestCase {

	/** Constructor que establece el conjunto de test a probar 
	 * @param testname nombre dado al conjunto de pruebas
	 */
	public ServiciosTestSuit(String testname) {
		super(testname);
	}
    /**
     * Acciones a realizar antes de cada Test
     */   	
    @Override
    protected void setUp() throws Exception
      {
        super.setUp();
      }
    /**
     * Acciones a realizar después de cada Test
     */    
    @Override
    protected void tearDown() throws Exception
      {
        super.tearDown();
      }
  	
    /**
     * Agrupa los test asociados a las clases de conexión  
     */     
    public static Test suite()
    {
      TestSuite suite = new TestSuite("TestSuitConexiones");
      suite.addTest(new ServicioImagenUrlTest("testPeticionUrlImagen"));
      suite.addTest(new ServicioJsonTest("testPeticionRestIdEmpresa"));
      suite.addTest(new ServicioJsonTest("testPeticionRestEmpresa"));


      return suite;
    }
    
    
    
}
