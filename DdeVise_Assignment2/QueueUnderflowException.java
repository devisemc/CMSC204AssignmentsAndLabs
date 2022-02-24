/**
 * 
 * @author Donovan deVise
 *
 */
public class QueueUnderflowException extends RuntimeException {
	public QueueUnderflowException()
	{
		super("queue underflow");
	}
}
