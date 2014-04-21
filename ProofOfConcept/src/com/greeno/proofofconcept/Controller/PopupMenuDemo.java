// Created by Ramya.D
// Created on 21.11.2013

package com.greeno.proofofconcept.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;

import android.widget.Toast;
import android.view.MenuItem; 


/* This will work from API level 9 */

public class PopupMenuDemo extends Activity { 
	
	
    Button popup_but; 
    
	 @Override 
	  protected void onCreate(Bundle savedInstanceState) { 
		  super.onCreate(savedInstanceState); 
		  setContentView(R.layout.popup_menu_demo); 
		  popup_but = (Button) findViewById(R.id.popup_but_id); 

		  
		  popup_but.setOnClickListener(new OnClickListener() { 
			   @Override 
			   public void onClick(View v) { 
				    PopupMenu popup = new PopupMenu(PopupMenuDemo.this, popup_but); 
				    popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
	
		    //popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { 
		    	
		    	popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { 
				        public boolean onMenuItemClick(MenuItem item) { 
	
				         Toast.makeText(PopupMenuDemo.this, 
				           "You Clicked : " + item.getTitle(), 
				           Toast.LENGTH_SHORT).show(); 
				         return true; 
				        } 
			       }); 
			       popup.show(); 

			    } 

		}); 

	 } 

 } 
