/*
 * @author：李放
 *
 */

package com.xiangbei.petal.spark;

public class CommendThread implements Runnable{
    Thread t;
    String name;

    public CommendThread(String name){
        this.name = name;
    }

    @Override
    public void run() {

    }

    public void start(){
        if(t == null){
            t = new Thread(this,name);
            t.start();
        }
    }
}
