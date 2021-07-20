package concurrency;
import java.util.concurrent.Semaphore;

public class FooBar3 {

    private int n;

    public FooBar3(int n) {
        this.n = n;
    }

    //利用信号量实现并发打印
    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i <n ; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i <n ; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }

}