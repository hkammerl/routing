package logistics;

import java.util.ArrayList;
import java.util.List;

public class Order {
	Location targetLocation;
	List unitSequence;

	public Order(Location targetLocation) {
		this.targetLocation = targetLocation;
		unitSequence = new ArrayList();
	}

	public void appendUnitToSequence(Unit unit) {
		unitSequence.add(unit);
	}
	
	public String toString() {
		return (targetLocation + ": " + unitSequence);
	}
}
