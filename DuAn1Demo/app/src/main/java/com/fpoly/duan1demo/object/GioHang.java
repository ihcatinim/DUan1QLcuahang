package com.fpoly.duan1demo.object;

public
class GioHang {
     private String ten;
     private double gia;
     private int soLuong, ma;

     public GioHang() {
     }

     public GioHang(String ten, double gia, int soLuong, int ma) {
          this.ten = ten;
          this.gia = gia;
          this.soLuong = soLuong;
          this.ma = ma;
     }

     public String getTen() {
          return ten;
     }

     public void setTen(String ten) {
          this.ten = ten;
     }

     public double getGia() {
          return gia;
     }

     public void setGia(double gia) {
          this.gia = gia;
     }

     public int getSoLuong() {
          return soLuong;
     }

     public void setSoLuong(int soLuong) {
          this.soLuong = soLuong;
     }

     public int getMa() {
          return ma;
     }

     public void setMa(int ma) {
          this.ma = ma;
     }
}

