package com.fpoly.duan1demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fpoly.duan1demo.object.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    public static final String TABLE_NAME = "SanPham";
    public static final String SQL_SANPHAM = "CREATE TABLE SanPham(" +
            "   maSanPham INTEGER PRIMARY KEY AUTOINCREMENT," +
            "   maLoai INTEGER REFERENCES LoaiSanPham(maLoai)," +
            "   tenSanPham TEXT NOT NULL," +
            "   soLuong NUMBER NOT NULL," +
            "   giaNhap NUMBER NOT NULL," +
            "   giaBan NUMBER NOT NULL)";

    private final SQLiteDatabase sqLiteDatabase;

    public SanPhamDAO(Context context) {
        Mydatabase mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public long addSanPham(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maLoai", sanPham.getMaLoai());
        contentValues.put("tenSanPham", sanPham.getTen());
        contentValues.put("soLuong", sanPham.getSoLuong());
        contentValues.put("giaNhap", sanPham.getGiaNhap());
        contentValues.put("giaBan", sanPham.getGiaBan());
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateSanPham(SanPham sanPham, String ma) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maLoai", sanPham.getMaLoai());
        contentValues.put("tenSanPham", sanPham.getTen());
        contentValues.put("soLuong", sanPham.getSoLuong());
        contentValues.put("giaNhap", sanPham.getGiaNhap());
        contentValues.put("giaBan", sanPham.getGiaBan());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maSanPham = ?", new String[]{ma});
    }

    public long updateSLSanPham(int soLuong,int ma){
        ContentValues contentValues = new ContentValues();
        contentValues.put("soLuong",soLuong);
        return sqLiteDatabase.update(TABLE_NAME,contentValues,"maSanPham = ?",new String[]{String.valueOf(ma)});
    }

    public long deleteSanPham(String ma) {
        return sqLiteDatabase.delete(TABLE_NAME, "maSanPham = ?", new String[]{ma});
    }

    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maSP = Integer.parseInt(cursor.getString(0));
                int maLoai = Integer.parseInt(cursor.getString(1));
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                SanPham sanPham = new SanPham(maSP, maLoai, soLuong, ten, giaNhap, giaBan);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public SanPham getSanPhamTheoMa(String ma) {
        String query = "select * from " + TABLE_NAME + " where maSanPham = '" + ma + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        SanPham sanPham = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int maSP = Integer.parseInt(cursor.getString(0));
            int maLoai = Integer.parseInt(cursor.getString(1));
            String ten = cursor.getString(2);
            int soLuong = cursor.getInt(3);
            double giaNhap = cursor.getDouble(4);
            double giaBan = cursor.getDouble(5);
            sanPham = new SanPham(maSP, maLoai, soLuong, ten, giaNhap, giaBan);
        }
        cursor.close();
        return sanPham;
    }

    public List<SanPham> getAllSanPhamTheoMa(String ma) {
        List<SanPham> list = new ArrayList<>();
        String query = "select DISTINCT * from " + TABLE_NAME + " where maSanPham like '%" + ma + "%' or tenSanPham like '%" + ma + "%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        SanPham sanPham;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int maSP = Integer.parseInt(cursor.getString(0));
            int maLoai = Integer.parseInt(cursor.getString(1));
            String ten = cursor.getString(2);
            int soLuong = cursor.getInt(3);
            double giaNhap = cursor.getDouble(4);
            double giaBan = cursor.getDouble(5);
            sanPham = new SanPham(maSP, maLoai, soLuong, ten, giaNhap, giaBan);
            list.add(sanPham);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<SanPham> getAllSanPhamTheoTen() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " order by tenSanPham asc";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maSP = Integer.parseInt(cursor.getString(0));
                int maLoai = Integer.parseInt(cursor.getString(1));
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                SanPham sanPham = new SanPham(maSP, maLoai, soLuong, ten, giaNhap, giaBan);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<SanPham> getAllSanPhamTheoGiaTangDan() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " order by giaBan asc";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maSP = Integer.parseInt(cursor.getString(0));
                int maLoai = Integer.parseInt(cursor.getString(1));
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                SanPham sanPham = new SanPham(maSP, maLoai, soLuong, ten, giaNhap, giaBan);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<SanPham> getAllSanPhamTheoGiaGiamDan() {
        List<SanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " order by giaBan desc";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maSP = Integer.parseInt(cursor.getString(0));
                int maLoai = Integer.parseInt(cursor.getString(1));
                String ten = cursor.getString(2);
                int soLuong = cursor.getInt(3);
                double giaNhap = cursor.getDouble(4);
                double giaBan = cursor.getDouble(5);
                SanPham sanPham = new SanPham(maSP, maLoai, soLuong, ten, giaNhap, giaBan);
                list.add(sanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

}
