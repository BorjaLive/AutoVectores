package vectores;

public class Vector {
    
    private int dimensiones;
    private double[] vector;
    private String nombre;
    private double modulo;
    
     public Vector(int dimensiones, double[] vector, String nombre){
         this.dimensiones = dimensiones;
         this.vector = vector;
         this.nombre = nombre;
         this.modulo = calcularModulo();
     }
     
     private double calcularModulo(){
         double sumatoria = 0;
         for(int i = 0; i < dimensiones; i++){
             sumatoria += vector[i]*vector[i];
         }
         return Math.sqrt(sumatoria);
     }
     
     public void setD(int dimensiones){
         this.dimensiones = dimensiones;
     }
     public void setV(double[] vector){
         this.vector = vector;
     }
     public void setN(String nombre){
         this.nombre = nombre;
     }
     public int getD(){
         return dimensiones;
     }
     public double[] getV(){
         return vector;
     }
     public String getN(){
         return nombre;
     }
     public double getM(){
         return modulo;
     }
}
