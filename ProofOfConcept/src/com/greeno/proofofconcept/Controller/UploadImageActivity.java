// Created by Ramya.D
// Created on 26.11.2013

package com.greeno.proofofconcept.Controller;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.greeno.proofofconcept.Model.HttpPostAndGet;


/* To Upload the image,  Add permission to access the internet connection in AndroidManifest.xml  */
public class UploadImageActivity extends Activity {
		
	Button uploadImage;
	
	int serverResponseCode = 0;
    ProgressDialog dialog = null;
        
    String upLoadServerUri = null;
    
	String uploadFilePath = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+""+"/Rainbow/images/";//"/mnt/sdcard/Camera/";
    String uploadFileName = "myImage.jpg";
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		uploadImage = (Button)findViewById(R.id.uploadImage);

//		upLoadServerUri = "http://rainbowagri.com/RainbowImage/REST/WebService/upload?file=";
		upLoadServerUri = "http://rainbowagri.com/RainbowImage/REST/WebService/upload";
		uploadImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//uploadImage();
				dialog = ProgressDialog.show(UploadImageActivity.this, "", "Uploading file...", true);
                
                new Thread(new Runnable() {
                        public void run() {
                             runOnUiThread(new Runnable() {
                                    public void run() {
                                        uploadImage.setText("uploading started.....");
                                    }
                                });                      
                           
                             uploadFile(uploadFilePath + "" + uploadFileName);
                                                      
                        }
                      }).start();        
                
			}
				
		});
		
	}
	
	
	 public int uploadFile(String sourceFileUri) {
         
         
         String fileName = sourceFileUri;
 
         HttpURLConnection conn = null;
         DataOutputStream dos = null;  
         String lineEnd = "\r\n";
         String twoHyphens = "--";
         String boundary = "*****";
         int bytesRead, bytesAvailable, bufferSize;
         byte[] buffer;
         int maxBufferSize = 1 * 1024 * 1024; 
         File sourceFile = new File(sourceFileUri); 
          
         if (!sourceFile.isFile()) {
              
              dialog.dismiss(); 
               
              Log.e("uploadFile", "Source File not exist :"
                                  +uploadFilePath + "" + uploadFileName);
               
              runOnUiThread(new Runnable() {
                  public void run() {
                	  uploadImage.setText("Source File not exist :"
                              +uploadFilePath + "" + uploadFileName);
                  }
              }); 
               
              return 0;
           
         }
         else
         {
              try { 
                   
                    // open a URL connection to the Servlet
                  FileInputStream fileInputStream = new FileInputStream(sourceFile);
                  URL url = new URL(upLoadServerUri);
                   
                  // Open a HTTP  connection to  the URL
                  conn = (HttpURLConnection) url.openConnection(); 
                  conn.setDoInput(true); // Allow Inputs
                  conn.setDoOutput(true); // Allow Outputs
                  conn.setUseCaches(false); // Don't use a Cached Copy
                  conn.setRequestMethod("POST");
                  conn.setRequestProperty("Connection", "Keep-Alive");
                  conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                  conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                  //conn.setRequestProperty("uploaded_file", fileName);
                  conn.setRequestProperty("file", fileName);
                   
                  dos = new DataOutputStream(conn.getOutputStream());
         
                  dos.writeBytes(twoHyphens + boundary + lineEnd); 
//                  dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""+ fileName + "\"" + lineEnd);
                  dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""+ fileName + "\"" + lineEnd);
                   
                  dos.writeBytes(lineEnd);
         
                  // create a buffer of  maximum size
                  bytesAvailable = fileInputStream.available(); 
         
                  bufferSize = Math.min(bytesAvailable, maxBufferSize);
                  buffer = new byte[bufferSize];
         
                  // read file and write it into form...
                  bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
                     
                  while (bytesRead > 0) {
                       
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
                     
                   }
         
                  // send multipart form data necesssary after file data...
                  dos.writeBytes(lineEnd);
                  dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
         
                  // Responses from the server (code and message)
                  serverResponseCode = conn.getResponseCode();
                  String serverResponseMessage = conn.getResponseMessage();
                    
                  Log.i("uploadFile", "HTTP Response is : "
                          + serverResponseMessage + ": " + serverResponseCode);
                   
                  if(serverResponseCode == 200){
                       
                      runOnUiThread(new Runnable() {
                           public void run() {
                                
                               String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                             +" http://www.androidexample.com/media/uploads/"
                                             +uploadFileName;
                                
                               uploadImage.setText(msg);
                               Toast.makeText(UploadImageActivity.this, "File Upload Complete.", 
                                            Toast.LENGTH_SHORT).show();
                           }
                       });                
                  }    
                   
                  //close the streams //
                  fileInputStream.close();
                  dos.flush();
                  dos.close();
                    
             } catch (MalformedURLException ex) {
                  
                 dialog.dismiss();  
                 ex.printStackTrace();
                  
                 runOnUiThread(new Runnable() {
                     public void run() {
                    	 uploadImage.setText("MalformedURLException Exception : check script url.");
                         Toast.makeText(UploadImageActivity.this, "MalformedURLException", 
                                                             Toast.LENGTH_SHORT).show();
                     }
                 });
                  
                 Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
             } catch (Exception e) {
                  
                 dialog.dismiss();  
                 e.printStackTrace();
                  
                 runOnUiThread(new Runnable() {
                     public void run() {
                    	 uploadImage.setText("Got Exception : see logcat ");
                         Toast.makeText(UploadImageActivity.this, "Got Exception : see logcat ", 
                                 Toast.LENGTH_SHORT).show();
                     }
                 });
                 Log.e("Upload file to server Exception", "Exception : "
                                                  + e.getMessage(), e);  
             }
             dialog.dismiss();       
             return serverResponseCode; 
              
          } // End else block 
        } 
	public void uploadImage()
	{
		HttpPostAndGet httpPostAndGet = new HttpPostAndGet();
		httpPostAndGet.postData1();
	}
	
	
}
