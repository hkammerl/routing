package routing;

public class Routing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Routing Start");
		
		//TestDijkstraAlgorithm test = new TestDijkstraAlgorithm();
		//test.testExcute();
		Warehouse warehouse = new Warehouse();
		warehouse.prepare();
		warehouse.run();
		
		System.out.println("Routing End");
		

	}

}
