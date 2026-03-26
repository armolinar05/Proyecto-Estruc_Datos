
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
        }else if(paciente.getId() > actual.dato.getId()){
                actual.derecha = insertarRecursivo(actual.derecha, paciente);
        }
        return actual;
    }
    
    public Nodo insertar(Paciente p){
        return insertarRecursivo(this.raiz, p);
    }
    
    private Nodo buscarId(Nodo actual, int id) {
        while (actual != null) {
            if (id == actual.dato.getId()) {
                return actual; 
            }
            if (id < actual.dato.getId()) {
                actual = actual.izquierda; 
            } else {
                actual = actual.derecha; 
            }
        }
        return null; 
    }
    
    public Nodo buscar(int id) {
        return buscarId(this.raiz, id);
    }
    
    private void ordenarId(Nodo actual){
        if(actual != null){
            ordenarId(actual.izquierda);
            System.out.println(actual.dato.toString());
            ordenarId(actual.derecha);
        }
    }
    
    public void Pa_Ordenados(){
        ordenarId(this.raiz);
    }
}
