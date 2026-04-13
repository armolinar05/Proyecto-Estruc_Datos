package gestion_hospital;
import java.io.*;

public class ArchivoAdmin {
    private static final String P_FILE = "pacientes.txt";
    private static final String A_FILE = "atenciones.txt";

    public static void obtener_DatosPacientes(ArbolBinario a) {
        File f = new File(P_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] d = l.split(";");
                if (d.length == 4) {
                    a.insertar(new Paciente(Integer.parseInt(d[0]), d[1], Integer.parseInt(d[2]), d[3], null));
                }
            }
        } catch (Exception e) {}
    }

    public static void guardarPacientes(ArbolBinario a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(P_FILE, false));
             PrintWriter pw = new PrintWriter(bw)) {
            escribirNodo(a.getRaiz(), pw);
            pw.flush(); 
        } catch (Exception e) {}
    }

    private static void escribirNodo(Nodo nodo, PrintWriter pw) {
        if (nodo == null) return;
        Paciente p = nodo.dato;
        pw.println(p.getId() + ";" + p.getNombre() + ";" + p.getPrioridad() + ";" + p.getDiagnostico());
        escribirNodo(nodo.izquierda, pw);
        escribirNodo(nodo.derecha, pw);
    }

    public static void registrarAtencion(Paciente p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(A_FILE, true));
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("ATENDIDO: " + p.getNombre() + " | ID: " + p.getId());
            pw.flush();
        } catch (Exception e) {}
    }
}