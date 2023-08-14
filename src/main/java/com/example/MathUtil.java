package com.example;

public class MathUtil {
    public static int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        int temp = Math.max(a, b);
        b = Math.min(a, b);
        a = temp;

        if(b > 0 && a%b == 0) {
            return b;
        }

        if(b == 0) {
            return Math.abs(a);
        }

        return mdc(a - b, b);
    }  
    
    public static int mdc(int ...valores) {
        if(valores == null)
            throw new NullPointerException("É necessário usar um valor diferente de null");

        if(valores.length == 0)
            throw new IllegalArgumentException("É necessário ao menos um valor para calcular o MDC.");

        int a = valores[0];
        for (int b: valores) {
            a = mdc(a, b);
        }
        return a;
    }
}
