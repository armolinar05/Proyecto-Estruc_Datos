
package gestion_hospital;

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }
    
    private Nodo insertarRecursivo(Nodo actual, Paciente paciente){
        
        if(actual == null){
            return new Nodo(paciente);
        }
        
        if(paciente.getId() < actual.dato.getId()){
            actual.izquierda = insertarRecursivo(actual.izquierda, paciente);
        }else{
            if(paciente.getId() > actual.dato.getId()){
                actual.derecha = insertarRecursivo(actual.derecha, paciente);
            }
        }
        return actual;
    }
    
    public Nodo insertar(Nodo actual, Paciente p){
        return insertarRecursivo(actual, p);
    }
    
}
