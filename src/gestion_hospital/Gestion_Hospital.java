package gestion_hospital;

import javax.swing.JOptionPane;

public class Gestion_Hospital {

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        ColaPrioridad cola = new ColaPrioridad();
        GrafoConexionesEntreAreas grafo = new GrafoConexionesEntreAreas();
        
        ArchivoAdmin.obtener_DatosPacientes(arbol);
        
        boolean ejecutandose = true;
        int opcion;

        while (ejecutandose) {

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

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido en el menú.");
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    String nombreArea = JOptionPane.showInputDialog("Nombre del área:");

                    if (nombreArea == null || nombreArea.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre del área no puede estar vacío.");
                        break;
                    }

                    boolean esValido = true;
                    String nombreTrim = nombreArea.trim();

                    for (int i = 0; i < nombreTrim.length(); i++) {
                        char c = nombreTrim.charAt(i);
                        if (!Character.isLetter(c) && c != ' ') {
                            esValido = false;
                            break;
                        }
                    }

                    if (!esValido) {
                        JOptionPane.showMessageDialog(null, "El nombre del área solo puede contener letras.");
                        break;
                    }

                    grafo.nuevoVertice(nombreTrim);

                    JOptionPane.showMessageDialog(null, "¡Área '" + nombreTrim + "' registrada exitosamente!");
                }

                case 2 -> {
                    String areasDisponibles = grafo.getNumAreas() == 0
                            ? "No hay áreas registradas aún.\n"
                            : "Áreas disponibles:\n" + grafo.mostrar() + "\n";

                    int id;
                    try {
                        String idInput = JOptionPane.showInputDialog("Cédula:");
                        if (idInput == null) break; 
                        id = Integer.parseInt(idInput.trim());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "La cédula debe ser un número entero.");
                        break;
                    }

                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    if (nombre == null || nombre.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                        break;
                    }

                    int prioridad;
                    try {
                        String prioridadInput = JOptionPane.showInputDialog("Prioridad 1-5 (1 -> Urgente, 5 -> Leve):");
                        if (prioridadInput == null) break;
                        prioridad = Integer.parseInt(prioridadInput.trim());

                        if (prioridad < 1 || prioridad > 5) {
                            JOptionPane.showMessageDialog(null, "Prioridad inválida. Debe ser un número entre 1 y 5.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "La prioridad debe ser un número válido entre 1 y 5.");
                        break;
                    }

                    String diagnostico = JOptionPane.showInputDialog("Diagnóstico:");
                    if (diagnostico == null) diagnostico = "Sin diagnóstico";

                    VerticeTrasladosEntreaAreas areaActual;
                    try {
                        String areaInput = JOptionPane.showInputDialog(areasDisponibles + "Área del paciente (escriba el número):");
                        if (areaInput == null) break;
                        int numAreaElegida = Integer.parseInt(areaInput.trim()) - 1;
                        areaActual = grafo.getArea(numAreaElegida);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al seleccionar el área. Verifique que el número exista.");
                        break;
                    }

                    if (areaActual == null) {
                        JOptionPane.showMessageDialog(null, "Número de área inválido.");
                        break;
                    }

                    Paciente p = new Paciente(id, nombre.trim(), prioridad, diagnostico.trim(), areaActual);

                    if (arbol.insertar(p)) {
                        ArchivoAdmin.guardarPacientes(arbol);
                        JOptionPane.showMessageDialog(null, "¡Paciente " + nombre.trim() + " registrado correctamente en " + areaActual.nombreDelAreaDeTraslado() + "!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Ya existe un paciente con la cédula " + id);
                    }
                }

                case 3 -> {
                    String menuArbol = """
                            ¿Cómo desea ver los pacientes?
                            1. Orden Alfabético (In-Orden)
                            2. Orden de Ingreso (Pre-Orden)
                            3. Orden de Salida (Post-Orden)
                            """;

                    String opcionArbol = JOptionPane.showInputDialog(menuArbol);
                    if (opcionArbol == null) {
                        break;
                    }

                    String resultado = "";
                    switch (opcionArbol.trim()) {
                        case "1" ->
                            resultado = arbol.mostrarInOrden(arbol.getRaiz());
                        case "2" ->
                            resultado = arbol.mostrarPreOrden(arbol.getRaiz());
                        case "3" ->
                            resultado = arbol.mostrarPostOrden(arbol.getRaiz());
                        default -> {
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                        }
                    }

                    JOptionPane.showMessageDialog(null,
                            resultado.isEmpty() ? "El hospital no tiene pacientes aún."
                            : "--- REPORTE DE PACIENTES ---\n" + resultado);
                }

                case 4 -> {
                    cola.vaciar();
                    if (arbol.getRaiz() == null) {
                        JOptionPane.showMessageDialog(null, "No hay pacientes pendientes.");
                        break;
                    }

                    arbol.transferirAColaPostOrden(arbol.getRaiz(), cola);

                    Paciente atendido = cola.atender(); 

                    if (atendido != null) {
                        arbol.eliminar(atendido.getId());

                        ArchivoAdmin.guardarPacientes(arbol); 

                        JOptionPane.showMessageDialog(null, "Paciente atendido: " + atendido.getNombre());
                    }
                }

                case 5 -> {
                    String areasDisponibles = grafo.getNumAreas() == 0
                            ? "No hay áreas registradas aún.\n"
                            : grafo.mostrar() + "\n";

                    int numA, numB;
                    try {
                        numA = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área 1:")) - 1;
                        numB = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área 2:")) - 1;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Los números de área deben ser números.");
                        break;
                    }

                    VerticeTrasladosEntreaAreas areaA = grafo.getArea(numA);
                    VerticeTrasladosEntreaAreas areaB = grafo.getArea(numB);

                    if (areaA == null || areaB == null) {
                        JOptionPane.showMessageDialog(null, "Número de área inválido.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, grafo.conectar(areaA.nombreDelAreaDeTraslado(), areaB.nombreDelAreaDeTraslado()));
                }

                case 6 -> {
                    String areasDisponibles = grafo.getNumAreas() == 0
                            ? "No hay áreas registradas aún.\n"
                            : grafo.mostrar() + "\n";

                    int numOrigen, numDestino;
                    try {
                        numOrigen = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área de origen:")) - 1;
                        numDestino = Integer.parseInt(JOptionPane.showInputDialog(areasDisponibles + "Número del área de destino:")) - 1;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Los números de área deben ser números.");
                        break;
                    }

                    VerticeTrasladosEntreaAreas areaOrigen = grafo.getArea(numOrigen);
                    VerticeTrasladosEntreaAreas areaDestino = grafo.getArea(numDestino);

                    if (areaOrigen == null || areaDestino == null) {
                        JOptionPane.showMessageDialog(null, "Número de área inválido.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, grafo.rutaOptima(areaOrigen.nombreDelAreaDeTraslado(), areaDestino.nombreDelAreaDeTraslado()));
                }

                case 7 -> {
                    String resultado = grafo.mostrar();
                    JOptionPane.showMessageDialog(null,
                            resultado.isEmpty() ? "Sin datos" : resultado);
                }

                case 8 -> {
                    String resultado = cola.mostrar();
                    JOptionPane.showMessageDialog(null,
                            resultado.isEmpty() ? "Cola vacía" : resultado);
                }

                case 9 -> {
                    ArchivoAdmin.guardarPacientes(arbol);
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    ejecutandose = false;
                }

                default ->
                    JOptionPane.showMessageDialog(null, "Opción no válida. Ingrese un número del 1 al 9.");
            }
        }
    }
}
