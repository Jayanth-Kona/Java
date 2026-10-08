package com.example.algos;

public class DSU{
    private final int[] parent;
    private final int[] weight;

    public DSU(int N){
        parent = new int[N + 1];
        weight = new int[N + 1];
        for(int u = 0 ; u <= N ; u++){
            parent[u] = u;
            weight[u] = 1;
        }
    }

    public int Find(int u){
        if(u != parent[u]) parent[u] = Find(parent[u]);
        return parent[u];
    }

    public boolean Add(int u , int v){
        u = Find(u);
        v = Find(v);

        if(u == v) return false;

        if(weight[u] < weight[v]){
            u = u ^ v;
            v = u ^ v;
            u = u ^ v;
        }

        parent[v] = u;
        weight[u] += weight[v];

        return true;
    }

    public int size(int u){
        return weight[Find(u)];
    }
}