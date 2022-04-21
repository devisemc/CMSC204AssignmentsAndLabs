import java.util.ArrayList;
import java.util.Random;

public class CarQueue {
	private ArrayList<Integer> queue;
	public CarQueue()
	{
		queue = new ArrayList<Integer>();
		// Add 1000 times, while true loop not possible due to infinite in constructor
		for (int i = 0; i < 1000; i++)
		{
			addToQueue();
		}
	}
	public void addToQueue()
	{
		class newCar implements Runnable
		{

			@Override
			public void run() {
				Random r = new Random();
				queue.add(r.nextInt(4));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
                	e.printStackTrace();
				}
			}
			
		}
		Runnable direction = new newCar();
		Thread t = new Thread(direction);
		t.start();
	}
	public int deleteQueue()
	{
		if (queue.size() == 0)
			return 404;
		return queue.remove(0);
	}
}
