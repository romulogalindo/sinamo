/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinamo.test;

/**
 *
 * @author romulogalindo
 */
public class Test {

    public Test() {
//        String t = "$_R1${icon}";
        String t = "null";
        System.out.println("t = " + t);
        System.out.println("t = " + t.split("\\$").length);
        System.out.println("t = " + t.split("\\$")[1]);
        System.out.println("t = " + t.split("\\$")[2].replace("{", ""));
        
    }
    public static void main(String[] args) {
        new Test();
    }
}
