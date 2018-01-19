package com.springmvc.solr.controller;

/**
 * class_name: ThreadAB
 * package: com.springmvc.solr.controller
 * describe: TODO
 *
 * @author: Liuxianglong
 * @date: 2018/1/19
 * creat_time: 14:49
 **/


public class ThreadAB {



        /**
         * @param args
         */
        public static void main(String[] args) {

            final Print business = new Print();

            new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<10;i++) {
                        business.print_A();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<10;i++) {
                        business.print_B();
                    }
                }
            }).start();

        }
    }
    class Print {

        private boolean flag = true;

        public synchronized void print_A () {
            while(!flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.print("A");
            flag = false;
            this.notify();
        }

        public synchronized void print_B () {
            while(flag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.print("B");
            flag = true;
            this.notify();
        }
    }

