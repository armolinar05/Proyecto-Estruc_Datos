package gestion_hospital;

public class Paciente {

    private int id;
    private String nombre;
    private int edad;
    private int prioridad;
    private String diagnostico;
    private String AreaActualDelPaciente;

    public Paciente(int id, String nombre, int edad, int prioridad, String diagnostico, String AreaActualDelPaciente) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
    public String GetAreaActualDelPaciente(){
        return AreaActualDelPaciente;
    }
    public void SetAreaActualDelPaciente (String AreaActualDelPaciente){
        this.AreaActualDelPaciente = AreaActualDelPaciente;
    }
    

    @Override
    public String toString() {
        return "Paciente{" + "id:" + id + ", nombre:" + nombre + ", edad:" + edad + ", prioridad:" + prioridad + ", diagnostico:" + diagnostico +
                "Area actual del paciente" +  AreaActualDelPaciente + '}';
    }

}
