package com.fpoly.duan1demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.database.LoaiSanPhamDAO;
import com.fpoly.duan1demo.object.LoaiSanPham;

public class ThemLoaiSanPhamActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    EditText edMa, edTen;
    LoaiSanPhamDAO loaiSanPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_san_pham);
        anhXaView();
        toolbar = findViewById(R.id.toolbar_them_loai_san_pham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhXaView() {
        edMa = findViewById(R.id.edSuaMaMatHang);
        edMa.setEnabled(false);
        edTen = findViewById(R.id.edThemTenMatHang);
        loaiSanPhamDAO = new LoaiSanPhamDAO(this);
    }

    public void ThemLoaiSanPhamLuu(View view) {
        String ten = edTen.getText().toString();
        if (ten.equalsIgnoreCase("")) {
            Toast.makeText(this, "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
            return;
        }
        LoaiSanPham loaiSanPham = new LoaiSanPham(0, ten);
        long chk = loaiSanPhamDAO.addLoaiSanPham(loaiSanPham);
        if (chk > 0) {
            Toast.makeText(getApplicationContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoaiSanPhamActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ThemLoaiSanPhamActivity.this, LoaiSanPhamActivity.class));
    }
}