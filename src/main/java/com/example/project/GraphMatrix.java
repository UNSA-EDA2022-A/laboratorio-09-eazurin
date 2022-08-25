package com.example.project;

import java.util.ArrayList;

public class GraphMatrix implements Graph {

    private int numVertices;
    private int[][] adjacency;

    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.adjacency = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                this.adjacency[i][j] = 0;
            }
        }
    }

    @Override
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 1;
            this.adjacency[to][from] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 0;
            this.adjacency[to][from] = 0;
            return true;
        }
        return false;
    }

    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numVertices) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> depthFirstSearch(int n) {
    	ArrayList<Integer> dfs = new ArrayList<Integer>();
    	for(int i = 0; i < this.numVertices; i++) {
    		dfs.add(-1); //inicializamos con -1
    	}
    	
    	return this.depthFirstSearch(n, dfs);
    }

    public ArrayList<Integer> depthFirstSearch(int n, ArrayList<Integer> visited) {
        visited.set(n, 1); //marcamos con 1 si visitamos
        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjacency[n][i] == 1 && visited.get(i) == -1) {
                depthFirstSearch(i, visited); //si es conexso y no esta marcado usamos
                //recursividad para marcarlo
                
            }
        }
        return visited;
    }

    public String toString() {
        String s = "    ";
        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " ";
        }
        s = s + " \n";

        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " : ";
            for (int j = 0; j < this.numVertices; j++) {
                s = s + String.valueOf(this.adjacency[i][j]) + " ";
            }
            s = s + "\n";
        }
        return s;
    }

    public int countConnectedComponents() {
    	int cont = 1; //si no hay, los componentes conexsos sera todo el grafo
    	ArrayList<Integer> connec = depthFirstSearch(0);
    	//solo seria necesario contar los -1 ya que modificamos el depthFirstSearch
    	for(int i = 0; i < this.numVertices; i++) {
    		if(connec.get(i) == -1)
    			cont++;
    	}
    	return cont;
    }

    public static void main(String args[]) {
        GraphMatrix graph = new GraphMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        System.out.println("The graph matrix:");
        System.out.println(graph);
        System.out.println("DFS:");
        System.out.println(graph.depthFirstSearch(0));
    }

}
