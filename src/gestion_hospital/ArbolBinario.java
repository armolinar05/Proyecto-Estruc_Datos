
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
    
    //Pacientes ordenados de menor a mayor por ID
    //(Izquierda -> Raíz -> Derecha)
    //Reportes ordenados
    private void in_Orden (Nodo actual){
        if(actual != null){
            in_Orden(actual.izquierda);
            System.out.println(actual.dato.toString());
            in_Orden(actual.derecha);
        }
    } 
    
    //Anota primero al jefe (la raíz) y luego a los hijos
    //(Raíz -> Izquierda -> Derecha)
    //Para respaldar o clonar el arbol
    private void pre_Orden(Nodo actual){
        if(actual != null){
           System.out.println(actual.dato.toString());
           pre_Orden(actual.izquierda);
           pre_Orden(actual.derecha);           
        }
    }
    
    //Visita primero todos los hijos (las hojas) y de último deja a la raíz
    //(Izquierda -> Derecha -> Raíz)
    //Para borrar o liberar memoria
    private void post_Orden(Nodo actual){
        if (actual != null) {
            post_Orden(actual.izquierda);
            post_Orden(actual.derecha);
            System.out.println(actual.dato.toString());
        }
    }
    
    public void mostrarInOrden(){
        in_Orden(this.raiz);
    }
    
    public void mostrarPreOrden() {
        pre_Orden(this.raiz);
    }

    public void mostrarPostOrden() {
        post_Orden(this.raiz);
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
