package com.example.algos;

public class Node{
    public int firstMax;
    public int secondMax;

    public Node(){
        firstMax = Integer.MIN_VALUE;
        secondMax = Integer.MIN_VALUE;
    }

    public Node(int num){
        firstMax = num;
        secondMax = Integer.MIN_VALUE;
    }

    public static Node merge(Node u , Node v){
        Node uv = new Node();

        for(int num : new int[]{u.firstMax , u.secondMax , v.firstMax , v.secondMax}){
            if(num >= uv.firstMax){
                uv.secondMax = uv.firstMax;
                uv.firstMax = num;
            }
            else if(num > uv.secondMax){
                uv.secondMax = num;
            }
        }

        return uv;
    }
};