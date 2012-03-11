package apirest.mmm.activitys.test;


import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.TextView;
import apirest.mmm.activitys.ActivityInfoCompany;
import apirest.mmm.activitys.R;

/**
 * Pruebas sobre ActivityInfoCompany. Actividad secundaria en la aplicación APIRest, 
 * donde se listan los datos de contacto de la empresa seleccionada en la actividad principal.
 * Incluye:
 *   - test condiciones iniciales 
 *   - test de control de estado ante cambios en el ciclo de vida de la actividad.
 */
public class ActivityInfoCompanyTest extends ActivityInstrumentationTestCase2<ActivityInfoCompany> {	
	// Variables para los objetos de la actividad a probar
    private ActivityInfoCompany mActivity;    
    private TextView txtVName;
    int idCompany;
    
	/**
	 * Constructor de la clase de prueba.
	 * LLama al super constructor con el paquete y la clase a probar	 
	 */
	public ActivityInfoCompanyTest() {
		super("apirest.mmm.activitys", ActivityInfoCompany.class);
	}	

    /**
     * Establece el entorno de prueba antes de cada test.
     */    
    @Override
    protected void setUp() throws Exception {
         
         //Llamada al superconstructor, requerida por la JUnit
        super.setUp();
        
        //Intent como actividad inicial
        Intent in = new Intent(Intent.ACTION_MAIN);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        Bundle infoEntreActividades = new Bundle();
        infoEntreActividades.putInt("ID", 1);
        in.putExtras(infoEntreActividades);
        
         // Permite el envío de eventos claves deshabilitando el modo de toque
         // Debe realizarse antes de iniciar la actividad, getActivity() 
        setActivityInitialTouchMode(false);
        
        //Se establece un intent para arrancar la actividad como una nueva actividad, también antes de getActivity()
        setActivityIntent(in);       
        
        
        // Referencia a la actividad principal de la aplicación a probar. También inicia la actividad         
        mActivity = getActivity();
                
        //Referencia al componente visual de la aplicación a probar, con nombre de empresa        
        txtVName = (TextView) mActivity.findViewById (R.id.txtVName);
                        
    }
    /** Test para los valores iniciales de los objetos clave, se verifica que los componentes
     *  inicializados no sean nulos
	 */
    public void testPrecondiciones() {

        // Comprobar que la referencia actividad no es nula
    	assertTrue(mActivity != null);
    	
        // Comprobar que la empresa cargada no es nula
        assertTrue(txtVName != null);        
        Log.i("testPRecondiciones", "txtVName " + txtVName.getText());
    }    
    
    /** Tests para el manejo de estados de la actividad cuando se para y luego reinicia.    
     */ 
    public void testStatePause() {
    	
        // Se obtiene el object instrumentation de la aplicación de prueba.
        // Este objeto realiza todo el trabajo de instrumentación para la el
      	// motor de ejecución de pruebas        
        Instrumentation instr = this.getInstrumentation();
        
    	// Guarda valor antes de pausar
        mActivity.setIdCompany(1);       
        
        // Instrumentación del motor de ejecución, onPause(), desde la actividad de prueba.        
        instr.callActivityOnPause(mActivity);
        
        // Guarda valor disntinto al anterior al anterior a la pausa          
        mActivity.setIdCompany(2);       
        
        // Instrumentación del motor de ejecución, onResume(), desde la actividad de prueba.        
        instr.callActivityOnResume(mActivity);
        
        // Comprueba que el valor guardado antes de la pausa y despues del resume son los mismos                     
        assertEquals(2,mActivity.getIdCompany());
  }
    
}



