package vectores;

import java.util.Scanner;
import static vectores.CalculadoraVectorial.vectores;

public class Vectores {

    static Scanner scaner = new Scanner(System.in);
    static CalculadoraVectorial vectores = new CalculadoraVectorial();
    
    public static void main(String[] args) {
        System.out.println("-----------| Matrices |-----------");
        
        vectores.crear(3, new double[]{2,-1,3}, "A");
        vectores.crear(3, new double[]{0,2,-5}, "B");
        vectores.crear(3, new double[]{1,-1,-2}, "C");
        
        menu();
    }
    
    public static void menu(){
        System.out.println("\nOpciones:\n"
                + "1. Introducir vector\n"
                + "2. Eliminar vector\n"
                + "3. Mostrar vector\n"
                + "\n"
                + "4. Angulo que forman dos vectores\n"
                + "5. Producto escalar\n"
                + "6. Producto vectorial\n"
                + "7. Producto mixto de 3 vectores\n"
                + "8. \n"
                + "\n"
                + "9. \n"
                + "10. \n"
                + "\n"
                + "11. \n"
                + "12. \n"
                + "\n"
                + "13. \n"
                + "14. \n"
                + "0. Salir");
        
        switch(scaner.nextInt()){
            case 0:
                System.exit(0);
                break;
            case 1:
                introducir();
                break;
            case 2:
                eliminar();
                break;
            case 3:
                mostrar();
                break;
            case 4:
                angulo();
                break;
            case 5:
                productoScalar();
                break;
            case 6:
                productoVectorial();
                break;
            case 7:
                productoMixto();
                break;
        }
        menu();
    }
    
    public static void introducir(){
        System.out.print("\nIntroduce el numero de dimensiones: ");
        int D = scaner.nextInt();
        double[] V = new double[D];
        for(int i = 0; i < D; i++){
            System.out.print("Introduce su valor en la dimension "+(i+1)+": ");
            V[i] = scaner.nextDouble();
        }
        System.out.print("Introduce el nombre del vector: ");
        vectores.crear(D,V,scaner.next());
        System.out.println("Nuevo vector creado.");
    }
    public static void eliminar(){
        vectores.eliminar(seleccionar());
    }
    public static void mostrar(){
        System.out.println("\n"+vectores.getDatosVector(seleccionar()));
    }
    
    public static void angulo(){
        System.out.println("\nSelecciona el primer vector.");
        int v1 = seleccionar();
        System.out.println("Selecciona el segundo vector");
        System.out.println("\nResultado: "+vectores.anguloDosVectores(v1, seleccionar()));
    }
    public static void productoScalar(){
        System.out.println("\nSelecciona el primer vector.");
        int v1 = seleccionar();
        System.out.println("Selecciona el segundo vector");
        System.out.println("\nResultado: "+vectores.productoEscalar(v1, seleccionar()));
    }
    public static void productoVectorial(){
        System.out.println("\nSelecciona el primer vector.");
        int v1 = seleccionar();
        System.out.println("Selecciona el segundo vector");
        double[] V = vectores.productoVectorial(v1, seleccionar());
        if(V == null){
            System.out.println("Los vectores no tienen las mismas dimensiones o no llegan a 3.");
        }else{
            mostrarGuardar(V);
        }
    }
    public static void productoMixto(){
        System.out.println("\nSelecciona el primer vector.");
        int v1 = seleccionar();
        System.out.println("\nSelecciona el segundo vector.");
        int v2 = seleccionar();
        System.out.println("Selecciona el tercer vector");
        String resultado = vectores.productoMixto(v1, v2, seleccionar());
        if(resultado == null){
            System.out.println("No se pudo realizar la operación.");
        }else{
            System.out.println("\nResultado: "+resultado);
        }
    }
    
    public static int seleccionar(){
        System.out.println();
        for(int i = 0; i < vectores.getNumeroVectores(); i++){
            System.out.println((i+1)+". "+vectores.getNombreVector(i));
        }
        System.out.print("Introduce el numero del vector: ");
        int n = scaner.nextInt();
        if(n <= vectores.getNumeroVectores()){
            return n-1;
        }else{
            System.out.println("Error en la seleccion, vuelve a introducir un valor dentro de los permitidos");
            return seleccionar();
        }
    }
    public static void mostrarGuardar(double[] V){
        if(V == null){
            System.out.println("Error en la operación.");
        }else{
            String valores = "";
            for(int i = 0; i < V.length; i++){
                valores += vectores.simplificar(V[i])+" ";
            }
            System.out.println("Resultado:  Dimensiones: "+V.length+" Valores: "+valores);
        }
        System.out.println("\nSelecciona una acción.\n"
                + "1. Guardar Vector\n"
                + "2. Volver al menú");
        if(scaner.nextInt() == 1){
            System.out.print("Introduce el nombre del vector: ");
            vectores.crear(V.length, V, scaner.next());
        }
    }
}
