package com.fpoly.duan1demo.object;

public
class HoaDonChiTiet {
    private int maHoaDon, maHDCT;
    private GioHang gioHang;

    public HoaDonChiTiet(int maHoaDon, int maHDCT, GioHang gioHang) {
        this.maHoaDon = maHoaDon;
        this.maHDCT = maHDCT;
        this.gioHang = gioHang;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }
}
