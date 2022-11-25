import java.util.Random;
public class Flight extends Thread
{
	private final int TIME=1000;
	private int flightNum;
	private Airport depart,land;
	
	public Flight(int flightNum, Airport depart,Airport land)
	{
		this.flightNum=flightNum;
		this.depart=depart;
		this.land=land;
	}
	
	public void run()
	{
		super.run();
		int routeNum;
		
		routeNum=depart.depart(flightNum); //departing
		wait2To5Sec();
		
		
		depart.freeRunway(flightNum, routeNum);//releasing the route
		wait2To5Sec(); //flying
		
		
		routeNum=land.land(flightNum); //landing
		wait2To5Sec();
		
		land.freeRunway(flightNum, routeNum); //releasing the route
	}
	
	public void wait2To5Sec()
	{
		Random rnd=new Random();
		int seconds;
		try {
			seconds=rnd.nextInt(2)+3;
			sleep(seconds*TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
