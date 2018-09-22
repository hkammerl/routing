package logistics;

import dijkstra.Edge;
import dijkstra.Vertex;

public class TransportPath extends Edge {

	private String transportPathName = null;

	public TransportPath(String transportPathName, Vertex source, Vertex destination, int weight) {
		super(source, destination, weight);
		this.transportPathName = transportPathName;
	}

	public TransportPath(Vertex source, Vertex destination, int weight) {
		super(source, destination, weight);
	}

	String getTransportPathName() {
		if (transportPathName != null) {
			return transportPathName;
		} else {
			return getSource().toString() + " |--> " + getDestination().toString();
		}
	}

	public String toString() {
		return getTransportPathName();
	}

}
