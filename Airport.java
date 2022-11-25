import java.util.LinkedList;

public class Airport //monitor
{
	private String name;
	private int numOfFreeRoutes;
	private boolean [] freeRoutes;
	private LinkedList<Integer> line=new LinkedList<Integer>();
	public Airport(String name, int n)
	{
		this.name=name;
		numOfFreeRoutes=n;
		freeRoutes=new boolean [numOfFreeRoutes];
		for(int i=0;i<freeRoutes.length;i++)
			freeRoutes[i]=true;
	}
	
	public synchronized int depart(int flightNum)
	{
		int num=departOrLand(flightNum);
		System.out.println("Flight "+flightNum+" departed from runway "+num+"from airport "+name);
		return num;
	}
	public synchronized int land(int flightNum)
	{
		int num=departOrLand(flightNum);
		System.out.println("Flight "+flightNum+" landed at runway "+num+" from airport "+name);
		return num;
	}
	
	public synchronized int departOrLand(int flightNum)
	{
		while(numOfFreeRoutes==0)
		{
			try {
				line.add(flightNum);
				System.out.println("No runway availalble flight "+flightNum+" is waiting at airport "+name);
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(!line.isEmpty() && line.getFirst()!=flightNum)
			try {
				System.out.println("More flights in line,flight "+flightNum+" waiting at airport "+name);
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		for(int i=0;i<freeRoutes.length;i++)
		{
			if(!line.isEmpty())
				line.remove();

			if(freeRoutes[i])
			{
				numOfFreeRoutes--;
				freeRoutes[i]=false;
				return i;
			}
		}
		return -1;
	}
	
	public synchronized void freeRunway(int flightNum,int routeNum)
	{
		numOfFreeRoutes++;
		freeRoutes[routeNum]=true;
		notifyAll();
	}
}
