package apirest.mmm.conexiones;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

/** Esta clase permite descargar la descarga de una imagen dada su URL
 *  Debido a que se establece una conexión http se trata de una tarea asíncrona.
 *  Para iniciar la tarea se debe utilizar el método execute() y 
 *  para obtener la respuesta invocar al método get(). 
 *  Ambos métodos se heredan de la clase {@linkAsyncTask}   
 */

public class ServicioImagenUrl  extends AsyncTask<String, Float, Bitmap> {	
	
	/**Establece una conexión en background para la descarga de datos.
     * No pude llamarse directamente ya que es invocado por el método execute() 
     * con sus mismos parámetros de entrada. 	
	 * @param params Texto con la URL que localiza la imagen solicitada
	 * @return Imagen descargada en formato Bitmap
	 **/	
	@Override
	protected Bitmap doInBackground(String... params) {    
		Bitmap bmImg = null;		
		try {
			
			HttpURLConnection conn= (HttpURLConnection) new URL(params[0]).openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bmImg = BitmapFactory.decodeStream(is);
			
		} catch (MalformedURLException e) {
			Log.e("ServicioImagenUrl MURL_E", "msg: " + e.getLocalizedMessage());
		} catch (IOException e) {
			Log.e("ServicioImagenUrl IO_E", "msg: " + e.getLocalizedMessage());
		}	

		return bmImg;
	}
}

