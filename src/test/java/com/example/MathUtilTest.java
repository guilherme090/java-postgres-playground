package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static com.example.MathUtil.mdc;

public class MathUtilTest {
    @Test
    void testMdcP1BPar() {
        int a = 4, b = 2;
        int esperado = 2;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP1BImpar() {
        int a = 9, b = 3;
        int esperado = 3;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP3APos() {
        int a = 946, b = 0;
        int esperado = 946;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP3ANeg() {
        int a = -216, b = 0;
        int esperado = 216;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP6() {
        int a = 3, b = 9;
        int esperado = mdc(a, b);
        int obtido = mdc(b, a);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP7ANegBPos() {
        int a = -3, b = 9;
        int esperado = 3;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP7APosBNeg() {
        int a = 3, b = -9;
        int esperado = 3;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP8() {
        int a = 6;
        int esperado = 6;
        int obtido = mdc(a, a);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP8Negativo() {
        int a = -6;
        int esperado = 6;
        int obtido = mdc(a, a);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP12DoisPrimos() {
        int p = 7, a = p;
        int esperado = p;
        int obtido = mdc(p, a);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP12DoisPrimosDiferentes() {
        int p = 7, a = 13;
        int esperado = 1;
        int obtido = mdc(p, a);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcCasoGeral() {
        int a = 12, b = 9;
        int esperado = 3;
        int obtido = mdc(a, b);

        assertEquals(esperado, obtido);
    }

    @Test
    void testMdcP4() {
        int m = 2, a = 6, b = 3;
        int esperado = 6;
        int obtido = m * mdc(a, b);

        assertEquals(mdc(m*a, m*b), obtido);
        assertEquals(esperado, obtido);
    }

    @Test
    void testMdc3Valores() {
        int a = 12, b = 6, c = 4;
        int esperado = 2;
        int obtido = mdc(a, b, c);

        assertEquals(esperado, obtido);
    } 

    @Test
    void testMdcNenhumValor() {
        var exception = assertThrows(IllegalArgumentException.class, () -> mdc() );
        System.out.println(exception.getMessage());
    } 

    @Test
    void testMdcP9() {
        int a = 6, b = 3, c = 2;

        assertTrue(mdc(a, mdc(b, c)) == mdc(mdc(a, b), c) && mdc(mdc(a, b), c) == mdc(a, b, c));
    } 

    // outra forma:
    @Test
    void testMdcNenhumValorOutraForma() {
        var exception = assertThrows(IllegalArgumentException.class, MathUtil::mdc );
        System.out.println(exception.getMessage());
    }

    @Test
    void testMdcNull() {
        var exception = assertThrows(NullPointerException.class, () -> mdc(null));
        var msgEsperada = "É necessário usar um valor diferente de null";
        assertEquals(msgEsperada, exception.getMessage());
    }

}
