// Created by Ramya.D
// Date - 21.11.2013
package com.greeno.proofofconcept.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
		
	Button checkPopupMenu, image;
	
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
		checkPopupMenu=(Button)findViewById(R.id.checkPopupMenu);
		image = (Button)findViewById(R.id.image);
		
		checkPopupMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, PopupMenuDemo.class);
				
				MainActivity.this.startActivity(intent);
				MainActivity.this.finish();
			}
		});
	
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, ImageActivity.class);
				
				MainActivity.this.startActivity(intent);
				MainActivity.this.finish();
			}
		});
		
	}
	
}
