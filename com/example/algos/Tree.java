package com.example.algos;
import java.util.*;

public class Tree{
    private final int N;
    private final List<List<Integer>> tree;
    
    int depth[];
    private int up[][];

    private int maxlevels = 1;
    private boolean lca_ready = false;

    public Tree(int N){
        this.N = N;
        tree = new ArrayList<>(N + 1);
        for(int u = 0 ; u <= N ; u++) tree.add(new ArrayList<>());
    }

    public void addEdge(int u , int v){
        tree.get(u).add(v);
        tree.get(v).add(u);
    }

    public int[] distance(int node){
        int[] dist = new int[N + 1];
        Arrays.fill(dist , -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        dist[node] = 0;

        while(queue.isEmpty() == false){
            int u = queue.poll();
            for(int v : tree.get(u)){
                if(dist[v] == -1){
                    dist[v] = 1 + dist[u];
                    queue.offer(v);
                }
            }
        }

        return dist;
    }

    public int[] farthestDistance(){
        int[] d1 = distance(1);

        int x = 1;
        for(int u = 1 ; u <= N ; u++) if(d1[x] < d1[u]) x = u;
        int[] dx = distance(x);

        int y = 1;
        for(int u = 1 ; u <= N ; u++) if(dx[y]  < dx[u]) y = u;
        int[] dy = distance(y);

        int[] maxDist = new int[N + 1];
        for(int u = 1 ; u <= N ; u++) maxDist[u] = Math.max(dx[u] , dy[u]);
        
        return maxDist;
    }

    public int lca(int u , int v){
        if(lca_ready == false){
            depth = new int[N + 1];
            Arrays.fill(depth , -1);
            depth[1] = 0;
            
            while((1 << maxlevels) <= N) maxlevels++;

            up = new int[N + 1][maxlevels + 1];
            up[1][0] = 1;

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(1);

            while(queue.isEmpty() == false){
                int parent = queue.poll();
                for(int child : tree.get(parent)){
                    if(depth[child] == -1){
                        depth[child] = 1 + depth[parent];
                        up[child][0] = parent;
                        queue.offer(child);
                    }
                }
            }

            for(int level = 1 ; level <= maxlevels ; level++){
                for (int node = 1 ; node <= N ; node++){
                    up[node][level] = up[up[node][level - 1]][level - 1];
                }
            }

            lca_ready = true;
        }

        if(depth[u] > depth[v]){
            u = u ^ v;
            v = u ^ v;
            u = u ^ v;
        }
 
        int diff = depth[v] - depth[u];
        for(int level = 0 ; level <= maxlevels ; level++){
            if(((diff >> level) & 1) == 1){
                v = up[v][level];
            }
        }
 
        if(u == v){
            return u;
        }
        else{
            for(int level = maxlevels ; level >= 0 ; level--){
                if(up[u][level] != up[v][level]){
                    u = up[u][level];
                    v = up[v][level];
                }
            }
            return up[u][0];
        }
    }
}