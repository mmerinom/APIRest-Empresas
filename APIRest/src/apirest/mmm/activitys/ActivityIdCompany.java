package apirest.mmm.activitys;

import apirest.mmm.activitys.R;
import apirest.mmm.conexiones.ServicioJson;
import apirest.mmm.model.IdCompany;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/** Actividad que muestra el nombre de las empresas de las que se tienen datos de contacto 
 *  realizando una petición REST al servicio JSON predeterminado.
 *  Se trata de la actividad de entrada en la aplicación
 */

public class ActivityIdCompany extends Activity {
	
	/** Componente visual para listar las empresas */
	ListView lstVEmpresas ;
	
	/** Método invocado cuando la actividad se crea por primera vez.
     *  Selecciona el área visual asociada a la actividad e inicializa sus controles
     *  @param savedInstanceState Estado de instancia salvado
     */	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.idcompany);
		initControls();
	}
	
	/** Identifica los componentes visuales y refresca la información
	 *  de la lista de empresas disponibles
     */ 
	private void initControls()	{

		//Conponente lista en pantalla
		lstVEmpresas = (ListView) findViewById (R.id.lstVEmpresas);


		//Oyente del evento onclick sobre un elmento de la lista
		lstVEmpresas.setOnItemClickListener(new OnItemClickListener() {
			/** Recupera la empresa seleccionada e inicia la actividad que muestra 
			 *  sus datos de contacto
			 *  @param parent Adaptadordevista que recibió el evento.
			 *  @param view ElementoVista en el adaptador que recibió el evento. 
			 *  @param position Posición del elemento vista en el adaptador
			 *  @param id identificador de fila del elementos que recibió la acción
			 */ 
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
											
				//Se fija la información a pasar a la siguiente actividad, el identificador de empresa				
				Intent pasoEntreActividades = new Intent(ActivityIdCompany.this, ActivityInfoCompany.class);				
				Bundle infoEntreActividades = new Bundle();	
				IdCompany idcompany = (IdCompany) parent.getItemAtPosition(position);
				infoEntreActividades.putInt("ID", idcompany.getId());				
				pasoEntreActividades.putExtras(infoEntreActividades);				
				
				//Se incia la aplicación secundaria: Información de contacto de una empresa
				startActivity(pasoEntreActividades);
			}		
		});
		//Carga de lista de empresas que mantien el servicio web
		refreshActivity();
	}
	
	/** Realiza la petición REST que obtiene la lista de empresas disponibles y muestra el resultado
     */
	private void refreshActivity() {
		try {
			
			//Se solicita la lista de empresas al servicio web
			ServicioJson servicio = new ServicioJson("http://82.194.75.216:8888/SoftwareDeveloper/{version}/company");
			servicio.execute("{version}","mercury.0");	
			
			//Se obtiene la respuesta en formato JSON del array de empresas
			String objetosJson = servicio.get();
			if (objetosJson == null) {
				Log.w("refreshActivity-servicio.get","null");
	             Toast.makeText(this,
	                     "Error en petición REST", Toast.LENGTH_LONG).show();
				
			}else {			
				
				//Se convierten los objetos JSON a colección de objetos ActivityListCompany				
				Type tipo = new TypeToken<List<IdCompany>>(){}.getType();
				List<IdCompany> lista = (new Gson()).fromJson(objetosJson.toString(), tipo);		
				if (lista == null) {
					Log.w("refreshActivity-ObjetoModel","null");
		             Toast.makeText(this,
		                     "Error al recuperar datos", Toast.LENGTH_LONG).show();
					
				}else{															
					//Adaptador para la lista
					ArrayAdapter<IdCompany> adapter = new ArrayAdapter<IdCompany>(this, android.R.layout.simple_list_item_1);
					for (int i=0; i< lista.size();i++){            
						adapter.add(lista.get(i));
					}
					lstVEmpresas.setAdapter(adapter);			
				}
			}			
		} catch (InterruptedException e) {
			Log.e("refreshActivity-servicio.get-IE",e.getMessage());
            Toast.makeText(this,
            		"Error en petición REST", Toast.LENGTH_LONG).show();			
		} catch (ExecutionException e) {
			Log.e("refreshActivity-servicio.get-EE",e.getMessage());
			
		}		
	}
}