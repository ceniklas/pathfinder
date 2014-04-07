public class main
{
    public static void main(String[] args)
    {
    	GameArea g = new GameArea(60, 41);
    	g.print();

    	
		//START TIMER
		long startTime = System.nanoTime();

    	g.calculatePath();
    	
		System.out.println("Time needed (ms): " + ((System.nanoTime() - startTime) / 1000000));    	
		

		startTime = System.nanoTime();

    	g.print();

    	System.out.println("Time needed (ms): " + ((System.nanoTime() - startTime) / 1000000));
    }
}
