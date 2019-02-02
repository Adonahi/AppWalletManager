package com.unam.unica.cursos.frafments.Fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.unam.unica.cursos.frafments.AuxSQL;
import com.unam.unica.cursos.frafments.R;

import java.util.Calendar;

public class UnoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_uno, container, false);

        Button boton = v.findViewById(R.id.btnGuardar);
        final EditText edtCantidad = v.findViewById(R.id.edtCantidadI);
        final Spinner spTipo = v.findViewById(R.id.spTipoIng);
        String [] arrTipo = getResources().getStringArray(R.array.arrTipoIngreso);

        ArrayAdapter<String> arrAdpTipo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrTipo);

        arrAdpTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTipo.setAdapter(arrAdpTipo);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar fecha = Calendar.getInstance();
                AuxSQL auxSQL = new AuxSQL(getActivity(), "ingresos", null, 1);
                SQLiteDatabase db = auxSQL.getWritableDatabase();
                String cantidad = edtCantidad.getText().toString();
                String tipo = spTipo.getSelectedItem().toString();
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                int mes = fecha.get(Calendar.MONTH) + 1;
                int anho = fecha.get(Calendar.YEAR);
                if(cantidad.equals("")) {
                    Toast.makeText(getActivity(), "No puede haber campos vacíos", Toast.LENGTH_LONG).show();
                    db.close();
                }
                else{
                    ContentValues registro = new ContentValues();
                    registro.put("tipo", tipo);
                    registro.put("cantidad", cantidad);
                    registro.put("anho", anho);
                    registro.put("mes", mes);
                    registro.put("dia", dia);
                    db.insert("Ingresos", null, registro);
                    db.close();
                    edtCantidad.setText("");

                    Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

}
