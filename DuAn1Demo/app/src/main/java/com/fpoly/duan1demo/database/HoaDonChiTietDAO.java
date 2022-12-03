package com.fpoly.duan1demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fpoly.duan1demo.object.GioHang;
import com.fpoly.duan1demo.object.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HDCT = "CREATE TABLE HoaDonChiTiet (" +
            "maHDCT INTEGER PRIMARY KEY AUTOINCREMENT," +
            "maHoaDon INTEGER REFERENCES HoaDon(maHoaDon)," +
            "tenSanPham TEXT NOT NULL," +
            "maSanPham INTEGER REFERENCES SanPham(maSanPham)," +
            "soLuong INTEGER NOT NULL," +
            "gia NUMBER NOT NULL)";
    private final SQLiteDatabase sqLiteDatabase;

    public HoaDonChiTietDAO(Context context) {
        Mydatabase mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public long addHDCT(HoaDonChiTiet hoaDonChiTiet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDonChiTiet.getMaHoaDon());
        contentValues.put("tenSanPham", hoaDonChiTiet.getGioHang().getTen());
        contentValues.put("maSanPham", hoaDonChiTiet.getGioHang().getMa());
        contentValues.put("soLuong", hoaDonChiTiet.getGioHang().getSoLuong());
        contentValues.put("gia", hoaDonChiTiet.getGioHang().getGia());
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateHDCT(HoaDonChiTiet hoaDonChiTiet, String ma) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDonChiTiet.getMaHoaDon());
        contentValues.put("tenSanPham", hoaDonChiTiet.getGioHang().getTen());
        contentValues.put("maSanPham", hoaDonChiTiet.getGioHang().getMa());
        contentValues.put("soLuong", hoaDonChiTiet.getGioHang().getSoLuong());
        contentValues.put("gia", hoaDonChiTiet.getGioHang().getGia());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maHDCT = ?", new String[]{ma});
    }

    public long deleteHDCT(String ma) {
        return sqLiteDatabase.delete(TABLE_NAME, "maHDCT = ?", new String[]{ma});
    }

    public List<HoaDonChiTiet> getAllHDCT(String maHD) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String query = " Select * from " + TABLE_NAME + " where maHoaDon = '" + maHD + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maHdct = Integer.parseInt(cursor.getString(0));
                int maHd = Integer.parseInt(cursor.getString(1));
                String ten = cursor.getString(2);
                int maSP = Integer.parseInt(cursor.getString(3));
                int soLuong = cursor.getInt(4);
                double gia = cursor.getDouble(5);
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(maHd, maHdct, new GioHang(ten, gia, soLuong, maSP));
                list.add(hoaDonChiTiet);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
