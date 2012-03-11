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

/** Esta clase realiza una petición REST para la descarga de objetos JSON. 
 *  Debido a que se establece una conexión http se trata de una tarea asíncrona.
 *  Para iniciar la tarea se debe utilizar el método execute() y para obtener 
 *  la respuesta invocar al método get(). 
 *  Ambos se heredan de la clase {@linkAsyncTask}      
 */
public class ServicioJson extends AsyncTask<String, Float, String> {
	
	/** URI del recurso a descargar */
	protected String cadenaURI;
	
	/** Crea un ServicioJson para una URI determinada. 
	 *  @param cadenaURI Texto con la URI que identifica el recurso.
	 *  	   Debe realizarse un marcado de los parámetros en la petición.   
	 *  	   Ejemplo: "...path1/{param1}/path2..."
	 */
	public ServicioJson (String cadenaURI) {
		super();
		this.cadenaURI = cadenaURI;
	}
	/**Establece una conexión en background para la descarga de datos.
	 * No pude llamarse directamente ya que es invocado por el método execute() 
     * con sus mismos parámetros de entrada. 
	 * @param params Número par de parámetros en formato texto, que siguen la lógica: clave-valor.
	 * 		  Primer parámetro: Texto que identifique a un parámetro. Ejemplo "{param1}". 
	 * 		  Segundo parámetro: Texto valor para el primer parámetro. Ejemplo "valor.param1".    
	 * @retun Texto con la cadena de objetos en formato JSON que responden a la petición REST
	 */
	@Override
	protected String doInBackground(String... params) { 
		String cadenaObjetosJSON = null; 
		
		if ((params.length == 0 || (params.length % 2) != 0)) {
			Log.w("ServicioJson:", "error en parámetros" + params.length);			
		}else{
			try {			
				//Se mapean los parámetros en la URI
				for (int i=0; i<params.length; i= i + 2) {
		    	cadenaURI = cadenaURI.replace(params[i],  URLEncoder.encode(params[i+1],"UTF-8"));								    
				}
				
				//Url según método get, cabeceras json y uri con parámetros
				HttpGet dirUrl = new HttpGet();				
				dirUrl.setURI(new URI(cadenaURI));  						
				dirUrl.setHeader("Accept", "application/json");
				dirUrl.setHeader("Content-type", "application/json");              	

				//Consulta url sobre conexión cliente http y respuesta Entity			
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