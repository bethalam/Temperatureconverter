package com.example.dineshvarma.temperatureconverter;

public class Converter {
    public Double fc(double f){
        double c = (f-32)* 5/9;
        return c;
    }
    public Double cf(double c){
        double f = (c*9/5)+32;
        return f;
    }
    public Double ck(double c){
        double k = c+273.15;
        return k;
    }
    public Double fk(double f){
        double k = ((f-32)*5/9)+273.15;
        return k;
    }
    public Double kc(double k){
        double c = k-273.15;
        return c;
    }
    public Double kf(double k){
        double f = (k-273.15)*9/5+32;
        return f;
    }
}
