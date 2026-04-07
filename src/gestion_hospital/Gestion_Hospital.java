package gestion_hospital;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Gestion_Hospital {

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        ColaPrioridad cola = new ColaPrioridad();
        GrafoConexionesEntreAreas grafo = new GrafoConexionesEntreAreas();
        boolean ejecutandose = true;
        int opcion;

         while (ejecutandose!=false){

            String menu = """
                    === HOSPITAL ===
                          
                    1. Agregar área del hospital      
                    2. Registrar paciente
                    3. Mostrar árbol
                    4. Atender paciente
                    5. Conectar áreas
                    6. Mostrar ruta optima
                    7. Mostrar áreas (grafo)
                    8. Mostrar cola
                    9. Salir
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break;
            }
            opcion = Integer.parseInt(input);

            switch (opcion) {
                case 1 -> {
                    grafo.nuevoVertice(
                            JOptionPane.showInputDialog("Nombre del área:")
                    );
                }

                case 2 -> {
                    String areasDisponibles;

                    if (grafo.getNumAreas() == 0) {
                        areasDisponibles = "No hay áreas registradas aún.\n";
                    } else {
                        areasDisponibles = "Áreas disponibles:\n" + grafo.mostrar() + "\n";
                    }

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Cédula:"));
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    int prioridad = Integer.parseInt(JOptionPane.showInputDialog("Prioridad:"));
                    String diagnostico = JOptionPane.showInputDialog("Diagnóstico:");
                    String areaInput = JOptionPane.showInputDialog(areasDisponibles + "Área del paciente (escriba el número):");
                    int numAreaElegida = Integer.parseInt(areaInput) - 1;
                    VerticeTrasladosEntreaAreas areaActual = grafo.getArea(numAreaElegida);

                    if (areaActual == null) {
                        JOptionPane.showMessageDialog(null, "Número de área inválido.");
                        break;
                    }
                    Paciente p = new Paciente(id, nombre, prioridad, diagnostico, areaActual);
                    arbol.insertar(p);
                    cola.encolar(p);
                    JOptionPane.showMessageDialog(null, "Paciente registrado en: " + areaActual.nombreDelAreaDeTraslado());
                }

                case 3 -> {
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

                case 4 -> {
                    Paciente atendido = cola.atender();
                    JOptionPane.showMessageDialog(null,
                            atendido != null ? "Atendido:\n" + atendido : "No hay pacientes");
                }
                case 5 -> {
                    String areasDisponibles;
                    if (grafo.getNumAreas() == 0) {
                        areasDisponibles = "No hay áreas registradas aún.\n";
                    } else {
                        areasDisponibles = grafo.mostrar() + "\n";
                    }

                    int numA = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área 1:")) - 1;
                    int numB = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área 2:")) - 1;

                    VerticeTrasladosEntreaAreas areaA = grafo.getArea(numA);
                    VerticeTrasladosEntreaAreas areaB = grafo.getArea(numB);

                    if (areaA == null || areaB == null) {
                        JOptionPane.showMessageDialog(null, "Número de área inválido.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, grafo.conectar(areaA.nombreDelAreaDeTraslado(), areaB.nombreDelAreaDeTraslado()));
                }

                case 6 -> {
                    String areasDisponibles;
                    if (grafo.getNumAreas() == 0) {
                        areasDisponibles = "No hay áreas registradas aún.\n";
                    } else {
                        areasDisponibles = grafo.mostrar() + "\n";
                    }

                    int numOrigen = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área de origen:")) - 1;
                    int numDestino = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área de destino:")) - 1;

                    VerticeTrasladosEntreaAreas areaOrigen = grafo.getArea(numOrigen);
                    VerticeTrasladosEntreaAreas areaDestino = grafo.getArea(numDestino);

                    if (areaOrigen == null || areaDestino == null) {
                        JOptionPane.showMessageDialog(null, "Número de área inválido.");
                        break;
                    }

                    JOptionPane.showMessageDialog(null, grafo.rutaOptima(areaOrigen.nombreDelAreaDeTraslado(), areaDestino.nombreDelAreaDeTraslado()));
                }

                case 7 -> {
                    JOptionPane.showMessageDialog(null,
                            grafo.mostrar().isEmpty() ? "Sin datos" : grafo.mostrar());
                }

                case 8 -> {
                    JOptionPane.showMessageDialog(null,
                            cola.mostrar().isEmpty() ? "Cola vacía" : cola.mostrar());
                }
                case 9 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    ejecutandose = false;
                }
            }

        }
    }
}
