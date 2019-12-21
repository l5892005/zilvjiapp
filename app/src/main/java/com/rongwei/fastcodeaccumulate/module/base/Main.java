package com.rongwei.fastcodeaccumulate.module.base;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> list=new ArrayList<>();


    public static void main(String args[]) {
        list.add("aaa");
        list.add("bbb");
        List<String> strings = list.subList(0, 1);


        System.out.println("test1"+strings);
    }
}
