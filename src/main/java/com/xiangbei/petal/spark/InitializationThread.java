/*
 * @author：李放
 *
 */
package com.xiangbei.petal.spark;


public class InitializationThread implements Runnable{
    Thread t;
    String name;

    public InitializationThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        SparkCommend.initialization();
        SparkCommend.loadRdd();
    }

    public void start(){
        if(t == null){
            t = new Thread(this,name);
            t.start();
        }
    }
}
