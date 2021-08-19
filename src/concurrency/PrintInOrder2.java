package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于原子性共享变量AtomicInteger实现多线程的顺序打印
 * 循环交替打印
 */
public class PrintInOrder2 {

    private AtomicInteger firstJobDone = new AtomicInteger(0);

    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public void first(Runnable printFirst) throws InterruptedException{
        while (true){
            printFirst.run();
            firstJobDone.incrementAndGet();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException{
        while (true){
            while (firstJobDone.get()!=1){

            }
            firstJobDone.getAndDecrement();
            printSecond.run();
            secondJobDone.incrementAndGet();
        }
    }

    public void third(Runnable printThird) throws InterruptedException{
        while (true){
            while (secondJobDone.get()!=1){

            }
            secondJobDone.getAndDecrement();
            printThird.run();
        }
    }

    public class ThreadA implements Runnable{

        @Override
        public void run() {
            System.out.println("first");
        }

    }

    public class ThreadB implements Runnable{

        @Override
        public void run() {
            System.out.println("second");
        }

    }

    public class ThreadC implements Runnable{

        @Override
        public void run() {
            System.out.println("third");
        }
    }

    public void runInOrder() throws InterruptedException {
        Runnable a = new ThreadA();
        Runnable b = new ThreadB();
        Runnable c = new ThreadC();
        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        Thread threadC = new Thread(c);
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintInOrder2 printInOrder2  = new PrintInOrder2();
        printInOrder2.runInOrder();
    }

}