package pers.kinson.im.chat.dispatch;

public abstract class DispatchTask implements Runnable {

	protected int dispatchKey;

	public int getDispatchKey() {
		return dispatchKey;
	}

}
