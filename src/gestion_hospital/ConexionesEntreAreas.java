package gestion_hospital;


public class ConexionesEntreAreas {
    
    int total;
    String[] nombresDeArea;
    TrasladosEntreaAreas [] cabeza;
    
    public ConexionesEntreAreas(int total){
        this.total = total;
        nombresDeArea = new String[total];
        cabeza = new TrasladosEntreaAreas [total];
    }
    
    public void agregaArea (int id, String nombresArea){
        nombresDeArea[id] = nombresArea;
        cabeza[id] = null;
        
    }
    
    public void trasladar (int Origen, int idDestino, Paciente idPaciente){
     TrasladosEntreaAreas nuevo = new TrasladosEntreaAreas(nombresDeArea[idDestino], idPaciente.getId());
    
    }
    
    
    
    
    
}
