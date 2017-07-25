package com.example.dineshvarma.temperatureconverter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    String[] names={"Celsius","Fahrenheit","kelvin"};
    TextView value1, value2;
    boolean state1, num1, dot1, state2, num2, dot2, v1=true, pluse1=true, pluse2=true;
    int a=0, b=1;
    Converter c = new Converter();
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        value1 = (TextView) findViewById(R.id.value1);
        value2 = (TextView) findViewById(R.id.value2);
        spinner1.setAdapter(new CustomAdapter(this));
        spinner2.setAdapter(new CustomAdapter(this));

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(50);
                TextView textView = (TextView) findViewById(R.id.name1);
                textView.setText(names[i]);
                a=i;
                result();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(50);
                TextView textView = (TextView) findViewById(R.id.name2);
                textView.setText(names[i]);
                b=i;
                result();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setSelection(0);
        spinner2.setSelection(1);
        result();

    }

    public void acclick(View view) {
        vibrator.vibrate(50);
        if (v1){
            value1.setText("0");
            state1 = false;
            dot1 = false;
            num1 = false;

        }else {
            value2.setText("0");
            state2 = false;
            dot2 = false;
            num2 = false;

        }
        result();


    }

    public void backspace(View view) {
        vibrator.vibrate(50);
        if (v1){
            String text = value1.getText().toString();
            if (text.length()>0){
                char check = text.charAt(text.length()-1);
                if (check=='.'){
                    dot1 = false;
                }
                text = text.substring(0, text.length()-1);
                value1.setText(text);
            }if (text.length()==0){
                value1.setText("0");
                state1 = false;
                dot1 = false;
                num1 = false;
            }
        }else {
            String text = value2.getText().toString();
            if (text.length()>0){
                char check = text.charAt(text.length()-1);
                if (check=='.'){
                    dot2 = false;
                }
                text = text.substring(0, text.length()-1);
                value2.setText(text);
            }if (text.length()==0){
                value2.setText("0");
                state2 = false;
                num2 = false;
                dot2 = false;
            }

        }
        result();

    }

    public void pluseorminus(View view) {
        vibrator.vibrate(50);
        if (v1){
              if (!num1 && !dot1) {
                  if (pluse1) {
                      value1.setText("-");
                      pluse1 = false;
                  } else {
                      value1.setText("");
                      pluse1 = true;
                  }
              }
              state1 =true;

          }else {
              if (!num2 && !dot2) {
                  if (pluse2) {
                      value2.setText("-");
                      pluse2 = false;
                  } else {
                      value2.setText("");
                      pluse2 = true;
                  }
              }
              state2 =true;

          }
        }



    public void numclick(View view) {
        vibrator.vibrate(50);

        if (v1) {
            if (value1.length() < 10) {
                if (state1) {
                    value1.append(view.getTag().toString());
                } else {
                    value1.setText(view.getTag().toString());
                }
                state1 = true;
                num1 = true;
            }
        }else {
            if (value2.length() < 10) {
                if (state2) {
                    value2.append(view.getTag().toString());
                } else {
                    value2.setText(view.getTag().toString());

                }
                state2 = true;
                num2 = true;
            }
        }
        result();
    }

    public void dotclick(View view) {
        vibrator.vibrate(50);

        if (v1) {
                if (!dot1) {
                    value1.append(".");
                }
                dot1 = true;
                num1 = false;
            }else {
                if(!dot2){
                value2.append(".");
            }
                dot2 = true;
                num2 = false;
        }

    }

    public void ans1click(View view) {
        vibrator.vibrate(50);
        v1 = true;
        value1.setTextColor(Color.parseColor("#FF5722"));
        value2.setTextColor(Color.parseColor("#000000"));


    }

    public void ans2click(View view) {
        vibrator.vibrate(50);
        v1 = false;
        value2.setTextColor(Color.parseColor("#FF5722"));
        value1.setTextColor(Color.parseColor("#000000"));
    }
    public void result() {
        Double ans;
        if (v1) {
            if (value1 != null && value1.length() != 0) {
                Double d = Double.valueOf(value1.getText().toString());
                if (a == 0 && b == 1) {
                    ans = c.cf(d);
                } else if (a == 0 && b == 2) {
                    ans = c.ck(d);
                } else if (a == 1 && b == 0) {
                    ans = c.fc(d);
                } else if (a == 1 && b == 2) {
                    ans = c.fk(d);
                } else if (a == 2 && b == 0) {
                    ans = c.kc(d);
                } else if (a == 2 && b == 1) {
                    ans = c.kf(d);
                } else {
                    ans = Double.valueOf(value1.getText().toString());
                }
                value2.setText(new DecimalFormat("##.####").format(ans));
            }else {
                value2.setText("");
            }
        } else {
            if (value2 != null && value1.length() != 0) {
                Double d = Double.valueOf(value2.getText().toString());

                if (a == 0 && b == 1) {
                    ans = c.fc(d);
                } else if (a == 0 && b == 2) {
                    ans = c.kc(d);
                } else if (a == 1 && b == 0) {
                    ans = c.cf(d);
                } else if (a == 1 && b == 2) {
                    ans = c.kf(d);
                } else if (a == 2 && b == 0) {
                    ans = c.ck(d);
                } else if (a == 2 && b == 1) {
                    ans = c.fk(d);
                } else {
                    ans = Double.valueOf(value2.getText().toString());
                }
                value1.setText(new DecimalFormat("##.####").format(ans));
            }else {
                value1.setText("");
            }
        }
    }
}
