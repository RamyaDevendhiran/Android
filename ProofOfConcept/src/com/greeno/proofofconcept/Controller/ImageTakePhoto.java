package com.greeno.proofofconcept.Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

public class ImageTakePhoto extends Activity{
	String imageCapturedPath = "";
	public final int CAMERA_PIC_REQUEST = 1;
	Bitmap thumbnail = null;
	String imageFileName =  "7.jpg";
	String imageFileNameTemp =  "TempImage1.jpg";
	File folderOriginal = new File(Environment.getExternalStorageDirectory()
			+ "/ProofOfConcept/Images");
	File folderTemp = new File(Environment.getExternalStorageDirectory()
			+ "/ProofOfConcept/Temp");
	File filePath = new File(folderOriginal,imageFileName);
	File filePathTemp = new File(folderTemp,imageFileNameTemp);
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);

		File folderMain = new File(Environment.getExternalStorageDirectory()
				+ "/ProofOfConcept");

		if (folderTemp.exists()) {
			folderTemp.delete();
		}
		
		if (!folderMain.exists()) {
			if (folderMain.mkdir()) {

			}
		}
		if (!folderOriginal.exists()) {
			if (folderOriginal.mkdir()) {

			}
		}
		if (!folderTemp.exists()) {
			if (folderTemp.mkdir()) {

			}
		}
		
		try {
				
			if(filePath.exists())
			{
				filePath.renameTo(filePathTemp);
			}
			
			
			Uri outputFileUri = Uri.fromFile(filePath);
			Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);		
			startActivityForResult(i, CAMERA_PIC_REQUEST);

		}
		catch(ActivityNotFoundException anfe){
		    //display an error message
		    String errorMessage = "Whoops - your device doesn't support capturing images!";
		    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
		    toast.show();
		}

        
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case CAMERA_PIC_REQUEST:
			if (resultCode == RESULT_OK) {
				
	        try{
			//    Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
///$					File filePath = new File(folderOriginal,imageFileName);
///$			File filePathTemp = new File(folderTemp,imageFileNameTemp);
	        	
	        	
	        	
	        //	Editer.PHOTO_FROM=11;

                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());

                for (File temp : f.listFiles()) {

                    if (temp.getName().equals("temp.jpg")) {

                        f = temp;

                        break;

                    }

                }

                //Uri.fromFile(createFile());

        ///        Constant.filePath=f.getAbsolutePath();

                String fname = "user_image_golf.jpg";

                BitmapFactory.Options options = new BitmapFactory.Options();

                options.inTempStorage = new byte[16 * 1024];

                options.inSampleSize = 5;

                options.outWidth = 1000;

                options.outHeight = 1000;

                String root = Environment.getExternalStorageDirectory().toString();

         //       File myDir = new File(root + imageFileName);   // == /
          //      myDir.mkdirs();

                File destination = new 
                			File(folderOriginal,imageFileName);


                Bitmap bitmap = BitmapFactory.decodeFile(destination.toString(), options);

                Bitmap map = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();

         //       map.compress(Bitmap.CompressFormat.JPEG, 100, bao);

                //byte[] ba = bao.toByteArray();

                File file = new File (folderOriginal, imageFileName);

                if (file.exists ()) file.delete ();

                try {

                    FileOutputStream out = new FileOutputStream(file);

                    map.compress(Bitmap.CompressFormat.JPEG, 100, out);

                    out.flush();

                    out.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }

                System.out.println("//imagedata=Base64.encodeBytes(ba);");
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
//////////////////////////////////////////////////////////	        	
				filePathTemp.delete();
				filePath.renameTo(filePathTemp);
				Intent intent = new Intent("com.android.camera.action.CROP"); 
			    intent.setDataAndType(getTempUri(1), "image");
			    intent.putExtra("crop", "true");
			    intent.putExtra("aspectX", 1);
			    intent.putExtra("aspectY", 1);
			    intent.putExtra("outputX", 56);
			    intent.putExtra("outputY", 56);
			    intent.putExtra("scale", true);
			    intent.putExtra("return-data", false);
			    intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri(0));
		//	    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
			    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//			    Bitmap.CompressFormat.PNG, 90, out
			    intent.putExtra("noFaceDetection",false); // lol, negative boolean noFaceDetection
			    if (true) {
			    	intent.putExtra("circleCrop", true);
			    }
			
			    startActivityForResult(intent, 2);
	        } catch (ActivityNotFoundException e) {
		        	Toast.makeText(this, "Activity Not Found Exception", Toast.LENGTH_LONG).show();
		        }
				
			}
			else
			{
				System.out.println("===== filePathTemp  == Cancel the photo");
///$				File filePath = new File(folderOriginal,imageFileName);
///$				File filePathTemp = new File(folderTemp,imageFileNameTemp);
				
				if(filePathTemp.exists())
				{
					Toast.makeText(ImageTakePhoto.this, 
					           "filePathTemp exists : " , 
					           Toast.LENGTH_SHORT).show();
				}
				if(filePath.exists())
				{

					Toast.makeText(ImageTakePhoto.this, 
					           "FILE EXITS......... : " , 
					           Toast.LENGTH_SHORT).show();
				}
				filePathTemp.renameTo(filePath);
			}
			break;
			
		case 2:
			if(resultCode == RESULT_OK)
			{
/*				File filePathTemp = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/ProofOfConcept/Temp",
						imageFileNameTemp);
				filePathTemp.delete();
				
*/				
			}
			else
			{
///$				File filePath = new File(folderOriginal,imageFileName);
///$				File filePathTemp = new File(folderTemp,imageFileNameTemp);
				filePathTemp.renameTo(filePath);
			}
		
		}
	}
	

	private Uri getTempUri(int pos) {
		return Uri.fromFile(getTempFile(pos));
	}
	
	private File getTempFile(int pos) {
		if (isSDCARDMounted()) {
			String fName = "";
		if(pos == 1)
			fName  = Environment.getExternalStorageDirectory()+"/ProofOfConcept/Temp/"+imageFileNameTemp;
		else
			fName = Environment.getExternalStorageDirectory()+"/ProofOfConcept/Images/"+imageFileName;
//		File f = new File(Environment.getExternalStorageDirectory()+"/ProofOfConcept/Images",fName);
		File f = new File(fName);
		try {
			f.createNewFile();
			
			//decodeFile(f);
/*			Bitmap data = null;
	        FileOutputStream os = new FileOutputStream(f);
	        data.compress(Bitmap.CompressFormat.JPEG, 100, os);
	        os.flush();
	        os.close();
*/		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, " IO Exception ", Toast.LENGTH_LONG).show();
		}
		return f;
		} else {
		return null;
		}
	}
	
	private boolean isSDCARDMounted(){
		String status = Environment.getExternalStorageState();
		
		if (status.equals(Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}
	private Bitmap decodeFile(File f) {
		try {
			// Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 70;

			// Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_SIZE
					&& o.outHeight / scale / 2 >= REQUIRED_SIZE)
				scale *= 2;

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
		}
		return null;
	}
}
