package routing;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;

import dijkstra.*;
import java.util.Random;
import java.util.Set;



public class TestDijkstraAlgorithm {

    private List<Vertex> verteces;
    private List<Edge> edges;


    public void testExcute() {
    	int anzahlVertices = 1000;
    	int anzahlEdges = 10000;

    	verteces = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        Instant start = Instant.now();        
        for (int i = 0; i < anzahlVertices; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            verteces.add(location);
        }
        Instant end = Instant.now();
        System.out.println("Time taken: Vertex "+ Duration.between(start, end).toMillis() +" milliseconds");        
//        // Standardweg quer durch...
//        for (int i=0; i < anzahlVertices-1; i++) {
//        	addLane("Edge-1-" + i, i, i+1, 100);
//        }
        start = Instant.now();        
        
        for (int i=0;i<anzahlEdges;i++) {
        	Random rand1 = new Random(); 
        	int value1 = rand1.nextInt(anzahlVertices);
        	Random rand2 = new Random(); 
        	int value2 = rand2.nextInt(anzahlVertices);         	
        	Random rand3 = new Random(); 
        	int value3 = rand3.nextInt(10);
        	
        	addLane("Edge-2-" + i, value1, value2, value3);
        }
        end = Instant.now();
        System.out.println("Time taken: Edges "+ Duration.between(start, end).toMillis() +" milliseconds");        
        
        
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
        Graph graph = new Graph(verteces, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        start = Instant.now();
        System.out.println("Execute");
        dijkstra.execute(verteces.get(0));
        end = Instant.now();
        System.out.println("Time taken: Execute "+ Duration.between(start, end).toMillis() +" milliseconds");        
        
        start = Instant.now();
        System.out.println("Path");
        LinkedList<Vertex> path = dijkstra.getPath(verteces.get(anzahlVertices-1));
        end = Instant.now();
        System.out.println("Time taken: Path "+ Duration.between(start, end).toMillis() +" milliseconds");        

        start = Instant.now();
        System.out.println("Route");
        LinkedList<Edge> route = dijkstra.getRoute(verteces.get(anzahlVertices-1));
        System.out.println("Finished");
        end = Instant.now();
        System.out.println("Time taken: Route "+ Duration.between(start, end).toMillis() +" milliseconds");        

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
        Edge lane = new Edge(laneId,verteces.get(sourceLocNo), verteces.get(destLocNo), duration );
        edges.add(lane);
    }
}