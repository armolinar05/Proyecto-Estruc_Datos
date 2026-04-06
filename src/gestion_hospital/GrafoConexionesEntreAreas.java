package gestion_hospital;


public class GrafoConexionesEntreAreas {
    private static int MAX_AREAS = 20;
    private int[][] matAd;
    private VerticeTrasladosEntreaAreas [] areas;
    private int numAreas; 
    
    public  GrafoConexionesEntreAreas(){
        this(MAX_AREAS);
    }
    public  GrafoConexionesEntreAreas(int mx){
        matAd = new int [mx][mx];
        areas = new VerticeTrasladosEntreaAreas[mx];
        for (int i = 0; i < mx; i++)
            for (int j= 0; j < mx; j++)
                matAd [i][j] = 0;
        numAreas = 0;  
    } 
    
    public void nuevoVertice(String nom){
        boolean esta = numArea(nom)>=0;
        
        if (!esta){
            VerticeTrasladosEntreaAreas v = new VerticeTrasladosEntreaAreas(nom);
            v.asignarId(numAreas);
            areas [numAreas++] = v;
        }
     
    }
    public int numArea(String nombre){
        
        for (int i = 0; i < numAreas;i++){
            if(areas[i].nombreDelAreaDeTraslado().equalsIgnoreCase(nombre))
               return i;
        }
        return -1;
   
    }    
    
    public String mostrar (){
        if (numAreas == 0) {
            return "No hay áreas registradas.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Áreas del Hospital ===\n");

        for (int i = 0; i < numAreas; i++) {
            
            sb.append(i+1).append(" - ");
        }

        sb.append("Total de áreas: ").append(numAreas);
        return sb.toString();
    
    }
    
    //agregar metodo  de traslado de pacientes, **investigar lo de rutas optimas
}
