public class node {

    int value; // value stored at node
    boolean searched = false; //flag for if node has been searched
    int eCount; //keeps track of how many nodes are linked
    node[] edges = new node[5]; //edges stored on node, not really necessary since adjacency matrix is used but ¯\_(ツ)_/¯
    int depth; // used in BFS search to keep track of best path length

    public node(int v){
        value=v;
        eCount=0;
        depth=0;
    }

   public void addEdge(node n){
        if(eCount<5){
            edges[eCount]= n;
            eCount++;
        }
        else
            System.out.println("node has reached maximum edges allowed");
    }

    public void showEdges(){ //prints all edges
        for(int i=0;i<5;i++){
            if(!(edges[i]==null))
            System.out.println(edges[i].value);       
        }
        
    }
}
