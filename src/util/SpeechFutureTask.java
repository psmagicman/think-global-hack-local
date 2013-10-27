package util;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class SpeechFutureTask<V> extends FutureTask<V> {

	public boolean canCancel = true;

	/**
	 * a new speaking task to be pushed onto the queue. If cancellable is set to
	 * true, it will be cancelled by a speakNow request.
	 * 
	 * @param callable
	 * @param cancellable
	 */
	public SpeechFutureTask(Callable<V> callable) {
		super(callable);
		// TODO Auto-generated constructor stub
	}
	
	public void setCanCancel(boolean canCancel){
		this.canCancel = canCancel;
	}

}
