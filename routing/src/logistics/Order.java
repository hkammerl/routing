package logistics;

import java.util.ArrayList;
import java.util.List;

public class Order {
	int orderSequence;
	Location targetLocation;
	List unitSequence;

	public Order(int orderSequence, Location targetLocation) {
		this.orderSequence = orderSequence;
		this.targetLocation = targetLocation;
		unitSequence = new ArrayList();
	}

	public void appendUnitToSequence(Unit unit) {
		unitSequence.add(unit);
		unit.assignUnit2Order(this, unitSequence.size());
	}
	
	public String toString() {
		return (targetLocation + ": " + unitSequence);
	}
}
