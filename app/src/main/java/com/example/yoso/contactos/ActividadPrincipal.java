package com.example.yoso.contactos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class ActividadPrincipal extends AppCompatActivity  {

    public final static int OPINION_REQUEST_CODE = 1;

    // Declaracione
    private Button btn_datapicker;
    private int anyo, mes, dia;
    private static final int DIALOG_ID = 0;

    private TextView  data_fecha;
    private TextInputLayout tilNombre;
    private TextInputLayout tilTelefono;
    private TextInputLayout tilCorreo;
    private TextInputLayout til_descripcion;
    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoCorreo;
    private EditText campoDescripcion;


    private EditText cnombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Recogiendo el intent
        Bundle bundle = getIntent().getExtras();
        //confirmar_nombre = bundle.getString("nombre");




        // Referencias TILs
        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilTelefono = (TextInputLayout) findViewById(R.id.til_telefono);
        tilCorreo = (TextInputLayout) findViewById(R.id.til_correo);
        til_descripcion = (TextInputLayout) findViewById(R.id.til_descripcion);

        // Referencias ETs
        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoTelefono = (EditText) findViewById(R.id.campo_telefono);
        campoCorreo = (EditText) findViewById(R.id.campo_correo);
        campoDescripcion = (EditText) findViewById(R.id.campo_descripcion);
       // data_fecha = (TextView) findViewById(R.id.data_fecha);
        data_fecha = (TextView)findViewById(R.id.data_fecha);


        // Botón aceptar
        Button botonAceptar = (Button) findViewById(R.id.boton_aceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });



        // Inicialzar el Data Picker
        /*final Calendar cal = Calendar.getInstance();
        anyo = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH) + 1;
        dia = cal.get(Calendar.DAY_OF_MONTH); */

        // Metodo de cuando el boton es pulsado
        showDialogOnButtonClick();


        // fecha nacimiento

        // Captura la fecha del nacimiento
        data_fecha.setText(dia + "/" + mes + "/" + anyo);




        // Salvar entradas
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        campoNombre.setText(prefe.getString("nombre", ""));
        data_fecha.setText(prefe.getString("fecha", ""));
        campoTelefono.setText(prefe.getString("telefono", ""));
        campoCorreo.setText(prefe.getString("correo", ""));
        campoDescripcion.setText(prefe.getString("descripcion", ""));



        campoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilTelefono.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        campoCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                esCorreoValido(String.valueOf(s));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




}

    // Metodo DataPicker
    public void showDialogOnButtonClick(){
        btn_datapicker = (Button)findViewById(R.id.data_picker);


        btn_datapicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDialog(DIALOG_ID);
            }
        });
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner, dia, mes, anyo);
        return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            anyo = year;
            mes = monthOfYear;
            dia = dayOfMonth;


            data_fecha.setText(dia + "/" + mes + "/" + anyo);




        }
    };

    // Método para comprobar si el nombre es valido y la long no exceda de 30
    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches()) {
            tilNombre.setError("Nombre no válido");
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    // Método para validar el teléfono
    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            tilTelefono.setError("Teléfono no válido");
            return false;
        } else {
            tilTelefono.setError(null);
        }

        return true;
    }

    // Método para validar correo
    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError("Correo electrónico no válido");
            return false;
        } else {
            tilCorreo.setError(null);
        }

        return true;
    }


    // Método validar datos
    private void validarDatos(){
        String nombre = tilNombre.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();
        String correo = tilCorreo.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esTelefonoValido(telefono);
        boolean c = esCorreoValido(correo);

        if (a && b && c) {


           // Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();


            // Iniciar la actividad Confirmar Datos
            Intent intent = new Intent(this, ConfirmarDatos.class);
            // Variables a enviar
            intent.putExtra("nombre", campoNombre.getText().toString());
            intent.putExtra("fecha", data_fecha.getText().toString());
            intent.putExtra("telefono", campoTelefono.getText().toString());
            intent.putExtra("email", campoCorreo.getText(). toString());
            intent.putExtra("descripcion", campoDescripcion.getText().toString());

            startActivity(intent);

            // Guardar el estado
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("nombre", campoNombre.getText().toString());
            editor.putString("fecha", data_fecha.getText().toString());
            editor.putString("telefono", campoTelefono.getText().toString());
            editor.putString("correo", campoCorreo.getText().toString());
            editor.putString("descripcion", campoDescripcion.getText().toString());
            editor.commit();
            finish();

        }



    }




}
