package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class FooBar2 {


    private int n;

    public FooBar2(int n) {
        this.n = n;
    }

    private Object lock = new Object();

    private volatile boolean condition = false;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            synchronized (lock){
                if(condition){
                    lock.wait();
                }
                printFoo.run();
                condition = true;
                lock.notifyAll();
            }
        }
    }


    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            synchronized (lock){
                if(!condition){
                    lock.wait();
                }
                printBar.run();
                condition = false;
                lock.notifyAll();
            }
        }
    }

}
