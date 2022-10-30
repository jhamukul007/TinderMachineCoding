package com.tinder.console;

public class ConsolePrint implements Print{

    @Override
    public void print(String s) {
        System.out.println(s);
        System.out.println();
    }

    @Override
    public void print(Object s) {
        System.out.println(s.toString());
        System.out.println();
    }

}
