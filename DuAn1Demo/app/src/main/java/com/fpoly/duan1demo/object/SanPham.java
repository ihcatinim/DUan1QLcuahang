package com.fpoly.duan1demo.object;

public class SanPham {

    private int maSanPham, maLoai, soLuong;
    private String ten;
    private double giaNhap, giaBan;

    public SanPham() {
    }

    public SanPham(int maSanPham, int maLoai, int soLuong, String ten, double giaNhap, double giaBan) {
        this.maSanPham = maSanPham;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.ten = ten;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}
