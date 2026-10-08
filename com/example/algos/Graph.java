package com.example.algos;
import java.util.*;

public class Graph{
    private final int N;
    private final List<List<Integer>> graph;

    public Graph(int N){
        this.N = N;
        graph = new ArrayList<>(N);
        for(int u = 0 ; u < N ; u++) graph.add(new ArrayList<>());
    }

    public void addEdge(int u , int v){
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    private void DFS(int u , List<Integer> comp , boolean[] visited){
        visited[u] = true;
        comp.add(u);

        for(int v : graph.get(u)){
            if(!visited[v]){
                DFS(v , comp , visited);
            }
        }
    }

    public List<List<Integer>> connectedComponents(){
        boolean[] visited = new boolean[N];
        List<List<Integer>> components = new ArrayList<>();

        for(int u = 0 ; u < N ; u++){
            if(!visited[u]){
                List<Integer> comp = new ArrayList<>();
                DFS(u , comp , visited);
                components.add(comp);
            }
        }

        return components;
    }
}