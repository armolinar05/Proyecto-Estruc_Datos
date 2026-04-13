package gestion_hospital;

public class ColaPrioridad {
    private Nodo primero;

    public void encolar(Paciente p) {
        Nodo nuevo = new Nodo(p);
        if (primero == null || p.getPrioridad() < primero.dato.getPrioridad()) {
            nuevo.siguiente = primero;
            primero = nuevo;
        } else {
            Nodo aux = primero;
            while (aux.siguiente != null && aux.siguiente.dato.getPrioridad() <= p.getPrioridad()) {
                aux = aux.siguiente;
            }
            nuevo.siguiente = aux.siguiente;
            aux.siguiente = nuevo;
        }
    }

    public Paciente atender() {
        if (primero == null) return null;
        
        Paciente atendido = primero.dato;
        this.primero = primero.siguiente; 
        
        ArchivoAdmin.registrarAtencion(atendido);
        return atendido;
    }

    public void vaciar() {
        this.primero = null;
    }

    public String mostrar() {
        if (primero == null) return "No hay pacientes pendientes.";
        StringBuilder sb = new StringBuilder();
        Nodo aux = primero;
        while (aux != null) {
            sb.append(aux.dato.toString()).append("\n");
            aux = aux.siguiente;
        }
        return sb.toString();
    }
}