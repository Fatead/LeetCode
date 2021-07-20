package concurrency;

import java.util.concurrent.Semaphore;

/**
 * 利用Semaphore实现三个进程按顺序打印
 */
public class PrintInOrder {

    //参数为0表示没有资源可以获取，只能阻塞，当有别的线程调用release时，才会给信号量加一个资源
    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public PrintInOrder() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        two.acquire();
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        three.acquire();
        printThird.run();
    }

}