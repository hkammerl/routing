package logistics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TransportSystem {
	String Name;
	
	public Set<TransportPath> transportPaths;

	public TransportSystem(String Name) {
		this.Name = Name;
		transportPaths = new HashSet();
	}
	
	public void addTransportPath(TransportPath transportPath) {
		transportPaths.add(transportPath);
	}

}
