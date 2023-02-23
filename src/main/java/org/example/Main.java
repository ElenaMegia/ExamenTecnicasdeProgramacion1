package org.example;
class Main {
    public static void main(String[] args) {
        Graph grafo = new Graph(4, 5);
        grafo.aristas.add(grafo.new Arista(0, 1, 10));
        grafo.aristas.add(grafo.new Arista(0, 2, 6));
        grafo.aristas.add(grafo.new Arista(0, 3, 5));
        grafo.aristas.add(grafo.new Arista(1, 3, 15));
        grafo.aristas.add(grafo.new Arista(2, 3, 4));

        Kruskal k = new Kruskal();
        ArrayList<Grafo.Arista> resultado = k.kruskal(grafo);

        // Imprimir las aristas del árbol de expansión mínima
        for (Grafo.Arista arista : resultado) {
            System.out.println(arista.origen + " -- " + arista.destino + " : " + arista.peso);
        }
    }
}