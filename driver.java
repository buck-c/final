import java.util.Random;
import java.util.Scanner;

public class driver {
    public static void main(String[] args){

        int qty=1000; //total amount of nodes
        int upperBound=100000; //highest possible value for any node
        int edgeMax=5; // maximum amount of edges for any node
        Random r = new Random();
        graph graph = new graph(qty); 

        for(int i=0;i<qty;i++){ //creates graph with random values for each node
            node newNode=new node(r.nextInt(upperBound));
            graph.insertVertex(i, newNode);
        }

        for(int i=0;i<qty;i++){ // inserts between 1 and max edges for each node
            int edgeCount=r.nextInt(edgeMax)+1;
            for(int j=0;j<edgeCount;j++){
                int to=r.nextInt(qty);
                while(graph.insertEdge(i, to)==false){ //keeps picking a new random place for edge if edge has already been inserted or is trying to connect to self
                    to=r.nextInt(qty);
                }
            }    
        }
  
        int index=r.nextInt(qty);
        System.out.println("Vertex at randomly selected index "+index+": "); // displays a random value in the graph to make testing search below easier
        graph.showVertex(index);

        Scanner scan = new Scanner(System.in);
        System.out.println("\nInput a value to search");
        int input = scan.nextInt();

        System.out.println("\nDepth First Search:"); //initiates depth first search, starting from first node
        if(!(graph.DFS(0,input)))
            System.out.println("search failed");

        System.out.println("\nBreadth First Search:"); //initiates breadth first search, starting from first node
        if(!(graph.BFS(0,input)))
            System.out.println("search failed");

        scan.close();  
    }    
}