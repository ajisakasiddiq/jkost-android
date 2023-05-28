package com.example.jkost_android;

public class ModelClass {
    private String  nama_kost ,status,no, harga,deskripsi,alamat,imgpertama,imgkedua, imgketiga,imgkeempat;

    public String getNamakost() {
        return nama_kost;
    }

    public void setNamakost(String namakost) {
        nama_kost = namakost;
    }

    public String getStatus() {
        return status;
    }

    public String getNo() {
        return no;
    }

    public String getHarga(Integer harga) {
        return this.harga;
    }

    public void setHarga(String harga) {
        harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getImgpertama() {
        return imgpertama;
    }

    public void setImgpertama(String imgpertama) {
        this.imgpertama = imgpertama;
    }

    public String getImgkedua() {
        return imgkedua;
    }

    public String getImgketiga() {
        return imgketiga;
    }

    public String getImgkeempat() {
        return imgkeempat;
    }



//    public String getKostNum() {
//        return kostNum;
//    }
//
//    public void setKostNum(String kostNum) {
//        this.kostNum = kostNum;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }


//    public ModelClass(String namaKamar, String hargaKamar) {
//        this.namaKamar = namaKamar;
//        this.hargaKamar = hargaKamar;
//    }

//    public ModelClass(String namakost, String status, String no, String hargaKamar, String deskripsi, String alamat, String imgpertama, String imgkedua, String imgketiga, String imgkeempat) {
//        this.Namakost = namakost;
//        this.status = status;
//        this.no = no;
//        this.hargaKamar = hargaKamar;
//        this.deskripsi = deskripsi;
//        this.alamat = alamat;
//        this.imgpertama = imgpertama;
//        this.imgkedua = imgkedua;
//        this.imgketiga = imgketiga;
//        this.imgkeempat = imgkeempat;
//    }
}
