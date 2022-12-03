package com.fpoly.duan1demo.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.database.SanPhamDAO;
import com.fpoly.duan1demo.object.SanPham;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    TextView tvMa, tvTen, tvSoLuong, tvGiaBan, tvGiaNhap, tvDanhMuc;
    Button btnChinhSua, btnXoa;
    ImageView imgSP;
    String ma;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        anhXaView();
        intent = getIntent();
        ma = intent.getStringExtra("sanPham");
        final SanPhamDAO sanPhamDAO = new SanPhamDAO(this);
        SanPham sanPham = sanPhamDAO.getSanPhamTheoMa(ma);
        tvMa.setText("Mã mặt hàng : " + sanPham.getMaSanPham());
        tvTen.setText("Tên mặt hàng : " + sanPham.getTen());
        tvSoLuong.setText("Số lượng : " + sanPham.getSoLuong());
        tvGiaNhap.setText("Giá nhập : " + Math.round(sanPham.getGiaNhap()) + " VNĐ");
        tvGiaBan.setText("Giá bán : " + Math.round(sanPham.getGiaBan()) + " VNĐ");
        tvDanhMuc.setText("Danh mục : " + sanPham.getMaLoai());

        // sự kiện update:
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ChiTietSanPhamActivity.this, SuaSanPhamActivity.class);
                intent1.putExtra("ma", ma);
                startActivity(intent1);
                finish();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new androidx.appcompat.app.AlertDialog.Builder(ChiTietSanPhamActivity.this);
                b.setTitle("Xác nhận");
                b.setMessage("Bạn có đồng ý xóa sản phẩm này không?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        long chk = sanPhamDAO.deleteSanPham(ma);
                        if (chk > 0) {
                            Toast.makeText(ChiTietSanPhamActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChiTietSanPhamActivity.this, SanPhamActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ChiTietSanPhamActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
            }
        });
    }

    private void anhXaView() {
        tvMa = findViewById(R.id.tvMaCTSP);
        tvTen = findViewById(R.id.tvTenCTSP);
        tvSoLuong = findViewById(R.id.tvSoLuongCTSP);
        tvGiaBan = findViewById(R.id.tvGiaBanCTSP);
        tvGiaNhap = findViewById(R.id.tvGiaNhapCTSP);
        tvDanhMuc = findViewById(R.id.tvDanhMucCTSP);
        btnChinhSua = findViewById(R.id.btnUpdateCTSP);
        btnXoa = findViewById(R.id.btnXoaSanPhamCTSP);
        imgSP = findViewById(R.id.imgCTSP);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChiTietSanPhamActivity.this, SanPhamActivity.class);
        startActivity(intent);
    }
}