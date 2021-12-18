import java.util.*;

public class graph {

    int edge[][];
    node node[];
    int qty;
    
    public graph(int num){
        qty = num; //amount of nodes
        edge = new int[qty][qty]; //initializes adjacency matrix
        node = new node[qty]; //creates array of nodes
    }

    public void insertVertex(int vertexNumber, node newNode){ //inserts nodes
        node[vertexNumber]=newNode;
    }

    public boolean insertEdge(int fromVertex, int toVertex){
        if(edge[fromVertex][toVertex]==1 || fromVertex==toVertex)//edge already inserted or attempting to connect vertex to itself
            return false; 
        else{  
            edge[fromVertex][toVertex]=1; //inserts edge to adjacency matrix and node itself
            node[fromVertex].addEdge(node[toVertex]);
        return true; }
    }

    public boolean DFS(int start, int key){
        int v;
        Stack<Integer> stack = new Stack();
        int pathLength=0; //amount of nodes searched
    
        for(int i=0;i<qty;i++){ //set all nodes to not searched yet
            if(node[i] != null)
                node[i].searched=false;
        }

        stack.push(start); //pushes first node onto stack and flags as searched
        node[start].searched=true;
    
        while(!stack.empty()){
            v=stack.pop(); //pops off top of stack
            if(node[v].value==key){ //checks to see if value is found
                System.out.println(node[v].value+" located. nodes searched: "+pathLength);
                return true;
            }
            pathLength++; //otherwise increments pathLength
            for (int column=0; column < qty; column++){
                if(edge[v][column]==1 && node[column].searched==false){ //and adds all edges to the stack to have their children searched
                    stack.push(column);
                    node[column].searched=true;
                }
            }
        }
        return false; 
    }

    public boolean BFS(int start, int key){
        int v;
        Queue<Integer> queue = new LinkedList<Integer>();
        int pathLength=0;

        for(int i=0;i<qty;i++){ //set all nodes to not searched yet
            if(node[i] != null)
                node[i].searched=false;
        }
        queue.add(start); //add first node to queue and flag as searched
        node[start].searched=true;
        while(!queue.isEmpty()){
            v=queue.poll(); //remove "first in line"
            if(node[v].value==key){ //check to see if its a match
                System.out.println(node[v].value+" located. nodes searched: "+pathLength+ "\n\nShortest path possible: "+ node[v].depth);
                return true;
            }
            pathLength++; // otherwise increment path length and add all edges to the queue
            for (int column=0; column < qty; column++){
                if(edge[v][column]==1 && node[column].searched==false){
                    queue.add(column);
                    node[column].searched=true;
                    node[column].depth=node[v].depth+1; //increments "depth" in node to keep track of how many "generations" have been enqueued for shortest possible length
                }                                       
            }
        }
        return false;
    }

    public void showVertex(int index){ //shows value of any given vertex
        System.out.print(node[index].value);
    }

    public void showEdges(){ //prints adjacency matrix. not used in main due to size of matrix
        for(int i=0;i<qty;i++){
            for(int j=0;j<qty;j++){
                System.out.print(edge[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public void nodeEdges(int index){ //prints values of edges of a given node
        node[index].showEdges();
    }
}
