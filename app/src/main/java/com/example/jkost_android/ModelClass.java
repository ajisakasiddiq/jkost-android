package com.example.jkost_android;

public class ModelClass {
    private String  Namakost;
    private final String status;
    private final String no;
    private String hargaKamar;
    private final String deskripsi;
    private final String alamat;
    private String imgpertama;
    private final String imgkedua;
    private final String imgketiga;
    private final String imgkeempat;
    String kostName, kostNum;
    String img;
//    public ModelClass(String namaKamar, String hargaKamar) {
//        this.namaKamar = namaKamar;
//        this.hargaKamar = hargaKamar;
//    }

    public ModelClass(String namakost, String status, String no, String hargaKamar, String deskripsi, String alamat, String imgpertama, String imgkedua, String imgketiga, String imgkeempat) {
        this.Namakost = namakost;
        this.status = status;
        this.no = no;
        this.hargaKamar = hargaKamar;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.imgpertama = imgpertama;
        this.imgkedua = imgkedua;
        this.imgketiga = imgketiga;
        this.imgkeempat = imgkeempat;
    }

    public String getKostName() {
        return Namakost;
    }

    public void setKostName(String NamaKost) {
        this.Namakost = NamaKost;
    }

    public String getKostHarga() {
        return hargaKamar;
    }

    public void setKostHarga(String hargaKamar) {
        this.hargaKamar = hargaKamar;
    }

    public String getImg() {
        return imgpertama;
    }

    public void setImg(int img) {
        this.imgpertama = imgpertama;
    }
}
