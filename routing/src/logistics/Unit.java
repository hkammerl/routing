package logistics;

public class Unit {
	String barcode;
	Location location;
	Order order;
	int sequenceNumber;

	public Unit(String barcode, Location location) {
		this.barcode = barcode;
		this.location = location;
	}

	public void UpdateLocation(Location location) {
		this.location = location;
	}

	public void assignUnit2Order(Order order, int sequenceWithinOrder) {
		this.order=order;
		sequenceNumber = order.orderSequence * 1000 + sequenceWithinOrder;
	}
	
	public String getUnitBarcode() {
		return (barcode);
	}

	public String toString() {
		return (barcode + " -> " + location.toString() + "(" + sequenceNumber + ")");
	}
}
