package spiderboot.helper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class PrepareProduction implements Runnable {
  BlockingQueue<String> queue;

  PrepareProduction(BlockingQueue<String> q) {
    queue = q;
  }

  public void run() {
    String thisLine;
    try {
      queue.put("1");
      queue.put("done");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class DoProduction implements Runnable {
  private final BlockingQueue<String> queue;

  DoProduction(BlockingQueue<String> q) {
    queue = q;
  }

  public void run() {
    try {
      String value = queue.take();
      while (!value.equals("done")) {
        value = queue.take();
        System.out.println(value);
      }
    } catch (Exception e) {
      System.out.println(Thread.currentThread().getName() + " "
          + e.getMessage());
    }
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    BlockingQueue<String> q = new LinkedBlockingQueue<String>();
    Thread p1 = new Thread(new PrepareProduction(q));
    Thread c1 = new Thread(new DoProduction(q));
    p1.start();
    c1.start();
    p1.join();
    c1.join();
  }
}
