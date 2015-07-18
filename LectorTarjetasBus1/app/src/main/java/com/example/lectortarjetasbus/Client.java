package com.example.lectortarjetasbus;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Client extends Activity {
    private Socket socket;
    private static final int SERVERPORT = 8888;
    //private static final String SERVER_IP = "192.168.43.197";
    String SERVER_IP;
    String ip, ci;
    TextView txtip, txtci;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistacliente);     
        new Thread(new ClientThread()).start();
        
        
        txtip=(TextView) findViewById(R.id.textViewIPclient);
        txtci=(TextView) findViewById(R.id.textViewCI);
        Bundle bolsarecibida= getIntent().getExtras();
		ip = bolsarecibida.getString("ip");
	    ci= bolsarecibida.getString("ci");
	    
	    txtci.setText("CI :" + ci);
	    txtip.setText("IP"+ip);
		SERVER_IP=ip;
        
    }
    public void onClick(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.EditText01);
            String str = et.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            
            if(socket.isConnected()){
            	Toast.makeText(getApplicationContext(),"true"+ socket.getPort()  , Toast.LENGTH_SHORT).show();
            	            }
            if(!socket.isConnected()){
            	Toast.makeText(getApplicationContext(),"false"+ socket.getLocalSocketAddress().toString(), Toast.LENGTH_SHORT).show();
                        }
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
}
}