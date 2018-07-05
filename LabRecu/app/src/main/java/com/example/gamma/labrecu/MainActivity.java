package com.example.gamma.labrecu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText pesos;
    EditText dolars;
    float peso_val;
    float dolar_val;
    int change_val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pesos = (EditText) findViewById(R.id.peso_get);
        dolars = (EditText)findViewById(R.id.dolar_get);
    }

    public void PesotoDolar(View view){
        String pes = pesos.getText().toString();
        if(pes == ""){
            Toast.makeText(this,"Ingrese un numero valido",
                    Toast.LENGTH_LONG).show();
        }
        else{
            peso_val = Float.parseFloat(pes);
            dolar_val = peso_val*680;
            dolars.setText(String.valueOf(dolar_val));
        }

    }

    public void DolartoPeso(View view){
        String dol = dolars.getText().toString();
        if(dol == ""){
            Toast.makeText(this,"Ingrese un numero valido",
                    Toast.LENGTH_LONG).show();
        }
        else{
            dolar_val = Float.parseFloat(dol);
            peso_val = dolar_val/680;
            pesos.setText(String.valueOf(peso_val));
        }

    }

    public void Logout(View view){
        SharedPreferences credenciales = getApplicationContext().getSharedPreferences("crendencials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = credenciales.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }




}
