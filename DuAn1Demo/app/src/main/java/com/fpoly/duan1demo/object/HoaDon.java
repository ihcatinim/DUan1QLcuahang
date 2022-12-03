package com.fpoly.duan1demo.object;

import java.util.Date;

public class HoaDon {
    private int maHD, chietKhau, khachTra, traLai, tongTien;
    private Date ngayBan;
    private String trangThai, tenKH;

    public HoaDon(int maHD, String tenKH, int chietKhau, int khachTra, int traLai, int tongTien, Date ngayBan, String trangThai) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.chietKhau = chietKhau;
        this.khachTra = khachTra;
        this.traLai = traLai;
        this.tongTien = tongTien;
        this.ngayBan = ngayBan;
        this.trangThai = trangThai;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    public int getKhachTra() {
        return khachTra;
    }

    public void setKhachTra(int khachTra) {
        this.khachTra = khachTra;
    }

    public int getTraLai() {
        return traLai;
    }

    public void setTraLai(int traLai) {
        this.traLai = traLai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
}


