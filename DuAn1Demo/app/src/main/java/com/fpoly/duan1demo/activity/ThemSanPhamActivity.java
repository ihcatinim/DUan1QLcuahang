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
import android.widget.LinearLayout;
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

public class ThemSanPhamActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgThemDanhMuc;
    Spinner spnDanhMuc;
    EditText edMa, edTen, edSoLuong, edGiaBan, edGiaNhap;
    int theLoai;
    List<String> listTheLoai;
    ArrayList<LoaiSanPham> list;
    SanPhamDAO sanPhamDAO;
    LinearLayout lnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_them_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sanPhamDAO = new SanPhamDAO(this);

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

        themDanhMuc_DonVi();
    }

    //sự kiên action bar
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
        finish();
        startActivity(new Intent(ThemSanPhamActivity.this, SanPhamActivity.class));
    }

    //ánh xạ
    public void anhXaView() {
        edMa = findViewById(R.id.edSuaMaMatHang);
        edMa.setEnabled(false);
        edTen = findViewById(R.id.edThemTenMatHang1);
        edSoLuong = findViewById(R.id.edThemSoLuong);
        edGiaBan = findViewById(R.id.edThemGiaBan);
        edGiaNhap = findViewById(R.id.edThemGiaNhap);
        spnDanhMuc = findViewById(R.id.spnThemDanhMuc);
        lnThem = findViewById(R.id.lnThem);
        imgThemDanhMuc = findViewById(R.id.themDanhMucThemSanPham);
    }

    private void themDanhMuc_DonVi() {
        imgThemDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemSanPhamActivity.this, ThemLoaiSanPhamActivity.class));
            }
        });
    }

    public void ThemSanPhamLuu(View view) {
        if (edTen.getText().toString().equalsIgnoreCase("") || edSoLuong.getText().toString().equalsIgnoreCase("")
                || edGiaBan.getText().toString().equalsIgnoreCase("") || edGiaNhap.getText().toString().equalsIgnoreCase("")) {
            Snackbar snackbar = Snackbar.make(lnThem, "Vui lòng điền chính xác thông tin", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        SanPham sanPham = new SanPham();
        sanPham.setMaLoai(theLoai);
        sanPham.setTen(edTen.getText().toString());
        sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
        sanPham.setGiaBan(Double.parseDouble(edGiaBan.getText().toString()));
        sanPham.setGiaNhap(Double.parseDouble(edGiaNhap.getText().toString()));
        long chk = sanPhamDAO.addSanPham(sanPham);
        if (chk > 0) {
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(this, SanPhamActivity.class);
            startActivity(intent);
        }
    }
}