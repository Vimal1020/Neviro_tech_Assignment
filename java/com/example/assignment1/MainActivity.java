package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView message;
    EditText fname,paedit,interest;
    Spinner time;
    RadioGroup radioGroup;
    RadioButton si,ci;
    Button submit;
    public int[] t={0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message=findViewById(R.id.message_text);
        message.setVisibility(View.INVISIBLE);
        fname=findViewById(R.id.fname_edit);
        interest=findViewById(R.id.interest_edit);
        paedit=findViewById(R.id.pa_edit);
        time=findViewById(R.id.time_spin);
        radioGroup=findViewById(R.id.radiogroup);
        si=findViewById(R.id.si_radio);
        ci=findViewById(R.id.ci_radio);
        submit=findViewById(R.id.submit_but);
        List<String> list = new ArrayList<String>();
        list.add("Choose the Year");
       for(int i=1;i<21;i++)
       {
           list.add(i+" Year");
       }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);
        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 t[0] = Integer.parseInt(String.valueOf(time.getSelectedItemPosition()));

                 getSelectedtime(t);

            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               Toast.makeText(MainActivity.this,"Please Select from dropdownlist",Toast.LENGTH_SHORT).show();
            }

        });

         submitrecords();
    }
    private void getSelectedtime(int[] t) {
        this.t=t;
        Toast.makeText(MainActivity.this,"out"+String.valueOf(t[0]).toString(), Toast.LENGTH_SHORT).show();

    }


    public  void submitrecords() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(paedit.getText().toString().isEmpty() || interest.getText().toString().isEmpty()||t[0]==0||selectedId==0){
                    Toast.makeText(MainActivity.this,"Please Input All The Fjelds",Toast.LENGTH_SHORT).show();
                }
                final int pa = Integer.parseInt(paedit.getText().toString());
                double r = Integer.parseInt(interest.getText().toString());
                double amount;
                switch (selectedId) {
                    case R.id.si_radio:
                        amount = (pa * r * t[0]) / 100;
                        textset(fname, amount, pa, r, t[0]);
                        break;
                    case R.id.ci_radio:
                        r/=100;
                        amount = pa * Math.pow((1 + r ), t[0]);
                        float cinterest= (float) (amount-pa);

                        r*=100;
                        textset(fname, cinterest, pa, r, t[0]);
                        break;
                }
            }

                    private void textset(EditText name,double amount,int p,double r,int t) {
                      message.setVisibility(View.VISIBLE);
                        message.setText("Hi "+name.getText().toString()+" on the deposit amount of  "+ String.valueOf(p).toString()+" Rs for "+String.valueOf(t).toString()+"years at "+String.valueOf(r) +" % interest rate, you will be payed a monthly interest of "+String.valueOf( String.format("%.2f",amount))+" Rs. Thanks");
                        fname.setText(null);
                        paedit.setText(null);
                        interest.setText(null);
                        time.setSelection(0);
                    }
                });

            }
}





