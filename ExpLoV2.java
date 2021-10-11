package menu;

import java.util.ArrayList;

public class ExpLoV2 {

    public void LeerCadena(String cadena) {
        ArrayList<String> Entero = new ArrayList<String>();
        ArrayList<String> Caracter = new ArrayList<String>();
        ArrayList<String> Cadena = new ArrayList<String>();
        String [] separador=cadena.split("\\s");
        int contE=0,contC=0,contS=0;
        for (int i = 0; i < separador.length; i++) {
            if(separador[i].matches("^[0-9]{1,}$")){
                contE++;
                Entero.add(separador[i]);
            }else if(separador[i].length()==1){
                contC++;
                Caracter.add(separador[i]);
            }else{
                contS++;
                Cadena.add(separador[i]);
            }
        }
        System.out.println("Los enteros son: "+Entero.toString()+" y son: "+contE+" en total");
        System.out.println("Los caractres son: "+Caracter.toString()+" y son: "+contC+" en total");
        System.out.println("Las cadenas son: "+Cadena.toString()+" y son: "+contS+" en total");
    }
}
