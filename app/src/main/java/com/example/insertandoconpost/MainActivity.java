package com.example.insertandoconpost;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    /*
    Usando la librería: https://loopj.com/android-async-http/
    añadir
    dependencies{
        implementation 'com.loopj.android:android-async-http:1.4.9'

    }
     */
Button insertar;
TextView modelo, marca, precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertar = findViewById(R.id.button);
        modelo = findViewById(R.id.editTextModelo);
        marca = findViewById(R.id.editTextMarca);

        precio = findViewById(R.id.editTextPrecio);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncHttpClient cliente = new AsyncHttpClient();
                RequestParams parametros = new RequestParams();
                parametros.put("modelo", modelo.getText().toString());
                parametros.put("marca", marca.getText().toString());
                parametros.put("precio", precio.getText().toString());


                String SCRIPT = "http://192.168.0.42/webmanuel/insertarConPost.php";

                cliente.post(SCRIPT, parametros, new AsyncHttpResponseHandler() {
                    @Override
                    public void onProgress(long bytesWritten, long totalSize) {
                        super.onProgress(bytesWritten, totalSize);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(MainActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });


            };
        });

    }
}