package com.example.jkost_android.ui.profile;

public class Transaksi {
    private String nama_kost, name, nama_pemesan, status;

    private int  id_kost, id_kamar, id_transaction, user_id, kamar_id, id, no_kamar, durasi_sewa, total_price, tgl_sewa;

    public String getNama_kost() {
        return nama_kost;
    }

    public void setNama_kost(String nama_kost) {
        this.nama_kost = nama_kost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan = nama_pemesan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_kost() {
        return id_kost;
    }

    public void setId_kost(int id_kost) {
        this.id_kost = id_kost;
    }

    public int getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(int id_kamar) {
        this.id_kamar = id_kamar;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getKamar_id() {
        return kamar_id;
    }

    public void setKamar_id(int kamar_id) {
        this.kamar_id = kamar_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_kamar() {
        return no_kamar;
    }

    public void setNo_kamar(int no_kamar) {
        this.no_kamar = no_kamar;
    }

    public int getDurasi_sewa() {
        return durasi_sewa;
    }

    public void setDurasi_sewa(int durasi_sewa) {
        this.durasi_sewa = durasi_sewa;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getTgl_sewa() {
        return tgl_sewa;
    }

    public void setTgl_sewa(int tgl_sewa) {
        this.tgl_sewa = tgl_sewa;
    }
}