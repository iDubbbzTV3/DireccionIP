package com.example.bruce.direccionip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public class IPv4 {

        public String firstIP(String ip, String subnet) {

            //Paso el String a un arreglo separando por los puntos
            String[] octetos = ip.split(java.util.regex.Pattern.quote("."));

            //Creo enteros con el valor de cada String partida
            int num1 = Integer.parseInt(octetos[0]);
            int num2 = Integer.parseInt(octetos[1]);
            int num3 = Integer.parseInt(octetos[2]);
            int num4 = Integer.parseInt(octetos[3]);

            int subnet0 = Integer.parseInt(subnet);

            //Paso los enteros a Strings binarias, cada una con 8 cifras e.g (00011010)
            String binaryString1 = Integer.toBinaryString(num1);
            binaryString1 = binaryString1.substring(binaryString1.length() - 8);

            String binaryString2 = Integer.toBinaryString(num2);
            binaryString2 = binaryString2.substring(binaryString2.length() - 8);

            String binaryString3 = Integer.toBinaryString(num3);
            binaryString3 = binaryString3.substring(binaryString3.length() - 8);

            String binaryString4 = Integer.toBinaryString(num4);
            binaryString4 = binaryString4.substring(binaryString4.length() - 8);

            //Tomo las Strings y creo una unica String e.g(11111111111111110000000011111111)
            String direccionIP = new String(binaryString1 + binaryString2 + binaryString3 + binaryString4);

            //Convierto el String en un arreglo de caracteres
            char[] charArray = direccionIP.toCharArray();

            //En el for hago la operacion de subnet para hacer todos los ultimos numeros "0"
            for(int i = subnet0; i < 32; i++){
                charArray[i] = '0';
            }

            //Parto el Array de 32 caracteres a 4 arrays de 8 caracteres (para agregarle el punto)
            char[] char1 = new char[8];
            char[] char2 = new char[8];
            char[] char3 = new char[8];
            char[] char4 = new char[8];

            for (int i = 0; i < 8; i++){
                char1[i] = charArray[i];
            }
            for (int i = 0; i < 8; i++){
                char2[i] = charArray[i+8];
            }
            for (int i = 0; i < 8; i++){
                char3[i] = charArray[i+16];
            }
            for (int i = 0; i < 8; i++){
                char4[i] = charArray[i+24];
            }

            //Hago el ultimo caracter un '1' para tener el primer numero de red
            char4[7] = '1';

            //Paso los arreglos de caracteres a Strings
            String caracter1 = new String(char1);
            String caracter2 = new String(char2);
            String caracter3 = new String(char3);
            String caracter4 = new String(char4);

            //Paso los Strings de binarios a Integers
            int foo1 = Integer.parseInt(caracter1, 2);
            int foo2 = Integer.parseInt(caracter2, 2);
            int foo3 = Integer.parseInt(caracter3, 2);
            int foo4 = Integer.parseInt(caracter4, 2);

            //Ahora uno los arrays y les agrego punto entre medio

            direccionIP = new String(foo1+"."+foo2+"."+foo3+"."+foo4);

            return direccionIP;
        }
    }
}
