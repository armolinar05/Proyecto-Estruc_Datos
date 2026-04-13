package gestion_hospital;

public class GrafoConexionesEntreAreas {

    private static int MAX_AREAS = 20;
    private int[][] matAd;
    private VerticeTrasladosEntreaAreas[] areas;
    private int numAreas;

    public GrafoConexionesEntreAreas() {
        this(MAX_AREAS);
    }

    public GrafoConexionesEntreAreas(int mx) {
        matAd = new int[mx][mx];
        areas = new VerticeTrasladosEntreaAreas[mx];
        for (int i = 0; i < mx; i++) {
            for (int j = 0; j < mx; j++) {
                matAd[i][j] = 0;
            }
        }
        numAreas = 0;
    }

    public void nuevoVertice(String nom) {
        boolean esta = numArea(nom) >= 0;

        if (!esta) {
            VerticeTrasladosEntreaAreas v = new VerticeTrasladosEntreaAreas(nom);
            v.asignarId(numAreas);
            areas[numAreas++] = v;
        }

    }

    public int numArea(String nombre) {

        for (int i = 0; i < numAreas; i++) {
            if (areas[i].nombreDelAreaDeTraslado().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;

    }

    public String mostrar() {
        if (numAreas == 0) {
            return "No hay áreas registradas.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Áreas del Hospital ===\n");

        for (int i = 0; i < numAreas; i++) {

            sb.append(i + 1).append(" - ").append(areas[i].toString()).append("\n");
        }

        sb.append("Total de áreas: ").append(numAreas);
        return sb.toString();

    }

    public int getNumAreas() {
        return numAreas;
    }

    public VerticeTrasladosEntreaAreas getArea(int indice) {
        if (indice >= 0 && indice < numAreas) {
            return areas[indice];
        }
        return null;
    }

    public String conectar(String nombreA, String nombreB) {
        int a = numArea(nombreA);
        int b = numArea(nombreB);

        if (a == -1 || b == -1) {
            return "Una o ambas áreas no existen.";
        }

        if (matAd[a][b] == 1) {
            return "Las áreas ya están conectadas.";
        }

        matAd[a][b] = 1;
        matAd[b][a] = 1; // grafo no dirigido, la conexión va en ambos sentidos
        return "Áreas conectadas: " + nombreA + " <-> " + nombreB;
    }

    public String rutaOptima(String nombreOrigen, String nombreDestino) {
        int origen = numArea(nombreOrigen);
        int destino = numArea(nombreDestino);

        if (origen == -1 || destino == -1) {
            return "Una o ambas áreas no existen.";
        }

        if (origen == destino) {
            return "Ya estás en el área destino.";
        }


        boolean[] visitado = new boolean[numAreas];
        int[] anterior = new int[numAreas];

        for (int i = 0; i < numAreas; i++) {
            anterior[i] = -1;
        }

        int[] cola = new int[numAreas];
        int frente = 0, fin = 0;

        visitado[origen] = true;
        cola[fin++] = origen;

        boolean encontrado = false;

        while (frente < fin) {
            int actual = cola[frente++];

            if (actual == destino) {
                encontrado = true;
                break;
            }

            for (int i = 0; i < numAreas; i++) {
                if (matAd[actual][i] == 1 && !visitado[i]) {
                    visitado[i] = true;
                    anterior[i] = actual;
                    cola[fin++] = i;
                }
            }
        }

        if (!encontrado) {
            return "No existe ruta entre " + nombreOrigen + " y " + nombreDestino + ".";
        }


        StringBuilder ruta = new StringBuilder();
        int paso = destino;
        while (paso != -1) {
            ruta.insert(0, areas[paso].nombreDelAreaDeTraslado());
            if (anterior[paso] != -1) {
                ruta.insert(0, " -> ");
            }
            paso = anterior[paso];
        }

        return "Ruta óptima:\n" + ruta.toString();
    }

   
}
