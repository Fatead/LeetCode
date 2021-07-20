package concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private Object lock = new Object();
    private int i=0;
    private int flag = 0;

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int j=1;j<=n;j++){
            synchronized(lock){
                while(flag!=0){
                    lock.wait();
                }
                printNumber.accept(0);
                flag=1;
                i=j;
                lock.notifyAll();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int j=2;j<=n;j=j+2){
            synchronized(lock){
                while(flag!=1||i!=j){
                    lock.wait();
                }
                printNumber.accept(j);

                flag=0;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int j=1;j<=n;j=j+2){
            synchronized(lock){
                while(flag!=1||i!=j){
                    lock.wait();
                }
                printNumber.accept(j);
                flag=0;
                lock.notifyAll();
            }
        }
    }

}