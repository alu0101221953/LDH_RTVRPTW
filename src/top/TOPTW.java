package top;

import java.util.ArrayList;
import java.util.Arrays;

import es.ull.esit.utilities.ExpositoUtilities;


public class TOPTW {
    private int nodes;
    private double[] x;
    private double[] y;
    private double[] score;
    private double[] readyTime;
    private double[] dueTime;
    private double[] serviceTime;
    private int vehicles;
    private int depots;
    private double maxTimePerRoute;
    private double maxRoutes;
    private double[][] distanceMatrix;

    /**
     * @brief Constructor de la clase TOPTW.
     * @param nodes Número de nodos del problema.
     * @param routes Número de rutas del problema.
     * @return No devuelve nada.
     * @details El constructor de la clase TOPTW se encarga de inicializar los
     * atributos de la clase.
     */
    public TOPTW(int nodes, int routes) {
        this.nodes = nodes;
        this.depots = 0;
        this.x = new double[this.nodes + 1];
        this.y = new double[this.nodes + 1];
        this.score = new double[this.nodes + 1];
        this.readyTime = new double[this.nodes + 1];
        this.dueTime = new double[this.nodes + 1];
        this.serviceTime = new double[this.nodes + 1];
        this.distanceMatrix = new double[this.nodes + 1][this.nodes + 1];
        for (int i = 0; i < this.nodes + 1; i++) {
            for (int j = 0; j < this.nodes + 1; j++) {
                this.distanceMatrix[i][j] = 0.0;
            }
        }
        this.maxRoutes = routes;
        this.vehicles = routes;
    }

    /**
     * @brief Método que comprueba si un nodo es un depósito.   
     * @param a
     * @return Devuelve true si el nodo es un depósito, false en caso contrario.
     */
    public boolean isDepot(int a) {
        if(a > this.nodes) {
            return true;
        }
        return false;
    }

    /**
     * @brief Método que devuelve la distancia entre dos nodos.
     * @param route
     * @return Devuelve la distancia entre dos nodos.
     */
    public double getDistance(int[] route) {
        double distance = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            int node1 = route[i];
            int node2 = route[i + 1];
            distance += this.getDistance(node1, node2);
        }
        return distance;
    }

    /**
     * @brief Método que devuelve la distancia entre dos nodos.
     * @param route
     * @return Devuelve la distancia entre dos nodos.
     */
    public double getDistance(ArrayList<Integer> route) {
        double distance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            int node1 = route.get(i);
            int node2 = route.get(i + 1);
            distance += this.getDistance(node1, node2);
        }
        return distance;
    }

    /**
     * @brief Método que devuelve la distancia entre dos nodos.
     * @param route
     * @return Devuelve la distancia entre dos nodos.
     */
    public double getDistance(ArrayList<Integer>[] routes) {
        double distance = 0.0;
        for (ArrayList<Integer> route : routes) {
            distance += this.getDistance(route);
        }
        return distance;
    }

    /**
     * @brief Método que calcula la distancia entre dos nodos.
     */
    public void calculateDistanceMatrix() {
        for (int i = 0; i < this.nodes + 1; i++) {
            for (int j = 0; j < this.nodes + 1; j++) {
                if (i != j) {
                    double diffXs = this.x[i] - this.x[j];
                    double diffYs = this.y[i] - this.y[j];
                    this.distanceMatrix[i][j] = Math.sqrt(diffXs * diffXs + diffYs * diffYs);
                    this.distanceMatrix[j][i] = this.distanceMatrix[i][j];
                } else {
                    this.distanceMatrix[i][j] = 0.0;
                }
            }
        }
    }

    /**
     * @brief Método que devuelve el tiempo máximo por ruta.
     */
    public double getMaxTimePerRoute() {

        return maxTimePerRoute;
    }

    /**
     * @brief Método que establece el tiempo máximo por ruta.
     */    
    public void setMaxTimePerRoute(double maxTimePerRoute) {
        this.maxTimePerRoute = maxTimePerRoute;
    }

    /**
     * @brief Método que devuelve el número máximo de rutas.
     */
    public double getMaxRoutes() {
        return maxRoutes;
    }

    /**
     * @brief Método que establece el número máximo de rutas.
     */
    public void setMaxRoutes(double maxRoutes) {
        this.maxRoutes = maxRoutes;
    }

    /**
     * @brief Método que devuelve el tiempo máximo por ruta.
     */
    public int getPOIs() {
        return this.nodes;
    }

    /**
     * @brief Método que devuelve el tiempo máximo por ruta.
     */
    public double getDistance(int i, int j) {
        if(this.isDepot(i)) { i=0; }
        if(this.isDepot(j)) { j=0; }
        return this.distanceMatrix[i][j];
    }

    /**
     * @brief Método que devuelve el tiempo máximo por ruta.
     * @param i
     * @param j
     * @return
     */
    public double getTime(int i, int j) {
        if(this.isDepot(i)) { i=0; }
        if(this.isDepot(j)) { j=0; }
        return this.distanceMatrix[i][j];
    }

    /**
     * @brief Método que devuelve los nodos.
     * @return
     */
    public int getNodes() {
        return this.nodes;
    }

    /**
     * @brief Método que establece los nodos.
     * @param nodes
     */
    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    /**
     * @brief Método que devuelve los depósitos.
     * @param index
     * @return Devuelve los depósitos.
     */
    public double getX(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.x[index];
    }

    /**
     * @brief Método que establece los depósitos.
     * @param index
     * @param x
     */
    public void setX(int index, double x) {
        this.x[index] = x;
    }

    /**
     * @brief Método que devuelve los depósitos.
     * @param index
     * @return Devuelve los depósitos.
     */
    public double getY(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.y[index];
    }

    /**
     * @brief Método que establece los depósitos.
     * @param index
     * @param y
     */
    public void setY(int index, double y) {
        this.y[index] = y;
    }

    /**
     * @brief Método que devuelve el score.
     * @param index
     * @return Devuelve el score.
     */
    public double getScore(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.score[index];
    }
    
    /**
     * @brief Método que devuelve el score.
     * @return Devuelve el score.
     */
    public double[] getScore() {
        return this.score;
    }

    /**
     * @brief Método que establece el score.
     * @param index
     * @param score
     */
    public void setScore(int index, double score) {
        this.score[index] = score;
    }

    /**
     * @brief Método que devuelve el ready time.
     * @param index
     * @return Devuelve el ready time.
     */
    public double getReadyTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.readyTime[index];
    }

    /**
     * @brief Método que establece el ready time.
     * @param index
     * @param readyTime
     */
    public void setReadyTime(int index, double readyTime) {
        this.readyTime[index] = readyTime;
    }

    /**
     * @brief Método que devuelve el due time.
     * @param index
     * @return Devuelve el due time.
     */
    public double getDueTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.dueTime[index];
    }

    /**
     * @brief Método que establece el due time.
     * @param index
     * @param dueTime
     */
    public void setDueTime(int index, double dueTime) {
        this.dueTime[index] = dueTime;
    }

    /**
     * @brief Método que devuelve el service time.
     * @param index
     * @return Devuelve el service time.
     */
    public double getServiceTime(int index) {
        if(this.isDepot(index)) { index=0; }
        return this.serviceTime[index];
    }

    /**
     * @brief Método que establece el service time.
     * @param index
     * @param serviceTime
     */
    public void setServiceTime(int index, double serviceTime) {
        this.serviceTime[index] = serviceTime;
    }

    /**
     * @brief Método que devuelve los depósitos.
     * @return Devuelve los depósitos.
     */
    public int getVehicles() {
        return this.vehicles;
    }
    
    @Override
    /**
     * @brief Método que convierte el objeto en una cadena de texto.
     * @return Devuelve una cadena de texto con los datos del objeto.
     */
    public String toString() {
        final int COLUMN_WIDTH = 15;
        String text = "Nodes: " + this.nodes + "\n";
        String[] strings = new String[]{"CUST NO.", "XCOORD.", "YCOORD.", "SCORE", "READY TIME", "DUE DATE", "SERVICE TIME"};
        int[] width = new int[strings.length];
        Arrays.fill(width, COLUMN_WIDTH);
        text += ExpositoUtilities.getFormat(strings, width) + "\n";
        for (int i = 0; i < this.nodes; i++) {
            strings = new String[strings.length];
            int index = 0;
            //strings[index++] = Integer.toString("" + i);
            strings[index++] = Integer.toString(i);
            strings[index++] = "" + this.x[i];
            strings[index++] = "" + this.y[i];
            strings[index++] = "" + this.score[i];
            strings[index++] = "" + this.readyTime[i];
            strings[index++] = "" + this.dueTime[i];
            strings[index++] = "" + this.serviceTime[i];
            text += ExpositoUtilities.getFormat(strings, width);
            text += "\n";
        }
        text += "Vehicles: " + this.vehicles + "\n";
        strings = new String[]{"VEHICLE", "CAPACITY"};
        width = new int[strings.length];
        Arrays.fill(width, COLUMN_WIDTH);
        text += ExpositoUtilities.getFormat(strings, width) + "\n";
        return text;
    }

    /**
     * @brief Método que añaade un nodo. 
     * @return Devuelve el número de nodos.
     */
    public int addNode() {
        this.nodes++;
        return this.nodes;
    }
    
    /**
     * @brief Método que añaade un nodo depósito. 
     * @return Devuelve el número de nodos depósito.
     */
    public int addNodeDepot() {
        this.depots++;
        return this.depots;
    }
}
