package concurrency;

public class ProduceAndConsume2 {

    private Object lock = new Object();

    private volatile int amount = 0;

    private class Produce implements Runnable {

        @Override
        public void run() {
            synchronized (lock){
                while (amount<3){
                    if(amount<3){
                        amount++;
                        System.out.println("生产一个商品");
                    }else {
                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                lock.notifyAll();
            }
        }

    }

    private class Consumer implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while (amount>0){
                    if(amount>0){
                        amount--;
                        System.out.println("消费一个商品");
                    }else {
                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                lock.notifyAll();
            }
        }

    }

    public void run(){
        while (true){
            Runnable produce = new Produce();
            Runnable consume = new Consumer();
            produce.run();
            consume.run();
            System.out.println();
        }

    }

    public static void main(String[] args) {
        ProduceAndConsume2 produceAndConsume = new ProduceAndConsume2();
        produceAndConsume.run();
    }
}
