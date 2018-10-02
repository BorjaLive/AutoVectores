package vectores;

import java.util.ArrayList;

public class CalculadoraVectorial {
    
    static ArrayList <Vector> vectores = new ArrayList<>();
    
    public int crear(int D, double[] V, String N){
        vectores.add(new Vector(D, V, N));
        return vectores.size();
    }
    public void eliminar(int n){
        vectores.remove(n);
    }
    public String getDatosVector(int n){
        String valores = "";
        for(int i = 0; i < vectores.get(n).getD(); i++){
            valores += simplificar(vectores.get(n).getV()[i])+" ";
        }
        return vectores.get(n).getN()+" Dimensiones: "+vectores.get(n).getD()+" Valores: "+valores+" "+" Modulo: "+simplificar(vectores.get(n).getM());
    }
    
    public String anguloDosVectores(int v1, int v2){
        if(vectores.get(v1).getD() != vectores.get(v1).getD()){
            return "Los vectores no tienen las mismas dimensiones.";
        }
        return _simplificarAngulos(_grados(Math.acos(_multiplicarVectores(new int[] {v1,v2})/_multiplicarModulos(new int[] {v1,v2}))));
    }
    public String productoEscalar(int v1, int v2){
        if(vectores.get(v1).getD() != vectores.get(v1).getD()){
            return "Los vectores no tienen las mismas dimensiones.";
        }
        return simplificar(_multiplicarVectores(new int[] {v1,v2}));
        //return _simplificar(_multiplicarModulos(new int[] {v1,v2})*(_multiplicarVectores(new int[] {v1,v2})/_multiplicarModulos(new int[] {v1,v2})));
    }
    public double[] productoVectorial(int v1, int v2){
        if(vectores.get(v1).getD() != vectores.get(v1).getD() || vectores.get(v1).getD() < 3){
            return null;
        }
        double[][] M = new double[3][vectores.get(v1).getD()];
        for(int i = 0; i < vectores.get(v1).getD(); i++){
            M[1][i] = vectores.get(v1).getV()[i];
        }
        for(int i = 0; i < vectores.get(v1).getD(); i++){
            M[2][i] = vectores.get(v2).getV()[i];
        }
        Matriz matriz = new Matriz(M,3,vectores.get(v1).getD(),"");
        double[] V = new double[vectores.get(v1).getD()];
        for(int i = 0; i < vectores.get(v1).getD(); i++){
            V[i] = _calcularDeterminanate(new Matriz(_extraerMenor(matriz,0,i),2,vectores.get(v1).getD()-1,""));
        }
        return V;
    }
    public String productoMixto(int v1, int v2, int v3){
        if(vectores.get(v1).getD() != vectores.get(v1).getD() || vectores.get(v1).getD() != vectores.get(v3).getD() || vectores.get(v1).getD() < 3){
            return null;
        }
        double[][] M = new double[3][vectores.get(v1).getD()];
        for(int i = 0; i < vectores.get(v1).getD(); i++){
            M[0][i] = vectores.get(v1).getV()[i];
        }
        for(int i = 0; i < vectores.get(v1).getD(); i++){
            M[1][i] = vectores.get(v2).getV()[i];
        }
        for(int i = 0; i < vectores.get(v1).getD(); i++){
            M[2][i] = vectores.get(v3).getV()[i];
        }
        return simplificar(_calcularDeterminanate(new Matriz(M,3,vectores.get(v1).getD(),"")));
    }
    
    public double _multiplicarModulos(int[] V){
        double resultado = 1;
        for(int i = 0; i < V.length; i++){
            resultado *= vectores.get(V[i]).getM();
        }
        return resultado;
    }
    public double _multiplicarVectores(int[] V){
        double resultado = 0;
        double parcial;
        for(int j = 0; j < vectores.get(V[0]).getD(); j++){
            parcial = 1;
            for(int i = 0; i < V.length; i++){
                parcial *= vectores.get(V[i]).getV()[j];
            }
            resultado += parcial;
        }
        return resultado;
    }
    
    private double _calcularDeterminanate(Matriz matriz){
        double[][] M = _obtener_copia(matriz);
        
        if(matriz.getFilas() == 1 && matriz.getColumnas() == 1){
            return M[0][0];
        }else if(matriz.getFilas() == 2 && matriz.getColumnas() == 2){
            return (M[0][0]*M[1][1])-(M[0][1]*M[1][0]);
        }else if(matriz.getFilas() == 3 && matriz.getColumnas() == 3){
            return ((M[0][0]*M[1][1]*M[2][2])+(M[0][1]*M[1][2]*M[2][0])+(M[1][0]*M[2][1]*M[0][2]))-((M[0][2]*M[1][1]*M[2][0])+(M[0][1]*M[1][0]*M[2][2])+(M[1][2]*M[2][1]*M[0][0]));
        }else{
            double d = 0;
            for(int i = 0; i < matriz.getFilas(); i ++){
                for(int j = 0; j < matriz.getColumnas(); j ++){
                    d += M[i][j]*_calcularAdjunto(matriz,i,j);
                }
            }
            return d;
        }
    }
    private double _calcularAdjunto(Matriz matriz, int i, int j){
        double[][] M = _extraerMenor(matriz,i,j);
        double d = _calcularDeterminanate(new Matriz(M, matriz.getFilas()-1, matriz.getColumnas()-1, ""));
        return _par(i+j)?d:d*-1;
    }
    private double[][] _extraerMenor(Matriz matriz, int f, int c){
        double[][] M = new double[matriz.getFilas()-1][matriz.getColumnas()-1];
        
        int ii = 0;
        int jj;
        for(int i = 0; i < matriz.getFilas(); i++){
            if(i != f){
                jj = 0;
                for(int j = 0; j < matriz.getColumnas(); j++){
                    if(j != c){
                        M[ii][jj] = matriz.getMatriz()[i][j];
                        jj++;
                    }
                }
                ii++;
            }
        }
        
        return M;
    }
    private double[][] _obtener_copia(Matriz m){
        double[][] nueva = new double[m.getFilas()][m.getColumnas()];
        for(int i = 0; i < m.getFilas(); i++){
            for(int j = 0; j < m.getColumnas(); j++){
                nueva[i][j] = m.getMatriz()[i][j];
            }
        }
        return nueva;
    }
    
    public int getNumeroVectores(){
        return vectores.size();
    }
    public String getNombreVector(int n){
        return vectores.get(n).getN();
    }
    
    
    
    private double _rad(double grados){
        return (grados*Math.PI)/180;
    }
    private double _grados(double radianes){
        return radianes/(Math.PI/180);
    }
    private boolean _par(int n){
        String s = String.valueOf(n);
        char c = s.charAt(s.length()-1);
        return c == '0' || c == '2' || c == '4' || c == '6' || c == '8';
    }
    public String simplificar(double n){
        if(n-(int)n == 0){
            return Integer.toString((int)n);
        }
        
        if(n == Math.PI){
            return "π";
        }else if(n == Math.E){
            return "e";
        }
        
        for(int i = 0; i < 1024; i++){
            if(Double.compare((double)Math.sqrt(i), n) == 0){
                return("√"+i);
            }
        }
        double original = n;
        int b = 1;
        while (true){
           n *= b;
           if(n-(int)n == 0){
               break;
           }else{
               n = original;
               b++;
           }
           if(Double.isInfinite(n)){
                return String.valueOf(original);
           }
        }
        
        return (int)n+"/"+b;
    }
    private String _simplificarAngulos(double n){
        int grados = 0;
        int minutos = 0;
        int segundos = 0;
        while(n >= 1){
            n --;
            grados ++;
        }
        n *= 60;
        while(n >= 1){
            n --;
            minutos ++;
        }
        n *= 60;
        while(n >= 1){
            n --;
            segundos ++;
        }
        return grados+"º "+minutos+"' "+segundos+"''";
    }
}