package com.example.jkost_android.ui.profile;

public class Transaksi {
    private String tanggal;
    private int total;

    public Transaksi(String tanggal, int total) {
        this.tanggal = tanggal;
        this.total = total;
    }

    public String getTanggal(){
        return tanggal;
    }

    public int getTotal(){
        return total;
    }
}
