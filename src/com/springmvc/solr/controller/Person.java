package com.springmvc.solr.controller;

import org.junit.jupiter.api.Test;

/**
 * class_name: Person
 * package: com.springmvc.solr.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/18
 * creat_time: 11:06
 **/


public class Person  implements Runnable{

    private static int countMoney=20000;
    private static boolean flag = true;
    @Override
    public  void  run() {



        takenMoney();



    }
    //取钱的方法
    public synchronized void takenMoney(){
        while (flag) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(countMoney<=0) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "取完了,还剩"+countMoney);
            }else {
                countMoney -= 5000;
                System.out.println(Thread.currentThread().getName() + "取走了5000快,还剩"+countMoney);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Person(),"1").start();
        new Thread(new Person(),"2").start();


    }


}
