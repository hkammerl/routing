package logistics;

import dijkstra.Vertex;

public class Location extends Vertex {
	
	private String locationNumber;
	
    public Location(String locationNumber) {
    	super();
    	this.locationNumber = locationNumber;
    }

    public String getLocationNumber() {
    	return locationNumber;
    }
    
    public String toString() {
    	return (locationNumber);
    }
}
