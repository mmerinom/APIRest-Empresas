package apirest.mmm.activitys.test;


import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import apirest.mmm.activitys.ActivityIdCompany;
import apirest.mmm.model.IdCompany;

/**
 * Pruebas sobre ActivityIdCompany.  Actividad de entrada en la aplicación APIRest, 
 * donde se listan las empresas de las que se mantienen datos de contacto.
 * Incluye:
 *   - test de condiciones iniciales 
 *   - test UI 
 */
public class ActivityIdCompanyTest extends ActivityInstrumentationTestCase2<ActivityIdCompany> {
    
	// Variables para los objetos de la actividad a probar
    private ActivityIdCompany mActivity;
    private ListView mlstVEmpresas;
    private ArrayAdapter<IdCompany> mAdapter;
    private IdCompany mIdCompany;
    private int mPosicion;
    
    
	/**
	 * Constructor de la clase de prueba.
	 * LLama al super constructor con el paquete y la clase a probar
	 */
	public ActivityIdCompanyTest() {
		super("apirest.mmm.activitys", ActivityIdCompany.class);
	}

    /**
     * Establece el entorno de prueba antes de cada test
     */    
    @Override
    protected void setUp() throws Exception {
         
         //Llamada al superconstructor, requerida por la JUnit
        super.setUp();
        
         // Permite el envío de eventos claves deshabilitando el modo de toque
         // Debe realizarse antes de iniciar la actividad, getActivity() 
        setActivityInitialTouchMode(false);
        
        // Referencia a la actividad principal de la aplicación a probar. También inicia la actividad         
        mActivity = getActivity();
        
        // Referencia al componente visual de la aplicación a probar        
        mlstVEmpresas = (ListView) mActivity.findViewById(apirest.mmm.activitys.R.id.lstVEmpresas);
        
        // Referencia al adaptatador del componente visual a probar
        mAdapter = (ArrayAdapter<IdCompany>) mlstVEmpresas.getAdapter();
        
        // Ahora la clase de prueba puede usar instrumentación sobre la actividad a probar.
    }
    
    /** Test para los valores iniciales de los objetos clave, se verifica que los componentes
     *  inicializados no sean nulos
	 */
    public void testPrecondiciones() {

        // Comprobar que la referencia a la lista no es nula
    	assertTrue(mlstVEmpresas != null);
    	
        // Comprobar que el oyente del evento no es nulo
    	assertTrue(mlstVEmpresas.getOnItemClickListener() != null);
    	
        // Comprobar que el adaptador de la lista se ha cargado correctamente
        assertTrue(mAdapter != null);        
    }
    
    /** Test para el IU de la actividad. Envía eventos de selección a la lista, y luego comprueba 
     *  si el estado del componente es compatible con la selección realizada. 
     */    
    public void testUI() {       
    	        
        /* Como se interactúa con la vista, se debe ejecutar con un UI Thread    	
         *  runOnUiThread(), con anómimo Runnable para declarar las sentencias a realizar
         */         
        mActivity.runOnUiThread(
            new Runnable() {
                public void run() {
                	mlstVEmpresas.requestFocus();
                	mlstVEmpresas.setSelection(0);                   
                }
            }
        );
        
        // Envía un evento pulsando una tecla
        this.sendKeys(KeyEvent.ACTION_DOWN);

        // Obtiene la posición del item seleccionado del componente 
        mPosicion = mlstVEmpresas.getSelectedItemPosition();        
        
        // Comprueba que la posición seleccionada es la esperada
        assertEquals(mPosicion,0);        
       
        // Obtiene la posición del elemento seleccionado      
        mIdCompany = (IdCompany) mlstVEmpresas.getItemAtPosition(mPosicion);                        

        //Comprueba que elemento obtenido no sea nulo
        assertTrue(mIdCompany != null);
        
        // Comprueba que el elemento seleccionado es el esperado
		assertEquals(mIdCompany.getId(), 1);		       
    }
    

    
}
