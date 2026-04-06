
package gestion_hospital;

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }
    
    private Nodo insertarRecursivo(Nodo actual, Paciente paciente) {
        if (actual == null) {
            return new Nodo(paciente);
        }
        int comparacion = paciente.getNombre().compareToIgnoreCase(actual.dato.getNombre());
        if (comparacion < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, paciente);
        } else if (comparacion > 0) {
                actual.derecha = insertarRecursivo(actual.derecha, paciente);
               }
        return actual;
    }
    
    public void insertar(Paciente p){
        this.raiz = insertarRecursivo(this.raiz, p);
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
    
    public Paciente buscarPaciente(int id) {
        Nodo resultado = buscarId(this.raiz, id);
        if (resultado != null) {
            return resultado.dato; 
        }
        return null; 
    }
    
    private String in_Orden(Nodo actual) {
        if (actual == null) return "";
            return in_Orden(actual.izquierda)
                 + actual.dato.toString() + "\n"
                 + in_Orden(actual.derecha);
    }

    private String pre_Orden(Nodo actual) {
        if (actual == null) return "";
            return actual.dato.toString() + "\n"
                + pre_Orden(actual.izquierda)
                + pre_Orden(actual.derecha);
    }

    private String post_Orden(Nodo actual) {
        if (actual == null) return "";
            return post_Orden(actual.izquierda)
                 + post_Orden(actual.derecha)
                 + actual.dato.toString() + "\n";
    }

    public String mostrarInOrden() {
        return in_Orden(this.raiz);
    }

    public String mostrarPreOrden() {
        return pre_Orden(this.raiz);
    }

    public String mostrarPostOrden() {
        return post_Orden(this.raiz);
    }
    
    private Nodo eliminar_Paciente(Nodo actual, int id) {
        if (actual == null) return null;

        if (id < actual.dato.getId()) {
            actual.izquierda = eliminar_Paciente(actual.izquierda, id);
        } else 
            if (id > actual.dato.getId()) {
            actual.derecha = eliminar_Paciente(actual.derecha, id);
            } else { 
                if (actual.izquierda == null) return actual.derecha;
                if (actual.derecha == null) return actual.izquierda;

                actual.dato = obtener_MasPequeno(actual.derecha);
                actual.derecha = eliminar_Paciente(actual.derecha, actual.dato.getId());
            }
        return actual;
    }       
    
    private Paciente obtener_MasPequeno(Nodo raiz){
        if(raiz.izquierda == null){
            return raiz.dato;
        }else{
            return obtener_MasPequeno(raiz.izquierda);
        }
    }
    
    public void eliminar(int id){
        this.raiz = eliminar_Paciente(this.raiz, id);
    }
}
