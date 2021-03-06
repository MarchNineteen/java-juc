package com.it.edu.jmm;

public class HappensBeforeSample {

    int a = 10;

    public HappensBeforeSample() {
        System.out.println("对象终结规则 对象的构造函数执行，结束先于finalize()方法");
    }

    private void add() {
        a++;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    protected void finalize() {
        System.out.println("我快死了！！！");
    }

    /**
     * 1、程序顺序原则，即在一个线程内必须保证语义串行性，也就是说按照代码顺序执行。
     * 2、锁规则 解锁(unlock)操作必然发生在后续的同一个锁的加锁(lock)之前，也就是说，如果对于一个锁解锁后，再加锁，那么加锁的动作必须在解锁动作之后(同一个锁)。
     * 3、volatile规则 volatile变量的写，写发生于读，这保证了volatile变量的可见性，简单的理解就是，volatile变量在每次被线程访问时，都强迫从主内存中读该变量的值，而当该变量发生变化时，又会强迫将最新的值刷新到主内存，任何时刻，不同的线程总是能够看到该变量的最新值。
     * 4、线程启动规则 线程的start()方法先于它的每一个动作，即如果线程A在执行线程B的start方法之前修改了共享变量的值，那么当线程B执行start方法时，线程A对共享变量的修改对线程B可见
     * 5、传递性 A先于B ，B先于C 那么A必然先于C
     * 6、线程终止规则 线程的所有操作先于线程的终结，Thread.join()方法的作用是等待当前执行的线程终止。假设在线程B终止之前，修改了共享变量，线程A从线程B的join方法成功返回后，线程B对共享变量的修改将对线程A可见。
     * 7、线程中断规则 对线程 interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过Thread.interrupted()方法检测线程是否中断。
     * 8、对象终结规则 对象的构造函数执行，结束先于finalize()方法
     */

    public static void main(String[] args) throws Throwable {
        // 8、对象终结规则
        HappensBeforeSample sample = new HappensBeforeSample();
//        sample = null;
//        System.gc();

        // 4、线程启动规则
//        Thread thread4B = new Thread(() -> System.out.println(sample.getA()));
//        sample.setA(20);
//        thread4B.start();//B start之前 A的所有操作对B都可见
//        sample.setA(20);// 这样也可能 因为B没有真正的run
//
//
//        thread4B.interrupt();
//        if (thread4B.isInterrupted()) {
//            System.out.println("main get thread interrupt");
//        }

        // 6.线程终止规则 假设在线程B终止之前，修改了共享变量，线程A从线程B的join方法成功返回后，线程B对共享变量的修改将对线程A可见。
//        Thread threadB = new Thread(() -> sample.add());
//        threadB.start();
//
//        threadB.join();
//        System.out.println(sample.getA());

    }

}
