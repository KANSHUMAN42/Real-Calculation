package com.example.realcalculation;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Basic_Calculator extends AppCompatActivity {

    @BindView(R.id.button0) Button button0;
    @BindView(R.id.button1)
    public Button button1;
        @BindView(R.id.button2) Button button2;
        @BindView(R.id.button3) Button button3;
        @BindView(R.id.button4) Button button4;
        @BindView(R.id.button5) Button button6;
        @BindView(R.id.button7) Button button7;
        @BindView(R.id.button8) Button button8;
    @BindView(R.id.button9)
    public Button button9;
        @BindView(R.id.buttonclr) Button buttonclr;
        @BindView(R.id.buttondel) Button buttondel;
        @BindView(R.id.buttonmul) Button buttonmul;
        @BindView(R.id.buttondevide) Button buttondevide;
        @BindView(R.id.buttonadd) Button buttonadd;
        @BindView(R.id.buttonpercent) Button buttonpercent;
        @BindView(R.id.buttondot) Button buttondot;
        @BindView(R.id.buttoneql)Button buttoneql;
        @BindView(R.id.et1) TextView et1;
        @BindView(R.id.et2)TextView et2;
        @BindView(R.id.buttonhistory)Button buttonhistory;
         int count=0;
         String expression="";
         String text="";
         Double result=0.0;
    DbManager db=new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calci);
        DbManager db=new DbManager(this);
        ButterKnife.bind(this);
        viewall();

    }

        @SuppressLint("SetTextI18n")
        public void onClick (View v){


            switch (v.getId()) {
                case R.id.button0:
                    et2.setText(et2.getText() + "0");
                    break;
                case R.id.button1:
                    et2.setText(et2.getText() + "1");
                    break;

                case R.id.button2:
                    et2.setText(et2.getText() + "2");
                    break;

                case R.id.button3:
                    et2.setText(et2.getText() + "3");
                    break;


                case R.id.button4:
                    et2.setText(et2.getText() + "4");
                    break;

                case R.id.button5:
                    et2.setText(et2.getText() + "5");
                    break;

                case R.id.button6:
                    et2.setText(et2.getText() + "6");
                    break;

                case R.id.button7:
                    et2.setText(et2.getText() + "7");
                    break;

                case R.id.button8:
                    et2.setText(et2.getText() + "8");
                    break;

                case R.id.button9:
                    et2.setText(et2.getText() + "9");
                    break;

                case R.id.buttondot:
                    if (count == 0 && et2.length() != 0) {
                        et2.setText(et2.getText() + ".");
                        count++;
                    }
                    break;

                case R.id.buttondel:

                    text = et2.getText().toString();
                    if (text.length() > 0) {
                        if (text.endsWith(".")) {
                            count = 0;
                        }
                        String newText = text.substring(0, text.length() - 1);
                        //to delete the data contained in the brackets at once
                        if (text.endsWith(")")) {
                            char[] a = text.toCharArray();
                            int pos = a.length - 2;
                            int counter = 1;
                            //to find the opening bracket position
                            for (int i = a.length - 2; i >= 0; i--) {
                                if (a[i] == ')') {
                                    counter++;
                                } else if (a[i] == '(') {
                                    counter--;
                                }
                                //if decimal is deleted b/w brackets then count should be zero
                                else if (a[i] == '.') {
                                    count = 0;
                                }
                                //if opening bracket pair for the last bracket is found
                                if (counter == 0) {
                                    pos = i;
                                    break;
                                }
                            }
                            newText = text.substring(0, pos);
                        }
                        //if e2 edit text contains only - sign or sqrt at last then clear the edit text e2
                        if (newText.equals("-") || newText.endsWith("sqrt")) {
                            newText = "";
                        }
                        //if pow sign is left at the last
                        else if (newText.endsWith("^"))
                            newText = newText.substring(0, newText.length() - 1);
                        et2.setText(newText);
                    }
                    break;
                case R.id.buttonsub:
                    operationClicked("-");
                    break;

                case R.id.buttonadd:
                    operationClicked("+");
                    break;


                case R.id.buttonmul:
                    operationClicked("*");
                    break;

                case R.id.buttondevide:
                    operationClicked("/");
                    break;
                case R.id.buttonclr:
                    et1.setText("");
                    et2.setText("");
                    expression = "";
                    count = 0;
                    break;
                case R.id.buttonbras:
                    et1.setText(et1.getText()+"(");
                    break;

                case R.id.buttonbrace:
                    et1.setText(et1.getText()+")");
                    break;
                case R.id.buttoneql:
                    if (et2.length() != 0) {
                        text = et2.getText().toString();
                        expression = et1.getText().toString() + text;
                    }
                    et1.setText("");
                    if (expression.length() == 0)
                        expression = "0.0";
                    //DoubleEvaluator evaluator = new DoubleEvaluator();
                    try {
                        //evaluate the expression
                        result = new DoubleEvaluator().evaluate(expression);
                        //insert expression and result in sqlite database if expression is valid and not 0.0
                        if (!expression.equals("0.0")){
                            et1.setText(expression );
                            DbManager db=new DbManager(this);
                            et2.setText("="+result+"");
                            String ans=et1.getText().toString();
                            String esp=et2.getText().toString();
                           boolean res1 = db.addRecord(ans,esp);
                            if(res1){
                                Toast.makeText(Basic_Calculator.this,"Data inserted",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(Basic_Calculator.this,"Data not inserted",Toast.LENGTH_SHORT).show();
                            }

                    } }
                    catch (Exception e) {
                        et2.setText("");
                        et1.setText("");
                        expression = "";
                        e.printStackTrace();
                    }
                    break;

            }
        }
        private void operationClicked(String op)
        {
            if(et2.length()!=0)
            {
                String text=et2.getText().toString();
                et1.setText(et1.getText() + text+op);
                et2.setText("");
                count=0;
            }
            else
            {
                String text=et1.getText().toString();
                if(text.length()>0)
                {
                    String newText=text.substring(0,text.length()-1)+op;
                    et1.setText(newText);
                }
            }
        }
    public void viewall(){
        buttonhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.fetchAll();
                StringBuffer buffer = new StringBuffer();
                if (res.getCount() == 0) {
                    show_message("cannot fetch","ohkk");
                    Toast.makeText(Basic_Calculator.this, "nothing to display", Toast.LENGTH_SHORT).show();
                } else

                {
                    while (res.moveToNext()) {
                        buffer.append(res.getString(0));
                        buffer.append("\n");
                        buffer.append(res.getString(1));
                        buffer.append("\n");

                    }

                    show_message(buffer.toString(), "endl");
                }

            }
        });
    }






    public void show_message(String buffer,String result){

        AlertDialog.Builder builder = new AlertDialog.Builder(Basic_Calculator.this);
        builder.setCancelable(true);
        builder.setTitle("HISTORY");
        builder.setMessage(buffer);
        builder.show();

        }

        }


