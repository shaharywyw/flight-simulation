
public class FlightSimulation 
{

	public static void main(String[] args) 
	{
		final int FLIGHTS=10;
		final int ROUTES=3;
		Airport TLV=new Airport("BenGurion",ROUTES);
		Airport NYC=new Airport("JFK",ROUTES);
		Flight [] flights=new Flight[FLIGHTS];
		double diraction;
		
		for(int i=0;i<FLIGHTS;i++)
		{
			diraction=Math.random();
			if(diraction<=0.5)
				flights[i]=new Flight(i,TLV,NYC);
			else
				flights[i]=new Flight(i,NYC,TLV);
		}
		
		for(int i=0;i<FLIGHTS;i++)
			flights[i].start();
	}

}
