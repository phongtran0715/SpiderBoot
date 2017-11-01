package spiderboot.helper;

public class NewThread implements Runnable {
	String name; // name of thread
	public boolean stopped = false;

	Thread t;

	public NewThread(String threadname) {
		name = threadname;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
	}
	
	public Thread getThead() {
		return t;
	}

	public void run() {
		try {
			for (int i = 100; i > 0; i--) {
				System.out.println(name + ": " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println(name + "Interrupted");
		}
		System.out.println(name + " exiting.");
	}
	
	public void startThread() {
		t.start();
	}
}
