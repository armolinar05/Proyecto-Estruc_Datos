package gestion_hospital;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Gestion_Hospital {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        ColaPrioridad cola = new ColaPrioridad();
        GrafoConexionesEntreAreas grafo = new GrafoConexionesEntreAreas();

          int opcion;

        do {
           
            String menu = """
                    === HOSPITAL ===
                    1. Registrar paciente
                    2. Mostrar árbol
                    3. Mostrar cola
                    4. Atender paciente
                    5. Agregar área
                    6. Conectar áreas
                    7. Mostrar grafo
                    8. Salir
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break;

            opcion = Integer.parseInt(input);

            switch (opcion) {

                case 1 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Cedúla :"));
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    int prioridad = Integer.parseInt(JOptionPane.showInputDialog("Prioridad:"));
                    String diagnostico = JOptionPane.showInputDialog("Digite el diagnostico del paciente:");
                    String AreaActualDelPaciente = JOptionPane.showInputDialog("Digite el area del hospital donde se encuentra el paciente:");
                    
                    
                    Paciente p = new Paciente(id, nombre, prioridad, diagnostico, AreaActualDelPaciente);

                    arbol.insertar(p);
                    cola.encolar(p);

                    JOptionPane.showMessageDialog(null, "Paciente registrado.");
                }
                   
                case 2 -> {
                    String menuArbol = """
                            ¿Cómo desea ver los pacientes?
                            1. Orden Alfabético (In-Orden)
                            2. Orden de Ingreso (Pre-Orden)
                            3. Orden de Salida (Post-Orden)
                            """;

                    String opcionArbol = JOptionPane.showInputDialog(menuArbol);

                    if (opcionArbol != null) {
                        String resultado = "";

                        if (opcionArbol.equals("1")) {
                            resultado = arbol.mostrarInOrden();
                        } else if (opcionArbol.equals("2")) {
                            resultado = arbol.mostrarPreOrden();
                        } else if (opcionArbol.equals("3")) {
                            resultado = arbol.mostrarPostOrden();
                        }

                        if (resultado.equals("")) {
                            JOptionPane.showMessageDialog(null, "El hospital no tiene pacientes aún.");
                        } else {
                            JOptionPane.showMessageDialog(null, "--- REPORTE DE PACIENTES ---\n" + resultado);
                        }
                    }
                }

                case 3 -> JOptionPane.showMessageDialog(null,
                    cola.mostrar().isEmpty() ? "Cola vacía" : cola.mostrar());

                case 4 -> {
                    Paciente atendido = cola.atender();
                    JOptionPane.showMessageDialog(null,
                            atendido != null ? "Atendido:\n" + atendido : "No hay pacientes");
                }

                case 5 -> grafo.nuevoVertice(
                    JOptionPane.showInputDialog("Nombre del área:")
                );

//                case 6 -> {
//                    String a = JOptionPane.showInputDialog("Área 1:");
//                    String b = JOptionPane.showInputDialog("Área 2:");
//                    grafo.conectar(a,b);
//                }
//
//                case 7 -> JOptionPane.showMessageDialog(null,
//                    grafo.mostrar().isEmpty() ? "Sin datos" : grafo.mostrar());
//
//                case 8 -> JOptionPane.showMessageDialog(null, "Saliendo...");
            }

        } while (true);
    }
}