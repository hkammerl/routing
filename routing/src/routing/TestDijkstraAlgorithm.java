package routing;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import dijkstra.*;
import java.util.Random;



public class TestDijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Edge> edges;


    public void testExcute() {
    	int anzahlVertices = 100;
    	int anzahlEdges = 100000;

    	nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < anzahlVertices; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        // Standardweg quer durch...
        for (int i=0; i < anzahlVertices-1; i++) {
        	addLane("Edge" + i, i, i+1, 100);
        }
        
        for (int i=0;i<anzahlEdges-1;i++) {
        	Random rand1 = new Random(); 
        	int value1 = rand1.nextInt(anzahlVertices);
        	Random rand2 = new Random(); 
        	int value2 = rand2.nextInt(anzahlVertices);         	
        	Random rand3 = new Random(); 
        	int value3 = rand3.nextInt(10);         	
        	
        	addLane("Edge" + i, value1, value2, value3);
        }
        
//        addLane("Edge_0", 0, 1, 85);
//        addLane("Edge_1", 0, 2, 217);
//        addLane("Edge_2", 0, 4, 173);
//        addLane("Edge_3", 2, 6, 186);
//        addLane("Edge_4", 2, 7, 103);
//        addLane("Edge_5", 3, 7, 183);
//        addLane("Edge_6", 5, 8, 250);
//        addLane("Edge_7", 8, 9, 84);
//        addLane("Edge_8", 7, 9, 167);
//        addLane("Edge_9", 4, 9, 502);
//        addLane("Edge_10", 9, 10, 40);
//        addLane("Edge_11", 1, 10, 600);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        System.out.println("Execute");
        dijkstra.execute(nodes.get(0));
        System.out.println("Path");
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(anzahlVertices-1));
        System.out.println("Route");
        LinkedList<Edge> route = dijkstra.getRoute(nodes.get(anzahlVertices-1));
        System.out.println("Finished");

        if (path == null) {
        	System.out.println("NULL");
        	return;
        }
        
        if (path.size() == 0) {
        	System.out.println("0");
        	return;
        }
        
        for (Vertex vertex : path) {
            System.out.println(vertex);
        }
        for (Edge edge: route) {
            System.out.println(edge);
        }

        
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
            int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
}