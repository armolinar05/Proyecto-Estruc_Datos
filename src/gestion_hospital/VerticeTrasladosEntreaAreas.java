package gestion_hospital;

public class VerticeTrasladosEntreaAreas {
    String AreaTraslado;
    int numArea;
    
    public VerticeTrasladosEntreaAreas(String AreaTraslado){
        this.AreaTraslado = AreaTraslado;
        this.numArea = -1;
    }
    
    public String nombreDelAreaDeTraslado (){
        return AreaTraslado;
    }
    public boolean equals(VerticeTrasladosEntreaAreas n){
        return AreaTraslado.equals(n.AreaTraslado);
    }
    public void asignarId (int n){
        numArea = n; 
    }
    @Override
    public String toString() {
    return AreaTraslado + " ("+ numArea +")";
    }
  
}
