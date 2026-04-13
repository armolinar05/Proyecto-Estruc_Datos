
package gestion_hospital;

public class Nodo {
    public Paciente dato;
    public Nodo siguiente;
    public Nodo izquierda, derecha;

    public Nodo(Paciente dato) {
        this.dato = dato;
        this.siguiente = null;
        this.izquierda = null;
        this.derecha = null;
    }

    @Override
    public String toString() {
        return "Nodo{" + "dato:" + dato + '}';
    }
    

    

        
}
