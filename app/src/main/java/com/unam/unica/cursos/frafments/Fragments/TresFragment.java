package com.unam.unica.cursos.frafments.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.unam.unica.cursos.frafments.AuxSQL;
import com.unam.unica.cursos.frafments.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class TresFragment extends Fragment {
    DecimalFormat format = new DecimalFormat("#.##");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tres, container, false);
        final TextView txtvIngresos = v.findViewById(R.id.txtvIngresos);
        final TextView txtvGastos = v.findViewById(R.id.txtvGastos);
        Spinner spSeleccion = v.findViewById(R.id.spSeleccion);
        String [] arrSeleccion = getResources().getStringArray(R.array.arrSeleccion);

        ArrayAdapter<String> arrAdSeleccion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrSeleccion);

        arrAdSeleccion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spSeleccion.setAdapter(arrAdSeleccion);

        final Calendar fecha = Calendar.getInstance();

        AuxSQL ingresos = new AuxSQL(getActivity(),"ingresos", null, 1);
        AuxSQL gastos = new AuxSQL(getActivity(),"gastos", null,1);

        final SQLiteDatabase dbIng = ingresos.getReadableDatabase();
        final SQLiteDatabase dbGas = gastos.getReadableDatabase();

        spSeleccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                double gastos = 0;
                double ingresos = 0;
                int anho = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH) + 1;
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                switch (i){
                    case 0: //Año
                        Cursor filaIngA = dbIng.rawQuery("SELECT cantidad FROM Ingresos WHERE anho = '" + anho + "'",null);
                        Cursor filaGasA = dbGas.rawQuery("SELECT cantidad FROM Gastos WHERE anho = '" + anho + "'",null);
                        if(filaIngA.moveToFirst() && filaGasA.moveToFirst()){

                            do{
                                ingresos = ingresos + filaIngA.getDouble(0);
                            }while(filaIngA.moveToNext());

                            do{
                                gastos = gastos + filaGasA.getDouble(0);
                            }while(filaGasA.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else if(filaIngA.moveToFirst()){

                            do{
                                ingresos = ingresos + filaIngA.getDouble(0);
                            }while(filaIngA.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else if(filaGasA.moveToFirst()){

                            do{
                                gastos = gastos + filaGasA.getDouble(0);
                            }while(filaGasA.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else{

                            Toast.makeText(getActivity(),"No se encontró", Toast.LENGTH_LONG).show();

                        }
                        break;
                    case 1: //Mes
                        Cursor filaIngM = dbIng.rawQuery("SELECT cantidad FROM Ingresos WHERE anho = '" + anho + "' AND mes = '" + mes + "'",null);
                        Cursor filaGasM = dbGas.rawQuery("SELECT cantidad FROM Gastos WHERE anho = '" + anho + "' AND mes = '" + mes + "'",null);
                        if(filaIngM.moveToFirst() && filaGasM.moveToFirst()){

                            do{
                                ingresos = ingresos + filaIngM.getDouble(0);
                            }while(filaIngM.moveToNext());

                            do{
                                gastos = gastos + filaGasM.getDouble(0);
                            }while(filaGasM.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else if(filaIngM.moveToFirst()){

                            do{
                                ingresos = ingresos + filaIngM.getDouble(0);
                            }while(filaIngM.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else if(filaGasM.moveToFirst()){

                            do{
                                gastos = gastos + filaGasM.getDouble(0);
                            }while(filaGasM.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else{

                            Toast.makeText(getActivity(),"No se encontró", Toast.LENGTH_LONG).show();

                        }
                        break;
                    case 2: //Día
                        Cursor filaIngD = dbIng.rawQuery("SELECT cantidad FROM Ingresos WHERE anho = '" + anho + "' AND mes = '" + mes + "' AND dia = '" + dia + "'",null);
                        Cursor filaGasD = dbGas.rawQuery("SELECT cantidad FROM Gastos WHERE anho = '" + anho + "' AND mes = '" + mes + "' AND dia = '" + dia + "'",null);
                        if(filaIngD.moveToFirst() && filaGasD.moveToFirst()){

                            do{
                                ingresos = ingresos + filaIngD.getDouble(0);
                            }while(filaIngD.moveToNext());

                            do{
                                gastos = gastos + filaGasD.getDouble(0);
                            }while(filaGasD.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else if(filaIngD.moveToFirst()){

                            do{
                                ingresos = ingresos + filaIngD.getDouble(0);
                            }while(filaIngD.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else if(filaGasD.moveToFirst()){

                            do{
                                gastos = gastos + filaGasD.getDouble(0);
                            }while(filaGasD.moveToNext());

                            txtvGastos.setText("" + format.format(CalcularGas(gastos,ingresos)) + "%");
                            txtvIngresos.setText("" + format.format(CalcularIng(gastos, ingresos)) + "%");

                        }
                        else{

                            Toast.makeText(getActivity(),"No se encontró", Toast.LENGTH_LONG).show();

                        }
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }
    public double CalcularGas(double gastos, double ingresos){
        double resultado = (100 * gastos) / (gastos + ingresos);
        return resultado;
    }
    public double CalcularIng(double gastos, double ingresos){
        double resultado = (100 * ingresos) / (gastos + ingresos);
        return resultado;
    }
}
