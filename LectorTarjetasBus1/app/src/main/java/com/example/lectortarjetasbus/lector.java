package com.example.lectortarjetasbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lector extends Activity implements OnClickListener {
	
	String ipDirection;
	Button btnSend, btnScan;
	EditText edtxtCi;
	TextView txtProgress;
	String ci, result;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lectorvista);
		
		btnSend =(Button) findViewById(R.id.buttonCIenviar);
		edtxtCi=(EditText) findViewById(R.id.editTextCImanual);
		txtProgress=(TextView) findViewById(R.id.textViewQR);
		btnScan=(Button) findViewById(R.id.buttonScan);
		btnSend.setOnClickListener(this);
		btnScan.setOnClickListener(this);
		
		Bundle bundleInf= getIntent().getExtras();
		ipDirection = bundleInf.getString("ipDirec");
	    
		
		
		
		
		
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnSend){
			ci= edtxtCi.getText().toString();
			
			Intent intento1= new Intent(lector.this, Client.class);
			Bundle bundleNew = new Bundle();
			bundleNew.putString("ci", ci);
			bundleNew.putString("ip", ipDirection);
			intento1.putExtras(bundleNew);
			
			startActivity(intento1);
			
		}
		
		if(v==btnScan){
			IntentIntegrator.initiateScan(lector.this);
			
		}
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case IntentIntegrator.REQUEST_CODE:
            {
                if (resultCode == RESULT_CANCELED){
                }
                else
                {
                    //Recogemos los datos   que nos envio el c�digo Qr/codigo de barras
                    IntentResult scanResult = IntentIntegrator.parseActivityResult(
                            requestCode, resultCode, data);
                    String UPCScanned = scanResult.getContents();
                    result=UPCScanned;
                    txtProgress.setText(result);
                    edtxtCi.setText(result);

                    Toast.makeText(getApplicationContext(),UPCScanned,Toast.LENGTH_LONG
                    ).show();
                }
                break;
            }
        }
    }

}
