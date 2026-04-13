package gestion_hospital;

public class Paciente {
    private int id;
    private String nombre;
    private int prioridad;
    private String diagnostico;
    private VerticeTrasladosEntreaAreas area;

    public Paciente(int id, String nombre, int prioridad, String diagnostico, VerticeTrasladosEntreaAreas area) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.diagnostico = diagnostico;
        this.area = area;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public String getDiagnostico() { return diagnostico; }
    public VerticeTrasladosEntreaAreas getArea() { return area; }

    @Override
    public String toString() {
        String nArea = (area != null) ? area.nombreDelAreaDeTraslado() : "Sin area asignada";
        return "ID: " + id + " | Paciente: " + nombre + " | Prioridad: " + prioridad + " | Area: " + nArea;
    }
}