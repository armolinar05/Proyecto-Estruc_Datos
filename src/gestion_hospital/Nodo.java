
package gestion_hospital;

public class Nodo {
    Paciente dato;
    Nodo siguiente;
    Nodo izquierda;
    Nodo derecha;

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
