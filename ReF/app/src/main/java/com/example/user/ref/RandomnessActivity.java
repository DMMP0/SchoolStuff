package com.example.user.ref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RandomnessActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public  static Algoritmo_Casualita_Generico alg;
    Spinner dropdown;
    TextView Description ,LastTV,FirstTV,SecondTV,maxTV,ris;
    EditText FirstInput,MaxInput,SecondInput,LastInput;
    private static final String[]paths = {"Generatore Lineare Congruenziale", "Lagged Fibonacci", "Registro a Scorrimento a Retroazione Lineare","Blum Blum Shub","Esempio di algoritmo non distribuito"};
    Button set,gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomness);

        // obj def
        dropdown = findViewById(R.id.spinner1);
        Description = findViewById(R.id.textView6);
        Description.setMovementMethod(new ScrollingMovementMethod());
        LastTV = findViewById(R.id.textView2);
        FirstTV = findViewById(R.id.textView3);
        SecondTV = findViewById(R.id.textView4);
        maxTV = findViewById(R.id.textView5);
        ris = findViewById(R.id.textView7);
        ris.setMovementMethod(new ScrollingMovementMethod());
        FirstInput = findViewById(R.id.editText4);
        MaxInput = findViewById(R.id.editText5);
        SecondInput = findViewById(R.id.editText3);
        LastInput = findViewById(R.id.editText2);

        

        //create a list of items for the spinner.

        ArrayAdapter<String>adapter = new ArrayAdapter<>(RandomnessActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                alg = new Generatore_Lineare_Congruenziale();

                FirstTV.setText("Moltiplicaore");//a
                SecondTV.setText("Incremento");//c
                maxTV.setText("Modulo");//m
                LastTV.setText("seed");//seed

                FirstTV.setVisibility(View.VISIBLE);
                SecondTV.setVisibility(View.VISIBLE);
                maxTV.setVisibility(View.VISIBLE);
                LastTV.setVisibility(View.VISIBLE);

                FirstInput.setVisibility(View.VISIBLE);
                SecondInput.setVisibility(View.VISIBLE);
                MaxInput.setVisibility(View.VISIBLE);
                LastInput.setVisibility(View.VISIBLE);

                 break;

            case 1:
                alg = new Lagged_Fibonacci();

                FirstTV.setText("k");
                SecondTV.setText("j");
                maxTV.setText("Max");
                LastTV.setText("");


                FirstTV.setVisibility(View.VISIBLE);
                SecondTV.setVisibility(View.VISIBLE);
                maxTV.setVisibility(View.VISIBLE);
                LastTV.setVisibility(View.INVISIBLE);


                FirstInput.setVisibility(View.VISIBLE);
                SecondInput.setVisibility(View.VISIBLE);
                MaxInput.setVisibility(View.VISIBLE);
                LastInput.setVisibility(View.INVISIBLE);


                break;
            case 2:
                alg = new Registro_a_Scorrimento_a_Retroazione_Lineare();

                FirstTV.setText("");
                SecondTV.setText("Seed");
                maxTV.setText("");
                LastTV.setText("");


                FirstTV.setVisibility(View.INVISIBLE);
                SecondTV.setVisibility(View.VISIBLE);
                maxTV.setVisibility(View.INVISIBLE);
                LastTV.setVisibility(View.INVISIBLE);


                FirstInput.setVisibility(View.INVISIBLE);
                SecondInput.setVisibility(View.VISIBLE);
                MaxInput.setVisibility(View.INVISIBLE);
                LastInput.setVisibility(View.INVISIBLE);



                break;
            case 3:
                try {
                    alg = new Blum_Blum_Shub();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FirstTV.setText("p");
                SecondTV.setText("q");
                maxTV.setText("seed");
                LastTV.setText("");




                FirstTV.setVisibility(View.VISIBLE);
                SecondTV.setVisibility(View.VISIBLE);
                maxTV.setVisibility(View.VISIBLE);
                LastTV.setVisibility(View.INVISIBLE);


                FirstInput.setVisibility(View.VISIBLE);
                SecondInput.setVisibility(View.VISIBLE);
                MaxInput.setVisibility(View.VISIBLE);
                LastInput.setVisibility(View.INVISIBLE);

                break;
            case 4:
                alg = new Esempio1();

                //TODO


                FirstTV.setText("");
                SecondTV.setText("");
                maxTV.setText("Max");
                LastTV.setText("");



                FirstTV.setVisibility(View.INVISIBLE);
                SecondTV.setVisibility(View.INVISIBLE);
                maxTV.setVisibility(View.VISIBLE);
                LastTV.setVisibility(View.INVISIBLE);


                FirstInput.setVisibility(View.INVISIBLE);
                SecondInput.setVisibility(View.INVISIBLE);

                MaxInput.setVisibility(View.VISIBLE);
                LastInput.setVisibility(View.INVISIBLE);
                
                

                
                break;

        }
        Description.setText(alg.Description());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onSetPressed(View view)
    {
        if(alg instanceof Generatore_Lineare_Congruenziale)
        {

            ((Generatore_Lineare_Congruenziale) alg).setA(Integer.parseInt((FirstInput.getText().toString())));
            ((Generatore_Lineare_Congruenziale) alg).setC(Integer.parseInt(SecondInput.getText().toString()));
            ((Generatore_Lineare_Congruenziale) alg).setM(Integer.parseInt( MaxInput.getText().toString()));
            ((Generatore_Lineare_Congruenziale) alg).setSeed(Integer.parseInt( LastInput.getText().toString()));
        }else{
            if(alg instanceof Lagged_Fibonacci)
            {
                ((Lagged_Fibonacci) alg).setJ(Integer.parseInt(FirstInput.getText().toString()));
                ((Lagged_Fibonacci) alg).setK(Integer.parseInt(SecondInput.getText().toString()));
                ((Lagged_Fibonacci) alg).setMAX(Integer.parseInt(MaxInput.getText().toString()));
            }
            else{
                if (alg instanceof Registro_a_Scorrimento_a_Retroazione_Lineare)
                {
                    alg = new Registro_a_Scorrimento_a_Retroazione_Lineare(SecondInput.getText().toString());
                }
                else
                {
                    if (alg instanceof  Blum_Blum_Shub)
                    {

                        ((Blum_Blum_Shub) alg).setP(Integer.parseInt(FirstInput.getText().toString()));
                        ((Blum_Blum_Shub) alg).setQ(Integer.parseInt(SecondInput.getText().toString()));
                        try {
                            ((Blum_Blum_Shub) alg).setSeed(Integer.parseInt(MaxInput.getText().toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        if(alg instanceof Esempio1)
                        {
                            ((Esempio1) alg).setMax(Integer.parseInt(MaxInput.getText().toString()));
                        }
                    }
                }
            }
            Description.setText(alg.Description());
        }



    }

    public void onGENPressed(View view)
    {

        StringBuilder sbr = new StringBuilder("Numeri Generati:");
        String Ris;

        for(int i=0; i<10;i++)
        {
           sbr.append(" "+alg.Genera())  ;
        }
        Ris = sbr.toString();
        ris.setText(Ris);



    }

}
