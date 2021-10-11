package menu;

import java.util.Stack;

public class ExpArit {

    public void Operacion(String cadena) {
        String AritE = Espacio(cadena);
        Stack<String> cifras = new Stack<String>();
        if (AritE != "0") {
            String OPost = OrdenPost(AritE);
            String Cifras[] = OPost.split("\\s");
            for (int i = 0; i < Cifras.length; i++) {
                if (Cifras[i].matches("^[0-9]{1,}$") || Cifras[i].matches("^[0-9]{1,}\\.[0-9]{1,}$") || Cifras[i].matches("^\\.[0-9]{1,}$")) {
                    cifras.push(Cifras[i]);
                } else if (Cifras[i].matches("^[*|+|\\-|/|\\^]{1,}$")) {
                    String v2 = cifras.pop();
                    String v1 = cifras.pop();
                    String nuevo = OperBasic(Cifras[i], v1, v2);
                    cifras.push(nuevo);
                } else {
                    System.out.println("No se puede resolver, revise la expresión");
                }
            }
            String result = cifras.pop();
            System.out.println("Notación Postfija: " + OPost);
            System.out.println("Resultado: " + result);
        } else {
            System.out.println("No se puede resolver, revise la expresión");
        }
    }

    public static String OperBasic(String op, String v1, String v2) {
        String resul = "";
        double oper = 0;
        double va1 = Double.parseDouble(v1);
        double va2 = Double.parseDouble(v2);
        switch (op) {
            case "+":
                oper = va1 + va2;
                break;
            case "-":
                oper = va1 - va2;
                break;
            case "*":
                oper = va1 * va2;
                break;
            case "/":
                oper = va1 / va2;
                break;
            case "^":
                oper = Math.pow(va1, va2);
                break;
            default:
                System.out.println("No se puede resolver, revise la expresión");
                break;
        }
        return resul + oper;
    }

    public static String Espacio(String cadena) {
        String valor = "";
        String caracter[] = cadena.split("");
        int entrada = 0, cierre = 0;
        for (int i = 0; i < caracter.length; i++) {
            if (caracter[i].matches("^([0-9]|\\.){1,}$")) {
                valor += caracter[i];
            } else {
                if (caracter[i].matches("^\\($")) {
                    valor = valor + " " + caracter[i] + " ";
                    entrada++;
                } else if (caracter[i].matches("^\\)$")) {
                    valor = valor + " " + caracter[i] + " ";
                    cierre++;
                } else {
                    valor = valor + " " + caracter[i] + " ";
                }
            }
        }
        if (entrada == cierre) {
            valor = valor.replaceAll("\\s+", " ");
            valor = valor.trim();
            return valor;
        } else {
            return "0";
        }
    }

    public static String OrdenPost(String exp) {
        Stack<String> operacion = new Stack<String>();
        Stack<String> operadores = new Stack<String>();
        Stack<String> Aux = new Stack<String>();
        String Exp[] = exp.split("\\s");
        String Post = "";
        int Presedencia = 0;
        //  4   3    2   1
        //  (   ^   */     +-
        // 1. Precedencia igual se cambian
        // 2. Precedencia menor se agrega a la pila
        // 3. Presedencia mayor se sacan valores de la pila
        for (int i = 0; i < Exp.length; i++) {
            if (Exp[i].matches("^[0-9]{1,}$") || Exp[i].matches("^[0-9]{1,}\\.[0-9]{1,}$") || Exp[i].matches("^\\.[0-9]{1,}$")) {
                operacion.push(Exp[i]);
            } else if (Exp[i].matches("^\\($")) {//Parentesis
                if (Presedencia == 4) {
                    operadores.push(Exp[i]);
                } else if (Presedencia < 4) {
                    operadores.push(Exp[i]);
                } else {
                    System.out.println("Revisa la expresión 1");
                }
                Presedencia = 4;
            } else if (Exp[i].matches("^\\^$")) {//Potencia
                if (Presedencia == 3) {
                    operacion.push(operadores.pop());
                    operadores.push(Exp[i]);
                } else if (Presedencia < 3) {
                    operadores.push(Exp[i]);
                } else if (Presedencia == 4) {
                    operadores.push(Exp[i]);
                } else {
                    System.out.println("Revisa la expresión 2");
                }
                Presedencia = 3;
            } else if (Exp[i].matches("^[\\*||\\/]$")) {//Multiplicación y división
                if (Presedencia == 2) {
                    operacion.push(operadores.pop());
                    operadores.push(Exp[i]);
                } else if (Presedencia < 2) {
                    operadores.push(Exp[i]);
                } else if (Presedencia > 2 && Presedencia != 4) {
                    for (int j = operadores.size(); j > 0; j--) {
                        String operador = operadores.pop();
                        if (operador.matches("^\\($")) {
                            operadores.push(operador);
                            j = 0;
                        } else {
                            operacion.push(operador);
                        }
                    }
                    operadores.push(Exp[i]);
                } else if (Presedencia == 4) {
                    operadores.push(Exp[i]);
                } else {
                    System.out.println("Revisa la expresión 3");
                }
                Presedencia = 2;
            } else if (Exp[i].matches("^[\\+||\\-]$")) {//Suma y resta
                if (Presedencia == 1) {
                    operacion.push(operadores.pop());
                    operadores.push(Exp[i]);
                } else if (Presedencia < 1) {
                    operadores.push(Exp[i]);
                } else if (Presedencia > 1 && Presedencia != 4) {
                    for (int j = operadores.size(); j > 0; j--) {
                        String operador = operadores.pop();
                        if (operador.matches("^\\($")) {
                            operadores.push(operador);
                            j = 0;
                        } else {
                            operacion.push(operador);
                        }
                    }
                    operadores.push(Exp[i]);
                } else if (Presedencia == 4) {
                    operadores.push(Exp[i]);
                } else {
                    System.out.println("Revisa la expresión 4 " + i + " " + Presedencia);
                }
                Presedencia = 1;
            } else if (Exp[i].matches("^\\)$")) {
                for (int j = operadores.size(); j > 0; j--) {
                    String operador = operadores.pop();
                    if (operador.matches("^\\($")) {
                        if (!operadores.empty()) {
                            String OperAux = operadores.pop();
                            if (OperAux.matches("^\\($")) {
                                Presedencia = 4;
                                operadores.push(OperAux);
                            } else if (OperAux.matches("^\\^$")) {
                                Presedencia = 3;
                                operadores.push(OperAux);
                            } else if (OperAux.matches("^[\\*||\\/]$")) {
                                Presedencia = 2;
                                operadores.push(OperAux);
                            } else if (OperAux.matches("^[\\+||\\-]$")) {
                                Presedencia = 1;
                                operadores.push(OperAux);
                            } else {
                                System.out.println("Revisa la expresión 5");
                            }
                            j = 0;
                        } else {
                            Presedencia = 0;
                            j = 0;
                        }
                    } else {
                        operacion.push(operador);
                    }
                }
            } else {
                System.out.println("Revisa la expresión 6 ");
            }
        }
        if (!operadores.empty()) {
            for (int i = operadores.size(); i > 0; i--) {
                operacion.push(operadores.pop());
            }
        }
        for (int i = operacion.size(); i > 0; i--) {
            Aux.push(operacion.pop());
        }
        for (int i = Aux.size(); i > 0; i--) {
            Post = Post + Aux.pop() + " ";
        }
        return Post;
    }
}
