package routing;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.time.Duration;
import java.time.Instant;

import dijkstra.*;
import logistics.*;
import java.util.Random;
import java.util.Set;



public class TestDijkstraAlgorithm {

    private List<Location> locations;
    private List<TransportPath> transportPaths;


    public void testExcute() {
    	int anzahlVertices = 10;
    	int anzahlEdges = 0;

    	locations = new ArrayList();
        transportPaths = new ArrayList();
        Instant start = Instant.now();        
        for (int i = 0; i < anzahlVertices; i++) {
            Location location = new Location("loc " + i);
            locations.add(location);
        }
        Instant end = Instant.now();
        System.out.println("Time taken: Vertex "+ Duration.between(start, end).toMillis() +" milliseconds");        
//        // Standardweg quer durch...
//        for (int i=0; i < anzahlVertices-1; i++) {
//        	addLane("Edge-1-" + i, i, i+1, 100);
//        }
        addTransportPathByLocationNumber("loc 0", "loc 1", 1);
        addTransportPathByLocationNumber("loc 1", "loc 4", 1);
        addTransportPathByLocationNumber("loc 4", "loc 9", 1);
        
        start = Instant.now();        
        
        for (int i=0;i<anzahlEdges;i++) {
        	Random rand1 = new Random(); 
        	int value1 = rand1.nextInt(anzahlVertices);
        	Random rand2 = new Random(); 
        	int value2 = rand2.nextInt(anzahlVertices);         	
        	Random rand3 = new Random(); 
        	int value3 = rand3.nextInt(10);
        	
        	addTransportPathByIndex("Edge-2-" + i, value1, value2, value3);
        }
        end = Instant.now();
        System.out.println("Time taken: Edges "+ Duration.between(start, end).toMillis() +" milliseconds");        
        
        

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(locations, transportPaths);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        start = Instant.now();
        System.out.println("Execute");
        dijkstra.execute(locations.get(0));
        end = Instant.now();
        System.out.println("Time taken: Execute "+ Duration.between(start, end).toMillis() +" milliseconds");        
        
        start = Instant.now();
        System.out.println("Path");
        LinkedList<Location> path = dijkstra.getPath(locations.get(anzahlVertices-1));
        end = Instant.now();
        System.out.println("Time taken: Path "+ Duration.between(start, end).toMillis() +" milliseconds");        

        start = Instant.now();
        System.out.println("Route");
        LinkedList<Edge> route = dijkstra.getRoute(locations.get(anzahlVertices-1));
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
        
        for (Location vertex : path) {
            System.out.println(vertex);
        }
        for (Edge edge: route) {
            System.out.println(edge);
        }

        
    }
    
    private void addTransportPathByLocationNumber(String sourceLocationNumber, String targetLocationNumber, int weight) {
    	Location source=null;;
    	Location target=null;
    	for (Location  loc: locations) {    	
    		if (loc.getLocationNumber().equals(sourceLocationNumber)) {
    			source = loc;
    		}
    		if (loc.getLocationNumber().equals(targetLocationNumber)) {
    			target = loc;
    		}
    	}
    	
    	if (source != null && target != null) {
    	TransportPath lane = new TransportPath(source.getLocationNumber() + " -> " + target.getLocationNumber(), source, target, weight);
    	transportPaths.add(lane);
    	}
    	else {
    		System.out.println("addEdgeByLocationNummer failed");
    	}
    	
    }
    
    private void addTransportPathByIndex(String laneId, int sourceLocNo, int destLocNo,
            int duration) {
        TransportPath lane = new TransportPath(laneId, locations.get(sourceLocNo), locations.get(destLocNo), duration);
        transportPaths.add(lane);
    }
}