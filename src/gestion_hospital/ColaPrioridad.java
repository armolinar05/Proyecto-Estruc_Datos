
package gestion_hospital;



public class ColaPrioridad {
    private Nodo primero;
    
    public void encolar(Paciente p){
        Nodo nuevo = new Nodo(p);
        if(primero == null || primero.dato.getPrioridad() < 
                nuevo.dato.getPrioridad()){              
            nuevo.siguiente = primero;
            primero = nuevo;
        }else{
            Nodo actual = primero;
            while(actual != null && actual.siguiente.dato.getPrioridad() >= nuevo.dato.getPrioridad()){
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }
    
    public Paciente atender(){
        if(primero == null) {
            return null;
        }
        Paciente atendido = primero.dato;
        primero = primero.siguiente;
        ArchivoAdmin.registrarAtencion(atendido);
        return atendido;
    }
    
}
