package routing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dijkstra.DijkstraAlgorithm;
import dijkstra.Graph;
import logistics.*;

public class Warehouse {

	private List<Location> locations;
	private List<TransportPath> transportPaths;

	int LoopDuration = 100;
	int BlockDuration = 1;
	int VorzoneDuration = 5;
	int ShuttleDuration = 10;
	int LiftDuration = 1;
	int OSRAisles = 5;
	int LevelsPerOSR = 10;
	int LocationsPerLevel = 100;
	int NumberOfStations = 5;

	public void prepare() {
		System.out.println("PREPARE - START");
		locations = new ArrayList();
		transportPaths = new ArrayList();

		Location loop = new Location("LOOP");
		locations.add(loop);

		for (int s = 1; s <= NumberOfStations; s++) {
			Location station = new Location("S-" + s);
			locations.add(station);
			TransportPath transportPathStation = new TransportPath(loop, station, LoopDuration);
			transportPaths.add(transportPathStation);
		}

		for (int a = 1; a <= OSRAisles; a++) {
			System.out.println("OSR-GASSE - " + a);
			Location out = new Location("OSR-" + a + "-OUT");
			locations.add(out);
			TransportPath transportPathLoop = new TransportPath(out, loop, VorzoneDuration);
			transportPaths.add(transportPathLoop);

			String assignedStation = String.format("S-%d", a);
			TransportPath transportPathBlock = new TransportPath(out, locations.stream()
					.filter(location -> assignedStation.equals(location.getLocationNumber())).findAny().orElse(null),
					BlockDuration);
			transportPaths.add(transportPathBlock);
			for (int l = 1; l <= LevelsPerOSR; l++) {
				Location levelOut = new Location("OSR-" + a + "/" + l + "-OUT");
				locations.add(levelOut);
				TransportPath transportPathLift = new TransportPath(levelOut, out, LiftDuration);
				transportPaths.add(transportPathLift);
				for (int x = 1; x <= LocationsPerLevel; x++) {
					Location loc = new Location("OSR-" + a + "/" + l + "/" + x);
					locations.add(loc);
					TransportPath transportPathShuttle = new TransportPath(loc, levelOut, ShuttleDuration);
					transportPaths.add(transportPathShuttle);
				}
			}
		}
		System.out.println("PREPARE - FINISHED");
	}

	public void run() {

		Graph graph = new Graph(locations, transportPaths);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

		// START-LOCATION
		Location sourceLocation = locations.stream()
				.filter(location -> "OSR-1/1/1".equals(location.getLocationNumber())).findAny().orElse(null);
		System.out.println(sourceLocation.toString());

		// ZIEL-LOCATION
		Location targetLocation = locations.stream().filter(location -> "S-1".equals(location.getLocationNumber()))
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
