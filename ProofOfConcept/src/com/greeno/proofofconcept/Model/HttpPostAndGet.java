package com.greeno.proofofconcept.Model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.ContentBody;
//import org.apache.http.entity.mime.content.FileBody;
import android.content.Context;
import android.os.Environment;

public class HttpPostAndGet 
{
	
	public String getJSONResponse(String url,Context context) throws JSONException
	{
		
		JSONObject json = new JSONObject();
		String temp = null;
		temp = postData(json,url,context);
		
		return temp;
	}
 
 
		 public String postData(JSONObject json,String url,Context context)
		 {

				HttpParams httpParameters = new BasicHttpParams();
		        HttpConnectionParams.setSocketBufferSize(httpParameters,78990);
		        HttpClient httpclient=null;
		       
		        httpclient = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet(url);
			        String temp = null;
			        try {
			           
			        	StringEntity value = new StringEntity(json.toString());  
			            value.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			    //        HttpEntity entity = value;
			     
		 	            // Execute HTTP Post Request
			            HttpResponse response = httpclient.execute(httpGet);
			   //         HttpEntity httpEntity=response.getEntity();
			            temp = EntityUtils.toString(response.getEntity());
			          
			             
			        } catch (ClientProtocolException e) {
			            // TODO Auto-generated catch block
			        
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			        
			        }
			        catch(Exception e)
			        {
			        	
			        }	
			        return temp;
			}
		 
		 
		 public void postData1()
		 {

			 HttpURLConnection connection = null;
			 DataOutputStream outputStream = null;
	//		 DataInputStream inputStream = null;

//			 String pathToOurFile = "/data/file_to_send.mp3";
			// String urlServer = "http://192.168.1.1/handle_upload.php";
			 String pathToOurFile = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
			 String urlServer = "http://rainbowagri.com/RainbowImage/REST/WebService/upload?file=";
			 String lineEnd = "\r\n";
			 String twoHyphens = "--";
			 String boundary =  "*****";

			 int bytesRead, bytesAvailable, bufferSize;
			 byte[] buffer;
			 int maxBufferSize = 1*1024*1024;

			 pathToOurFile = pathToOurFile + "/Camera/Test.jpg";
			 try
			 {
			 FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );

			 URL url1 = new URL(urlServer);
			 connection = (HttpURLConnection) url1.openConnection();

			 // Allow Inputs & Outputs
			 connection.setDoInput(true);
			 connection.setDoOutput(true);
			 connection.setUseCaches(false);

			 // Enable POST method
			 connection.setRequestMethod("POST");

			 connection.setRequestProperty("Connection", "Keep-Alive");
			 connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

			 outputStream = new DataOutputStream( connection.getOutputStream() );
			 outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			 outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
			 outputStream.writeBytes(lineEnd);

			 bytesAvailable = fileInputStream.available();
			 bufferSize = Math.min(bytesAvailable, maxBufferSize);
			 buffer = new byte[bufferSize];

			 // Read file
			 bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			 while (bytesRead > 0)
			 {
			 outputStream.write(buffer, 0, bufferSize);
			 bytesAvailable = fileInputStream.available();
			 bufferSize = Math.min(bytesAvailable, maxBufferSize);
			 bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			 }

			 outputStream.writeBytes(lineEnd);
			 outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			 // Responses from the server (code and message)
//			 int serverResponseCode = connection.getResponseCode();
			 String serverResponseMessage = connection.getResponseMessage();
			 System.out.println("=== serverResponseMessage =="+serverResponseMessage);
			 fileInputStream.close();
			 outputStream.flush();
			 outputStream.close();
			 }
			 catch (Exception ex)
			 {
			 //Exception handling
			 }
		}
/*		 
		 public void postData2()
		 {
			 String url = "";
			 String imagePath = "";
			
			 HttpClient httpclient = new DefaultHttpClient();
             httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

             HttpPost httppost = new HttpPost(url);
             File file = new File(imagePath);

             MultipartEntity mpEntity = new MultipartEntity();
             ContentBody cbFile = new FileBody(file, "image/jpg");
             mpEntity.addPart("userfile", cbFile);
    
             httppost.setEntity(mpEntity);
             Log.e("executing request " + httppost.getRequestLine());
             HttpResponse response = httpclient.execute(httppost);
             HttpEntity resEntity = response.getEntity();
             Log.e(""+response.getStatusLine());
             if (resEntity != null) {
                       Log.e(EntityUtils.toString(resEntity));
             }
             if (resEntity != null) {
                       resEntity.consumeContent();
             }
             httpclient.getConnectionManager().shutdown();
		 }
*/
/*		 
		 public String postImage()
		 {
			 HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(
	                    "http://xyz/upload_picture");

	            try {
	                MultipartEntity entity = new MultipartEntity();

	                entity.addPart("key", new StringBody("abc"));
	                entity.addPart("login", new StringBody("abc"));
	                entity.addPart("password", new StringBody("test"));
	                entity.addPart("property_id", new StringBody("111"));


	                File file = new File(Environment.getExternalStoragePublicDirectory(
	                        Environment.DIRECTORY_DCIM).toString()
	                        + "/Camera/Test.jpg");
	                entity.addPart("picture", new FileBody(file));

	                httppost.setEntity(entity);
	                HttpResponse response = httpclient.execute(httppost);

	                Log.e("test", "SC:" + response.getStatusLine().getStatusCode());

	                HttpEntity resEntity = response.getEntity();

	                BufferedReader reader = new BufferedReader(new InputStreamReader(
	                        response.getEntity().getContent(), "UTF-8"));
	                String sResponse;
	                StringBuilder s = new StringBuilder();

	                while ((sResponse = reader.readLine()) != null) {
	                    s = s.append(sResponse);
	                }
	                Log.e("test", "Response: " + s);
	            } catch (ClientProtocolException e) {
	            } catch (IOException e) {
	            }
		 }

*/
}
