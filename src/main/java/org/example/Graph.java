package org.example;

import java.util.*;

public class Graph <V>{
    //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();
/******************************************************************
 * Añade el vértice ‘v‘ al grafo.
 *
 * @param v vértice a añadir.
 * @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso
 * contrario.
 * contrario.
 ******************************************************************/
public boolean addVertex(V v) {
    if (!adjacencyList.containsKey(v)) {
        adjacencyList.put(v, new HashSet<V>());
        return true;
    }
    return false;
}
    /******************************************************************
     * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. En
     * caso de que no exista alguno de los vértices, lo añade
     * también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return ‘true‘ si no existía el arco y ‘false‘ en caso
    contrario.
     ******************************************************************/
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        Set<V> adyacents1 = adjacencyList.get(v1);
        if (adyacents1.contains(v2)) {
            return false;
        }
        adyacents1.add(v2);
        return true;
    }
    /******************************************************************
     * Obtiene el conjunto de vértices adyacentes a ‘v‘.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     ******************************************************************/
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("Vertex does not exist");
        }
        return adjacencyList.get(v);
    }
    /******************************************************************
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return ‘true‘ si ‘v‘ es un vértice del grafo.
     ******************************************************************/
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }
/******************************************************************
 * Método ‘toString()‘ reescrito para la clase ‘Grafo.java‘.
 * @return una cadena de caracteres con la lista de
 * adyacencia.
 ******************************************************************/
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (V vertex : adjacencyList.keySet()) {
        sb.append(vertex.toString());
        sb.append(": ");
        sb.append(adjacencyList.get(vertex).toString());
        sb.append("\n");
    }
    return sb.toString();
}
    /*********************************************************
     * Obtiene, en caso de que exista, un camino entre ‘v1‘ y
     * ‘v2‘. En caso contrario, devuelve ‘null‘.
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices desde ‘v1‘ hasta
     * ‘v2‘ * pasando por arcos del grafo.
     *********************************************************/
    public List<V> onePath(V v1, V v2) throws Exception {
        Map<V, V> trace = new HashMap<>();
        Stack<V> open = new Stack<>();
        open.push(v1);
        trace.put(v1, null);
        boolean found = false;

        while (!open.isEmpty() && !found) {
            V v = open.pop();
            found = v.equals(v2);
            if (found) {
                break;
            }
            for (V s : obtainAdjacents(v)) {
                if (!trace.containsKey(s)) {
                    open.push(s);
                    trace.put(s, v);
                }
            }
        }

        if (found) {
            List<V> path = new ArrayList<>();
            V curr = v2;
            while (curr != null) {
                path.add(0, curr);
                curr = trace.get(curr);
            }
            return path;
        } else {
            return null;
        }
    }
}

