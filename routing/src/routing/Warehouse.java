package routing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dijkstra.DijkstraAlgorithm;
import dijkstra.Graph;
import logistics.*;

public class Warehouse {

	public Set<Location> locations;
	public Set<TransportPath> transportPaths;
	public Set<Unit> units;

	private int BFTDuration = 100;
	private int VorzoneDuration = 5;
	private int ShuttleDuration = 10;
	private int LiftDuration = 1;
	private int OSRAisles = 10; // muss gerade sein
	private int LevelsPerOSR = 20;
	private int LocationsPerLevel = 100;
	private int NumberOfStations = OSRAisles;

	public void InitializeWarehouse() {
		System.out.println("PREPARE - START");
		locations = new HashSet();
		transportPaths = new HashSet();
		units = new HashSet();

		// Loop Locations und Stationen
		Location osrBFT1 = null;
		Location stationBFT1 = null;
		for (int s = 1; s <= NumberOfStations; s++) {
			// Stations-Locations
			Location osrBFT = new Location("BO-" + s);
			locations.add(osrBFT);
			Location stationBFT = new Location("BS-" + s);
			locations.add(stationBFT);
			Location station = new Location("S-" + s);
			locations.add(station);
			// Kurzschluss nur alle 2 mal... (anzahl gassen muss gerade sein)
			if (s % 2 == 0) {
				TransportPath t1 = new TransportPath(osrBFT, stationBFT, BFTDuration);
				transportPaths.add(t1);
			}
			// Station
			TransportPath t2 = new TransportPath(stationBFT, station, BFTDuration);
			transportPaths.add(t2);
			if (osrBFT1 != null) {
				TransportPath t3 = new TransportPath(osrBFT1, osrBFT, BFTDuration);
				transportPaths.add(t3);
			}
			if (stationBFT1 != null) {
				TransportPath t4 = new TransportPath(stationBFT, stationBFT1, BFTDuration);
				transportPaths.add(t4);
			}
			osrBFT1 = osrBFT;
			stationBFT1 = stationBFT;
		}

		// OSR Gassen
		for (int a = 1; a <= OSRAisles; a++) {
			System.out.println("OSR-GASSE - " + a);

			// OSR-OUT Location
			Location out = new Location("OSR-OUT" + a);
			locations.add(out);
			// Path von OSR-OUT auf LOOP
			// Path von OSR-OUT auf "gegenüberliegende" Station
			String bo = String.format("BO-%d", a);
			TransportPath transportOutBFT = new TransportPath(out, locations.stream()
					.filter(location -> bo.equals(location.getLocationNumber())).findAny().orElse(null), BFTDuration);
			transportPaths.add(transportOutBFT);

			for (int l = 1; l <= LevelsPerOSR; l++) {
				// LEVEL-OUT Location
				Location levelOut = new Location("OSR-ZP" + a + "/" + l);
				locations.add(levelOut);
				// Path von Level-OUT auf OSR-OUT
				TransportPath transportPathLift = new TransportPath(levelOut, out, LiftDuration);
				transportPaths.add(transportPathLift);
				for (int x = 1; x <= LocationsPerLevel; x++) {
					// OSR-Location
					Location loc = new Location("OSR-" + a + "/" + l + "/" + x);
					locations.add(loc);
					// auf jede OSR-Location eine Unit mit Locno als Barcode
					Unit unit = new Unit("OSR-" + a + "/" + l + "/" + x, loc);
					units.add(unit);
					// Path von OSR-Location auf Level-OUT
					TransportPath transportPathShuttle = new TransportPath(loc, levelOut, ShuttleDuration);
					transportPaths.add(transportPathShuttle);
				}
			}
		}
		System.out.println("PREPARE - FINISHED");
	}
}
