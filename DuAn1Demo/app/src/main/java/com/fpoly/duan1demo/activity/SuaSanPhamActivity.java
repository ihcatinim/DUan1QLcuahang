package com.fpoly.duan1demo.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.database.LoaiSanPhamDAO;
import com.fpoly.duan1demo.database.SanPhamDAO;
import com.fpoly.duan1demo.object.LoaiSanPham;
import com.fpoly.duan1demo.object.SanPham;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SuaSanPhamActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    EditText edMa, edTen, edSoLuong, edGiaBan, edGiaNhap;
    Spinner spnDanhMuc;
    List<String> listTheLoai;
    ImageView imgSuaSanPham, imgThemSuaDanhMuc;
    SanPhamDAO sanPhamDAO;
    ArrayList<LoaiSanPham> list;
    int theLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        toolbar = findViewById(R.id.toolbar_sua_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anhXaView();


        doDuLieu();
        themDanhMuc_DonVi();

        //Đổ dữu liệu cho spinner:
        listTheLoai = new ArrayList<>();
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        listTheLoai = loaiSanPhamDAO.getAllTenLoaiSanPham();
        ArrayAdapter adapterTheLoai = new ArrayAdapter(this, R.layout.spinner_item, listTheLoai);
        spnDanhMuc.setAdapter(adapterTheLoai);

        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAllLoaiSanPham();
                theLoai = list.get(i).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imgSuaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSanPham();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SuaSanPhamActivity.this, SanPhamActivity.class));
    }

    private void themDanhMuc_DonVi() {
        imgThemSuaDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SuaSanPhamActivity.this, ThemLoaiSanPhamActivity.class));
            }
        });
    }

    private void anhXaView() {
        imgSuaSanPham = findViewById(R.id.imgLuuSuaSanPham);
        edMa = findViewById(R.id.edSuaMaMatHang);
        edMa.setEnabled(false);
        edTen = findViewById(R.id.edSuaTenMatHang);
        edSoLuong = findViewById(R.id.edSuaSoLuong);
        edGiaBan = findViewById(R.id.edSuaGiaBan);
        edGiaNhap = findViewById(R.id.edSuaGiaNhap);
        spnDanhMuc = findViewById(R.id.spnSuaDanhMuc);
        imgThemSuaDanhMuc = findViewById(R.id.themDanhMucSuaSanPham);
        sanPhamDAO = new SanPhamDAO(this);
    }


    public void updateSanPham() {

        if (edTen.getText().toString().equalsIgnoreCase("") || edSoLuong.getText().toString().equalsIgnoreCase("") || edGiaBan.getText().toString().equalsIgnoreCase("") || edGiaNhap.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Vui lòng điền chính xác thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        SanPham sanPham = new SanPham();
        sanPham.setMaLoai(theLoai);
        sanPham.setTen(edTen.getText().toString());
        sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
        sanPham.setGiaBan(Double.parseDouble(edGiaBan.getText().toString()));
        sanPham.setGiaNhap(Double.parseDouble(edGiaNhap.getText().toString()));
        Intent intent = getIntent();

        String ma = intent.getStringExtra("ma");
        sanPhamDAO.updateSanPham(sanPham, ma);
        Toast.makeText(SuaSanPhamActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        Intent myIten = new Intent(SuaSanPhamActivity.this, SanPhamActivity.class);
        startActivity(myIten);
        finish();
    }

    private void doDuLieu() {
        Intent intent = getIntent();
        String ma = intent.getStringExtra("ma");
        SanPham sanPham = sanPhamDAO.getSanPhamTheoMa(ma);
        edMa.setText(ma);
        edTen.setText(sanPham.getTen());
        edGiaBan.setText("" + Math.round(sanPham.getGiaBan()));
        edGiaNhap.setText("" + Math.round(sanPham.getGiaNhap()));
        edSoLuong.setText("" + sanPham.getSoLuong());
        spnDanhMuc.setSelection(sanPham.getMaLoai());
    }
}