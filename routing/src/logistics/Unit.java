package logistics;

public class Unit {
	String barcode;
	Location location;

	public Unit(String barcode, Location location) {
		this.barcode = barcode;
		this.location = location;
	}
	public void UpdateLocation(Location location) {
		this.location = location;
	}
	
	public String getUnitBarcode() {
		return (barcode);
	}
	
	public String toString() {
		return(barcode + " -> " + location.toString());
	}
}
