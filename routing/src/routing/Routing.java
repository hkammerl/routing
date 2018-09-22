package routing;

public class Routing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Routing Start");
		
		//TestDijkstraAlgorithm test = new TestDijkstraAlgorithm();
		//test.testExcute();
		Setup setup = new Setup();
		setup.prepare();
		setup.run();
		
		System.out.println("Routing End");
		

	}

}
