package com.example.gamma.labrecu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.example.gamma.labrecu.R.id.pass_get;

public class LoginActivity extends AppCompatActivity {
    EditText rutGet;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnLoginClick(View view){
        rutGet = (EditText) findViewById(R.id.rut_get);
        pass = (EditText) findViewById(R.id.pass_get);

        int rutSize = rutGet.length();
        if(rutSize < 8){
            Toast.makeText(this,"Rut invalido",
                    Toast.LENGTH_LONG).show();
        }
        else{
            String rutS = rutGet.getText().toString();
            int result = CheckRut(rutS ,rutSize);
            if(result == 0){
                Toast.makeText(this,"Rut invalido",
                        Toast.LENGTH_LONG).show();
            }
            else{
                String password = pass.getText().toString();
                if(password=="")
                {
                    Toast.makeText(this,"la clave no puede estar vacia",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Login();
                }

            }
        }



    }

    public void Login(){
        Intent intent = new Intent(this,MainActivity.class);
        SharedPreferences credenciales = getApplicationContext().getSharedPreferences("crendencials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = credenciales.edit();
        editor.putString("rut", rutGet.getText().toString());
        editor.putString("clave", pass.getText().toString());
        editor.commit();
        startActivity(intent);

    }

    public Integer CheckRut(String rut, int size){
        ArrayList digitos = new ArrayList<Integer>() {

        };
        for(int i=size-1;i>=0;i--){
            char letra = rut.charAt(i);
            int digito = Integer.parseInt(String.valueOf(letra));
            digitos.add(digito);
        }
        Integer facto = 2;
        ArrayList multi = new ArrayList<Integer>();
        for(int j=1;j<size;j++){
            int factor1 = (int) digitos.get(j);
            int valor = factor1*facto;
            multi.add(valor);
            facto++;
            if(facto==8){
                facto=2;
            }
        }
        Integer suma=0;
        for(int k=0;k<multi.size();k++){
            int numero = (int) multi.get(k);
            suma=suma+numero;
        }
        int division = suma%11;
        int verificador = 11-division;
        int last = (int) digitos.get(0);
        if(verificador == last){
            return 1;
        }
        else{

            return 0;
        }

    }


}
