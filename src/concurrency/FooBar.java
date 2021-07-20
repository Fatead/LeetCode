package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两种思路，自旋或者阻塞
 * 自旋方法实现思路
 */
public class FooBar {

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    //使用AtomicInteger来模拟P/V操作
    private AtomicInteger atomicInteger = new AtomicInteger(0);


    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (atomicInteger.get()!=0){
                Thread.yield();
            }
            printFoo.run();
            atomicInteger.getAndIncrement();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (atomicInteger.get()!=1){
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            atomicInteger.getAndDecrement();
        }
    }

}
