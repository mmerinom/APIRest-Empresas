package apirest.mmm.activitys;

import apirest.mmm.activitys.R;
import apirest.mmm.conexiones.ServicioImagenUrl;
import apirest.mmm.conexiones.ServicioJson;
import apirest.mmm.model.InfoCompany;
import apirest.mmm.model.Phone;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/** Actividad que muestra la información de contacto de una empresa 
 *  realizando una petición REST al servicio JSON predeterminado
 */

public class ActivityInfoCompany extends Activity {

	/** Componente visual para la imagen logotipo de la empresa */
	ImageView imgVLogo;
	/** Componente visual para el nombre de la empresa */	
	TextView txtVName;
	/** Componente visual para el sitio web de la empresa */	
	TextView txtVWebsite;
	/** Componente visual para listar las direcciones postalas de la empresa */
	ListView lstVAddressLines;
	/** Componente visual para el código postal de la empresa */
	TextView txtVZipCode;
	/** Componente visual para la ciudad de la empresa */
	TextView txtVCity;
	/** Componente visual para el país de la empresa */
	TextView txtVCountry;
	/** Componente visual para listar los teléfonos de la empresa */
	ListView lstVPhones;
	
	//Información pasada a la actividad
	int idCompany ;	
		
    /** Obtiene el identificador interno de la compañía consultada
	 * @return El entero que sirve como identificador
	 */
	public int getIdCompany() {
		return idCompany;
	}

	/** Establece el identificador de la compañía 
	 * @param idCompany Entero que sirve como identificador
	 */
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	/** Método invocado cuando la actividad se crea por primera vez.
     *  Selecciona el área visual asociada a la actividad e inicializa sus controles
     *  @param savedInstanceState Estado de instancia salvado
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infocompany);		
		initControls();		                                                
	}
	
	/** Obtiene las referencias de los componentes visuales y refresca la información 
	 *  de contacto de una empresa 
     */
	private void initControls(){
		idCompany = this.getIntent().getExtras().getInt("ID");
		
		//Componentes de pantalla
		imgVLogo = (ImageView) findViewById (R.id.imgVLogo);
		txtVName = (TextView) findViewById (R.id.txtVName);
		txtVWebsite = (TextView) findViewById (R.id.txtWebsite);
		lstVAddressLines = (ListView) findViewById (R.id.lstVAddressLines);
		txtVZipCode = (TextView) findViewById (R.id.txtVZipCode);
		txtVCity = (TextView) findViewById (R.id.txtVCity);		
		txtVCountry = (TextView) findViewById (R.id.txtVCountry);
		lstVPhones = (ListView) findViewById (R.id.lstVPhones);
		
		//Carga de datos de la empresa seleccionada
		refreshActivity(idCompany);
	}
	/** Realiza la petición REST que obtiene los datos de contacto de una empresa
	 *  y muestra el resultado.
	 * @param idCompany Entero con el identificador de la empresa a mostrar
     */
	private void refreshActivity(int idCompany){

		//Se solicita la empresa al servicio web
		ServicioJson servicio = new ServicioJson("http://82.194.75.216:8888/SoftwareDeveloper/{version}/company/{companyId}");
		servicio.execute("{version}","mercury.0","{companyId}",Integer.toString(idCompany));
		//Se obtiene la respuesta en formato JSON de la empresa
		try {

			String objetosJson = servicio.get();
			if (objetosJson == null) {
				Log.w("refreshActivity-servicio.get","null");			
	            Toast.makeText(this,
	            		"Error en petición REST", Toast.LENGTH_LONG).show();				
			}else{

				//Se convierten el objeto JSON a objeto InfoCompany				
				Type tipo = new TypeToken<InfoCompany>(){}.getType();
				InfoCompany infoCompany = (new Gson()).fromJson(objetosJson.toString(), tipo);
				if (infoCompany == null) {
					    Log.w("refreshActivity-ObjetoModel","null");
			            Toast.makeText(this,
			            		"Error al recuperar datos", Toast.LENGTH_LONG).show();

				}else{					
					
					    //Descarga de imagen por URL y asignación a su componente en pantalla
					    Bitmap bm = null;;
						ServicioImagenUrl servicioImagenUrl = new ServicioImagenUrl();
						servicioImagenUrl.execute(infoCompany.getLogo());
						try {
							bm = servicioImagenUrl.get(); 																											
							imgVLogo.setImageBitmap(bm); 
							
						} catch (InterruptedException e) {
							Log.e("IE servicioImagenUrl.get", "Msg: " + e.getMessage()) ;
				            Toast.makeText(this,
				            		"Error en petición REST", Toast.LENGTH_LONG).show();							
							
						} catch (ExecutionException e) {
							Log.e("EE servicioImagenUrl.get", "Msg: " + e.getMessage()) ;
				            Toast.makeText(this,
				            		"Error en petición REST", Toast.LENGTH_LONG).show();							
						}

                	    //Nombre y web
                	    txtVName.setText(infoCompany.getName());
                	    txtVWebsite.setText(infoCompany.getWebsite());                	    
						
						//Direcciones 
						ArrayAdapter<String> adapterAddress = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,infoCompany.getAddressLines());
						lstVAddressLines.setAdapter(adapterAddress);
						
						//CP, ciudad y pais
						txtVZipCode.setText(Integer.toString(infoCompany.getZipCode()));
						txtVCity.setText(infoCompany.getCity());
						txtVCountry.setText(infoCompany.getCountry());
						
						//Telefonos						
						Phone[] arrayPones = infoCompany.getPhones();
						ArrayAdapter<String> adapterPhone = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
						for (int i=0 ; i<arrayPones.length; i++){
							adapterPhone.add(arrayPones[i].getCountryCode() + "-" + arrayPones[i].getSubscriberNumber());
						}							
						lstVPhones.setAdapter(adapterPhone);
				}	
			}
		} catch (InterruptedException e) {
			Log.e("IE servicio.get", "Msg: " + e.getMessage());		
		} catch (ExecutionException e) {
			Log.e("IE servicio.get","Msg: " + e.getMessage());
		}
	}
	
}
