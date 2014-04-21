package com.greeno.proofofconcept.Controller;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class ImageActivity extends Activity {
	Button takePhoto,uploadImage;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		
		takePhoto = (Button)findViewById(R.id.takePhoto);
		uploadImage = (Button)findViewById(R.id.uploadImage);
		
		
		takePhoto.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				System.out.println("=== Clicked");

				Intent intent = new Intent(ImageActivity.this, ImageTakePhoto.class);
				ImageActivity.this.startActivity(intent);
				ImageActivity.this.finish();
				
			}
		});
		
		uploadImage.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ImageActivity.this, UploadImageActivity.class);
				ImageActivity.this.startActivity(intent);
				ImageActivity.this.finish();
				
			}
		});
		
	}
}
