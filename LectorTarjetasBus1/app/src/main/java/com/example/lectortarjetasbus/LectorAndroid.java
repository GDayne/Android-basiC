package com.example.lectortarjetasbus;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LectorAndroid extends Activity implements OnClickListener{
	
	Button botEnviar;
	TextView textIP;
	EditText editMsje;
	String msje;
	String direcIP;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lectorvistaip);
		
		botEnviar = (Button) findViewById(R.id.buttonEnviarmsje);
		textIP =(TextView) findViewById(R.id.textViewIP);
		editMsje=(EditText)findViewById(R.id.editTextMsje);
		
		botEnviar.setOnClickListener(this);
		
		Bundle bolsarecibida= getIntent().getExtras();
		direcIP = bolsarecibida.getString("ipDirec");
	    
		
		
	}
	
	
	
	
	
	
   // public static void main(String[] args) {
	public void run(){
		Toast.makeText(getApplicationContext(), "estableciendo", Toast.LENGTH_SHORT).show();
        try {
            /* Establece la conexion */
            Socket socket = new Socket("10.0.0.8", 8888);
        	//Socket socket = new Socket(direcIP, 8888);
            /* Stream para enviar objetos */
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            /* Stream para recibir objetos */
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            /* Creamos una instancia de la clase Scanner para leer cadenas desde consola */
            //Scanner scanner = new Scanner(System.in);
            /* Enviamos 10 solicitudes al servidor y leemos 10 respuestas*/
         	Toast.makeText(getApplicationContext(), socket.getLocalSocketAddress().toString(), Toast.LENGTH_SHORT).show();
            
            if(socket.isConnected()){
            	Toast.makeText(getApplicationContext(), "conectado", Toast.LENGTH_SHORT).show();
                
            }
            for(int i = 0; i < 10; i++) {
                /* Leemos una linea desde consola */
               //String request = scanner.nextLine();
            	 String request = "hola desde android";
                /* Enviamos la linea al servidor*/
                oos.writeObject(request);
                oos.flush();
                /* Leemos la respuesta del servidor */
                String response = (String) ois.readObject();
                /* Imprimimos la respuesta */
                System.out.println("Response:"+response);
            }
            /* Cerramos los streams y el socket */
            oos.close();
            ois.close();
            socket.close();
        } catch(Exception e) {

        }
    }






	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==botEnviar){
			msje = editMsje.getText().toString();
			run();
			
		}
		
		
	}
}
