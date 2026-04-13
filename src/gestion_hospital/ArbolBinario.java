package gestion_hospital;

public class ArbolBinario {
    private Nodo raiz;

    public boolean insertar(Paciente p) {
        if (existeID(raiz, p.getId())) return false;
        raiz = insertarRecursivo(raiz, p);
        return true;
    }

    private Nodo insertarRecursivo(Nodo actual, Paciente p) {
        if (actual == null) return new Nodo(p);
        if (p.getNombre().compareToIgnoreCase(actual.dato.getNombre()) < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, p);
        } else {
            actual.derecha = insertarRecursivo(actual.derecha, p);
        }
        return actual;
    }

    private boolean existeID(Nodo n, int id) {
        if (n == null) return false;
        if (n.dato.getId() == id) return true;
        return existeID(n.izquierda, id) || existeID(n.derecha, id);
    }

    public void transferirAColaPostOrden(Nodo actual, ColaPrioridad cola) {
        if (actual == null) return;
        transferirAColaPostOrden(actual.izquierda, cola);
        transferirAColaPostOrden(actual.derecha, cola);
        cola.encolar(actual.dato);
    }

    public void eliminar(int id) {
        raiz = eliminarRecursivo(raiz, id);
    }

    private Nodo eliminarRecursivo(Nodo actual, int id) {
        if (actual == null) return null;

        if (id == actual.dato.getId()) {
            if (actual.izquierda == null) return actual.derecha;
            if (actual.derecha == null) return actual.izquierda;

            Paciente sucesor = encontrarMenor(actual.derecha);
            actual.dato = sucesor;
            actual.derecha = eliminarRecursivo(actual.derecha, sucesor.getId());
        } else {
            actual.izquierda = eliminarRecursivo(actual.izquierda, id);
            actual.derecha = eliminarRecursivo(actual.derecha, id);
        }
        return actual;
    }

    private Paciente encontrarMenor(Nodo raiz) {
        return raiz.izquierda == null ? raiz.dato : encontrarMenor(raiz.izquierda);
    }

    public Nodo getRaiz() { return raiz; }

    public String mostrarInOrden(Nodo n) {
        if (n == null) return "";
        return mostrarInOrden(n.izquierda) + n.dato.toString() + "\n" + mostrarInOrden(n.derecha);
    }

    public String mostrarPreOrden(Nodo n) {
        if (n == null) return "";
        return n.dato.toString() + "\n" + mostrarPreOrden(n.izquierda) + mostrarPreOrden(n.derecha);
    }

    public String mostrarPostOrden(Nodo n) {
        if (n == null) return "";
        return mostrarPostOrden(n.izquierda) + mostrarPostOrden(n.derecha) + n.dato.toString() + "\n";
    }
}