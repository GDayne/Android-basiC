package com.example.lectortarjetasbus;



import com.example.lectortarjetasbus.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends Activity implements OnClickListener {

	Button botonIn;
	EditText edTxtDireccionIP;
	String direcIP;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuinicialvista);
		
		botonIn = (Button) findViewById(R.id.buttonInicio);
		edTxtDireccionIP =(EditText) findViewById(R.id.editTextDireccionIp);
		
		botonIn.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==botonIn){
			direcIP = edTxtDireccionIP.getText().toString();
			Intent intento= new Intent(Principal.this, lector.class);
			Bundle bolsa = new Bundle();
			bolsa.putString("ipDirec", direcIP);
			intento.putExtras(bolsa);
			
			startActivity(intento);
			
		}
		/*if(v==botonIn){
			IntentIntegrator.initiateScan(Principal.this);
			
		}*/
		
	}
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        switch(requestCode) {
	            case IntentIntegrator.REQUEST_CODE:
	            {
	                if (resultCode == RESULT_CANCELED){
	                }
	                else
	                {
	                    //Recogemos los datos   que nos envio el código Qr/codigo de barras
	                    IntentResult scanResult = IntentIntegrator.parseActivityResult(
	                            requestCode, resultCode, data);
	                    String UPCScanned = scanResult.getContents();
	                    //cOMO ES SOLO UN EJEMPLO LO SACAREMOS POR PANTALLA.
	                    Toast.makeText(getApplicationContext(),UPCScanned,Toast.LENGTH_LONG
	                    ).show();
	                }
	                break;
	            }
	        }
	    }
	}


