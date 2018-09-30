package com.company;

public class Main {

    public static void main(String[] args) {
        BiteMap map = new BiteMap();
        map.WriteInto("keklol".toCharArray());
        map.WriteInto("keklol".toCharArray());
        map.WriteInto("keklol".toCharArray());
        map.WriteInto("keklol".toCharArray());
        map.WriteInto("keklol".toCharArray());
        map.WriteInto("keklol".toCharArray());
        map.deleteElementFromMap(15);
        map.printStats();
    }
}
