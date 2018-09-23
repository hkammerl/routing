package routing;

import java.util.LinkedList;
import java.util.stream.Collectors;

import dijkstra.DijkstraAlgorithm;
import dijkstra.Graph;
import logistics.Location;
import logistics.TransportPath;

public class Routing {

	static Warehouse warehouse;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Routing Start");
		
		//TestDijkstraAlgorithm test = new TestDijkstraAlgorithm();
		//test.testExcute();
		warehouse = new Warehouse();
		warehouse.InitializeWarehouse();
		run();
		
		System.out.println("Routing End");
		

	}
	
	static void run() {

		Graph graph = new Graph(warehouse.locations.stream().collect(Collectors.toList()), warehouse.transportPaths.stream().collect(Collectors.toList()));
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

		// START-LOCATION
		Location sourceLocation = warehouse.locations.stream()
				.filter(location -> "OSR-4/4/1".equals(location.getLocationNumber())).findAny().orElse(null);
		System.out.println(sourceLocation.toString());

		// ZIEL-LOCATION
		Location targetLocation = warehouse.locations.stream().filter(location -> "S-8".equals(location.getLocationNumber()))
				.findAny().orElse(null);
		System.out.println(targetLocation.toString());

		dijkstra.execute(sourceLocation);

		LinkedList<Location> path = dijkstra.getPath(targetLocation);
		LinkedList<TransportPath> route = dijkstra.getRoute(targetLocation);

		if (path == null) {
			System.out.println("NULL");
			return;
		}

		for (Location location : path) {
			System.out.println(location);
		}

		if (route == null) {
			System.out.println("NULL");
			return;
		}

		for (TransportPath transportPath : route) {
			System.out.println(transportPath);
		}

	}
	

}
