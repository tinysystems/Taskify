package org.xtext.example.mydsl.debugger.processing;

public abstract class ThreadLauncher implements Runnable {
	
	Thread thread;
	protected boolean suspended;
	
	public void start() {
		if (thread == null) {
			thread = new Thread(this, "demo-dsl");
			thread.start();
		}
	}
	
	public synchronized void resume() {}
	
	public synchronized void suspend() {}

}
