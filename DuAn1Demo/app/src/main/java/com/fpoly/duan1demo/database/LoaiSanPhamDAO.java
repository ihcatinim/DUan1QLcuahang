package com.fpoly.duan1demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fpoly.duan1demo.object.LoaiSanPham;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamDAO {
    public static final String TABLE_NAME = "LoaiSanPham";
    public static final String SQL_LOAISANPHAM = "CREATE TABLE LoaiSanPham (" +
            "   maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
            "   tenLoai TEXt NOT NULL)";

    private final SQLiteDatabase sqLiteDatabase;

    public LoaiSanPhamDAO(Context context) {
        Mydatabase mydatabase = new Mydatabase(context);
        sqLiteDatabase = mydatabase.getWritableDatabase();
    }

    public long addLoaiSanPham(LoaiSanPham loaiSanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", loaiSanPham.getTenLoai());
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateLoaiSanPham(LoaiSanPham loaiSanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", loaiSanPham.getTenLoai());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, "maLoai = ?", new String[]{String.valueOf(loaiSanPham.getMaLoai())});
    }

    public long deleteLoaiSanPham(int ma) {
        return sqLiteDatabase.delete(TABLE_NAME, "maLoai = ?", new String[]{String.valueOf(ma)});
    }

    public List<LoaiSanPham> getAllLoaiSanPham() {
        List<LoaiSanPham> list = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maLoai = Integer.parseInt(cursor.getString(0));
                String tenLoai = cursor.getString(1);
                LoaiSanPham loaiSanPham = new LoaiSanPham(maLoai, tenLoai);
                list.add(loaiSanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<String> getAllTenLoaiSanPham() {
        List<String> list = new ArrayList<>();
        String query = "Select tenLoai from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String ten = cursor.getString(0);
                list.add(ten);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<LoaiSanPham> getAllLoaiSanPhamTheoMa(String ma) {
        List<LoaiSanPham> list = new ArrayList<>();
        String query = "Select DISTINCT * from " + TABLE_NAME + " where maLoai like '%" + ma + "%' or tenLoai like '%" + ma + "%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int maLoai = Integer.parseInt(cursor.getString(0));
                String tenLoai = cursor.getString(1);
                LoaiSanPham loaiSanPham = new LoaiSanPham(maLoai, tenLoai);
                list.add(loaiSanPham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
}
