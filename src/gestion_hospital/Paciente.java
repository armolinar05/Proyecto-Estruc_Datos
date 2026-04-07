package gestion_hospital;

public class Paciente {

    private int id;
    private String nombre;
    private int edad;
    private int prioridad;
    private String diagnostico;
    private VerticeTrasladosEntreaAreas AreaActualDelPaciente;

    public Paciente(int id, String nombre, int prioridad, String diagnostico, VerticeTrasladosEntreaAreas AreaActualDelPaciente) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.diagnostico = diagnostico;
        this.AreaActualDelPaciente = AreaActualDelPaciente;
        
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    
    

    @Override
    public String toString() {
        return "Paciente{" + "id:" + id + ", nombre:" + nombre + ", prioridad:" + prioridad + ", diagnostico:" + diagnostico +
                "Area actual del paciente" +  AreaActualDelPaciente + '}';
    }

}
