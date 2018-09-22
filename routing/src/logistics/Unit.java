package logistics;

public class Unit {
	String barcode;
	Location location;

	public Unit(String barcode, Location location) {
		this.barcode = barcode;
		this.location = location;
	}
	void UpdateLocation(Location location) {
		this.location = location;
	}
}
