package menu;

import java.util.Scanner;

public class principal {
    
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int op;
        boolean seguir = true;
        while (seguir) {
            System.out.println("Menu\n 1.- Expresión Aritmética\n 2.- Expresión Lógica V1\n 3.- Expresión Lógica V2\n 0.- Salir");
            op = leer.nextInt();
            leer.nextLine();
            switch (op) {
                case 1:
                    ExpArit obj1=new ExpArit();
                    System.out.println("Ingresa Expresión Aritmética:");
                    String Expresion=leer.next();
                    obj1.Operacion(Expresion);
                    break;
                case 2:
                    ExpLoV1 obj2 = new ExpLoV1();
                    System.out.println("NOT ¬   AND ^   OR v    Condicional ->  Bicondicional <->");
                    System.out.println("Ingrese Expresión Lógica (Utilizar MAYUSCULAS):");
                    String log=leer.next();
                    obj2.ExpLogica(log);
                    break;
                case 3:
                    ExpLoV2 obj3 = new ExpLoV2();
                    System.out.println("Ingresa tus valores (Separado por un espacio):");
                    String valor=leer.nextLine();
                    obj3.LeerCadena(valor);
                    break;
                default:
                    seguir = false;
                    System.out.println("Terminado");
                    break;
            }
        }
    }
}
