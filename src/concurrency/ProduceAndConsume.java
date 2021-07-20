package concurrency;

import java.util.concurrent.Semaphore;

/**
 * 基于信号量实现生产者消费者
 */
public class ProduceAndConsume {

    private Semaphore produceSemaphore = new Semaphore(1);

    private Semaphore consumeSemaphore = new Semaphore(0);

    private class Produce implements Runnable {

        @Override
        public void run() {
            try {
                produceSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产1个产品");
            consumeSemaphore.release();
        }

    }

    private class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                consumeSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费1个产品");
            produceSemaphore.release();
        }

    }

    public void run(){
        Runnable produce = new Produce();
        Runnable consume = new Consumer();
        while (true){
            produce.run();
            consume.run();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ProduceAndConsume produceAndConsume = new ProduceAndConsume();
        produceAndConsume.run();
    }

}