package com.example.algos;

public class SegmentTree{
    private final int n;
    private final Node[] Tree;

    public SegmentTree(int n , int nums[]){
        this.n = n;
        this.Tree = new Node[n << 2 | 2];
        Build(1 , 0 , n - 1 , nums);
    }

    private Node Build(int node , int low , int high , int[] nums){
        if(low == high) return Tree[node] = new Node(nums[low]);
        int mid = (low + high) >> 1;
        return Tree[node] = Node.merge(Build(node << 1 , low , mid , nums) , Build(node << 1 | 1 , mid + 1 , high , nums));
    }

    private Node Query(int node , int low , int high , int qL , int qR){
        if(high < qL || qR < low) return new Node();
        if(qL <= low && high <= qR) return Tree[node];
        int mid = (low + high) >> 1;
        return Node.merge(Query(node << 1 , low , mid , qL , qR) , Query(node << 1 | 1 , mid + 1 , high , qL , qR));
    }

    private void Update(int node , int low , int high , int index , int value){
        if(low == high){
            Tree[node] = new Node(value);
        }
        else{
            int mid = (low + high) >> 1;
            if(index <= mid) Update(node << 1 , low , mid , index , value);
            else Update(node << 1 | 1 , mid + 1 , high , index , value);
            Tree[node] = Node.merge(Tree[node << 1] , Tree[node << 1 | 1]);
        }
    }

    public Node Query(int L , int R){
        return Query(1 , 0 , n - 1 , L , R);
    }

    public void Update(int index , int value){
        Update(1 , 0 , n - 1 , index , value);
    }
}