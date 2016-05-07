package com.example.yoso.contactos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity implements View.OnClickListener {



    // Declaración atributos
    private Button btnEditar;
    private TextView nombre;
    private TextView fecha;
    private TextView telefono;
    private TextView email;
    private TextView descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Obteniendo una instancia del boton btnEditar
        btnEditar = (Button) findViewById(R.id.btn_editar);
        // Obteniendo la instancia de los TextView
        nombre = (TextView) findViewById(R.id.campo_nombre);
        fecha = (TextView) findViewById(R.id.campo_fecha);
        telefono = (TextView) findViewById(R.id.campo_telefono);
        email = (TextView) findViewById(R.id.campo_correo);
        descripcion = (TextView) findViewById(R.id.campo_descripcion);



        // Registrando la escucha sobre la actividad Confirmar Datos
        btnEditar.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        //confirmar_nombre = bundle.getString("nombre");
        nombre.setText(bundle.getString("nombre"));
        fecha.setText(bundle.getString("fecha"));
        telefono.setText(bundle.getString("telefono"));
        email.setText(bundle.getString("email"));
        descripcion.setText(bundle.getString("descripcion"));


    }

    @Override
    public void onClick(View v) {
        // Iniciando la actividad
        Intent intent = new Intent(this, ActividadPrincipal.class);

        //Crear un nuevo intent de respuesta
        intent.putExtra("cnombre", nombre.getText().toString());

        startActivity(intent);

    }





}
