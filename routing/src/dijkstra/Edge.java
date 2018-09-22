package dijkstra;
import java.util.UUID;
public class Edge {
	private final UUID id;
	private final Vertex source;
	private final Vertex destination;
	private final int weight;

	public Edge(Vertex source, Vertex destination, int weight) {
		this.id = UUID.randomUUID();
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public UUID getId() {
		return id;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return id + " (" + source + ", " + destination + " - " + weight + ")";
	}

}
