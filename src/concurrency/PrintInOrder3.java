package concurrency;

public class PrintInOrder3 {

    private boolean firstFinished;
    private boolean secondFinished;
    private Object lock = new Object();

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock){
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        //加锁
        synchronized (lock){
            while (!firstFinished){
                lock.wait();
            }
            printSecond.run();
            secondFinished = true;
            //唤醒其他阻塞的线程
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock){
            while (!secondFinished){
                lock.wait();
            }
            printThird.run();
        }
    }

}