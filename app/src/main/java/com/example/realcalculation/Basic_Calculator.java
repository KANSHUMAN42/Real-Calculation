package com.example.realcalculation;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
         int count=0;
         String expression="";
         String text="";
         Double result=0.0;
        @BindView(R.id.buttonhistory) Button buttonhistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calci);

        ButterKnife.bind(this);
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
                    DoubleEvaluator evaluator = new DoubleEvaluator();
                    try {
                        //evaluate the expression
                        result = new DoubleEvaluator().evaluate(expression);
                        //insert expression and result in sqlite database if expression is valid and not 0.0
                        if (!expression.equals("0.0"))
                            //dbHelper.insert("STANDARD",expression+" = "+result);
                            et1.setText(expression + "=");
                        et2.setText(result + "");
                    } catch (Exception e) {
                        et2.setText("Invalid Expression");
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
    }

