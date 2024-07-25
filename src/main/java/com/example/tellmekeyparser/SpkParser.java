package com.example.tellmekeyparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SpkParser {

    private int keyID;
    private String MotherBoard;
    private String CPU;
    private String Network;
    private String HDD;


    public String getKeyID() {
        return (String.valueOf(keyID));
    }

    public String getMotherBoard() {
        return MotherBoard;
    }

    public String getCPU() {
        return CPU;
    }

    public String getNetwork() {
        return Network;
    }

    public String getHDD() {
        return HDD;
    }

    @Override
    public String toString() {
        return String.valueOf(keyID);
    }

    void GetParametersFromSpkFile (String spkFileName){
        ArrayList<Integer> list = new ArrayList<>(); // список байтов
        ArrayList<String> stringlist = new ArrayList<>(); // список строк

        StringBuilder tempsb = new StringBuilder(); //служебный билдер

        try {
            try (FileInputStream fis = new FileInputStream(spkFileName)) {
                while (fis.available() > 0) {
                    list.add(fis.read());
                }
            } // поток чтения байтов из файла лицензии

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Integer integer : list) {
            if (Integer.toHexString(integer).length() < 2) {
                stringlist.add("0" + Integer.toHexString(integer));
            } else {
                stringlist.add(Integer.toHexString(integer));
            }
        }

        this.keyID = Integer.parseInt((stringlist.get(33) + stringlist.get(32)),16); //получили номер ключа

        for (int i = 68; i < 84; i++) {
            tempsb.append(stringlist.get(i)); //собираем строку HDD
        }

        this.HDD = tempsb.toString().toUpperCase(); //строка HDD

        tempsb.setLength(0);//чистим sb

        for (int i = 84; i < 100; i++) {
            tempsb.append(stringlist.get(i)); //собираем строку motherboard
        }

       this.MotherBoard = tempsb.toString().toUpperCase();

        tempsb.setLength(0);//чистим sb

        for (int i = 100; i < 116; i++) {
            tempsb.append(stringlist.get(i)); //собираем строку CPU
        }

        this.CPU = tempsb.toString().toUpperCase();

        tempsb.setLength(0);//чистим sb

        for (int i = 116; i < 132; i++) {
            tempsb.append(stringlist.get(i)); //собираем строку networkNum
        }

        this.Network = tempsb.toString().toUpperCase();

        tempsb.setLength(0);

    }
}