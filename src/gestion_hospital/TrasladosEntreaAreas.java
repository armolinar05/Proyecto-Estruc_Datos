package gestion_hospital;

public class TrasladosEntreaAreas {
    String AreaTraslado;
    int idPaciente;
    TrasladosEntreaAreas siguiente; 
    
    public TrasladosEntreaAreas(String AreaTraslado, int idPaciente){
        this.AreaTraslado = AreaTraslado;
        this.idPaciente = idPaciente;
        this.siguiente = null;
    
    }
    
    
    
    
}
