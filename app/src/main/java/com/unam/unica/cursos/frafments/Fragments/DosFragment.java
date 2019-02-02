package com.unam.unica.cursos.frafments.Fragments;

import android.content.ContentValues;
import android.content.Context;
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

public class DosFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dos, container, false);
        Button btnGuardar2 = v.findViewById(R.id.btnGuardar2);
        final EditText edtCantidad2 = v.findViewById(R.id.edtCantidadG);
        final Spinner spTipoG = v.findViewById(R.id.spTipoGas);
        String [] arrTipoG = getResources().getStringArray(R.array.arrTipoGasto);

        ArrayAdapter<String> arrAdpTipoG = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrTipoG);

        arrAdpTipoG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTipoG.setAdapter(arrAdpTipoG);

        btnGuardar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar fecha = Calendar.getInstance();
                AuxSQL auxSQL = new AuxSQL(getActivity(), "gastos", null, 1);
                SQLiteDatabase db = auxSQL.getWritableDatabase();
                String cantidad = edtCantidad2.getText().toString();
                String tipo = spTipoG.getSelectedItem().toString();
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
                    db.insert("Gastos", null, registro);
                    db.close();
                    edtCantidad2.setText("");

                    Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }
}
