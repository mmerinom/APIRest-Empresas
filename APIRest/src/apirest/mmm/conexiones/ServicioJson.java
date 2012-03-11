package apirest.mmm.conexiones;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import android.os.AsyncTask;
import android.util.Log;

/** Esta clase realiza una petici�n REST para la descarga de objetos JSON. 
 *  Debido a que se establece una conexi�n http se trata de una tarea as�ncrona.
 *  Para iniciar la tarea se debe utilizar el m�todo execute() y para obtener 
 *  la respuesta invocar al m�todo get(). 
 *  Ambos se heredan de la clase {@linkAsyncTask}      
 */
public class ServicioJson extends AsyncTask<String, Float, String> {
	
	/** URI del recurso a descargar */
	protected String cadenaURI;
	
	/** Crea un ServicioJson para una URI determinada. 
	 *  @param cadenaURI Texto con la URI que identifica el recurso.
	 *  	   Debe realizarse un marcado de los par�metros en la petici�n.   
	 *  	   Ejemplo: "...path1/{param1}/path2..."
	 */
	public ServicioJson (String cadenaURI) {
		super();
		this.cadenaURI = cadenaURI;
	}
	/**Establece una conexi�n en background para la descarga de datos.
	 * No pude llamarse directamente ya que es invocado por el m�todo execute() 
     * con sus mismos par�metros de entrada. 
	 * @param params N�mero par de par�metros en formato texto, que siguen la l�gica: clave-valor.
	 * 		  Primer par�metro: Texto que identifique a un par�metro. Ejemplo "{param1}". 
	 * 		  Segundo par�metro: Texto valor para el primer par�metro. Ejemplo "valor.param1".    
	 * @retun Texto con la cadena de objetos en formato JSON que responden a la petici�n REST
	 */
	@Override
	protected String doInBackground(String... params) { 
		String cadenaObjetosJSON = null; 
		
		if ((params.length == 0 || (params.length % 2) != 0)) {
			Log.w("ServicioJson:", "error en par�metros" + params.length);			
		}else{
			try {			
				//Se mapean los par�metros en la URI
				for (int i=0; i<params.length; i= i + 2) {
		    	cadenaURI = cadenaURI.replace(params[i],  URLEncoder.encode(params[i+1],"UTF-8"));								    
				}
				
				//Url seg�n m�todo get, cabeceras json y uri con par�metros
				HttpGet dirUrl = new HttpGet();				
				dirUrl.setURI(new URI(cadenaURI));  						
				dirUrl.setHeader("Accept", "application/json");
				dirUrl.setHeader("Content-type", "application/json");              	

				//Consulta url sobre conexi�n cliente http y respuesta Entity			
				HttpResponse respuestaHttp = new DefaultHttpClient().execute(dirUrl);
				HttpEntity respuestaEntity = respuestaHttp.getEntity();

				//Respuesta String de los objetos en formato JSON
				cadenaObjetosJSON = EntityUtils.toString(respuestaEntity);

			} catch (URISyntaxException e) {	
				Log.e("dirUrl.setURI:", e.getMessage());
			} catch (ClientProtocolException e) {			
				Log.e("httpClient.execute:", e.getMessage());
			} catch (IOException e) {
				Log.e("httpClient.execute:", e.getMessage());				
			}
		}

		return cadenaObjetosJSON;
	}
}