package com.fpoly.duan1demo.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.duan1demo.MainActivity;
import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.adapter.SanPhamAdapter;
import com.fpoly.duan1demo.database.SanPhamDAO;
import com.fpoly.duan1demo.object.GioHang;
import com.fpoly.duan1demo.object.SanPham;

import java.util.ArrayList;
import java.util.List;

public class BanHangFragment extends Fragment {

    EditText edTimKiem;
    ListView lvList;
    Spinner spnLocDanhSach;
    final String[] danhSachLC = {"Sắp xếp", "Giá ↑", "Giá ↓"};
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter sanPhamAdapter;
    TextView tvNull;
    int soLuong;
    static int tong = 0;
    MainActivity activity;
    TextView tvSoLuongBanHang;

    public BanHangFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BanHangFragment newInstance() {
        BanHangFragment fragment = new BanHangFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ban_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        setHasOptionsMenu(true);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, danhSachLC);
        spnLocDanhSach.setAdapter(adapter);
        doDuLieuTheoSpinner();
        timKiem();
        themSanPhamVaoGio();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainFragment.gioHangList.size() <= 0) {
            tong = 0;
            soLuong = 0;
            tvSoLuongBanHang.setVisibility(View.INVISIBLE);
            doDuLieuTheoSpinner();
        }
    }

    private void themSanPhamVaoGio() {
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (list.get(i).getSoLuong() <= 0) {
                    Toast.makeText(getContext(), "Mặt hàng này đã hết", Toast.LENGTH_SHORT).show();
                    return;
                }
                final Dialog dialog = new Dialog(getContext(), android.R.style.Theme);
                dialog.setContentView(R.layout.chon_so_luong_dialog);
                dialog.show();
                TextView tvTen = dialog.findViewById(R.id.tvTenSanPhamSL);
                TextView tvSoLuongSP = dialog.findViewById(R.id.tvSoLuongSanPhamSL);
                TextView tvGia = dialog.findViewById(R.id.tvGiaSanPhamSL);
                ImageView imgCong = dialog.findViewById(R.id.imgCongSoLuong);
                ImageView imgTru = dialog.findViewById(R.id.imgTruSoLuong);
                Button btnOk = dialog.findViewById(R.id.btnThemVaoGio);
                Button btnHuy = dialog.findViewById(R.id.btnHuyThemVaoGio);
                soLuong = 1;
                final TextView tvSoLuongMua = dialog.findViewById(R.id.tvSoLuongChonMua);

                tvTen.setText("" + list.get(i).getTen());
                tvSoLuongSP.setText("Còn :" + list.get(i).getSoLuong());
                tvGia.setText("" + list.get(i).getGiaBan() + " VNĐ");
                imgCong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (soLuong >= list.get(i).getSoLuong()) {
                            soLuong = list.get(i).getSoLuong();
                            tvSoLuongMua.setText("" + soLuong);
                            Toast.makeText(getContext(), "Đã đạt giới hạn số lượng", Toast.LENGTH_SHORT).show();
                        } else {
                            soLuong++;
                            tvSoLuongMua.setText("" + soLuong);
                        }
                    }
                });
                imgTru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (soLuong <= 0) {
                            soLuong = 0;
                        } else {
                            soLuong--;

                        }
                        tvSoLuongMua.setText("" + soLuong);
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tong += soLuong;
                        if (tong > 0) {
                            tvSoLuongBanHang.setText("" + tong);
                            tvSoLuongBanHang.setVisibility(View.VISIBLE);
                            boolean chk = false;
                            if (MainFragment.gioHangList.size() > 0) {
                                for (int j = 0; j < MainFragment.gioHangList.size(); j++) {
                                    if (MainFragment.gioHangList.get(j).getMa() == list.get(i).getMaSanPham()) {
                                        MainFragment.gioHangList.get(j).setSoLuong(MainFragment.gioHangList.get(j).getSoLuong() + soLuong);
                                        chk = true;
                                    }
                                }
                                if (!chk) {
                                    SanPham sanPham = list.get(i);
                                    GioHang gioHang = new GioHang();
                                    gioHang.setMa(sanPham.getMaSanPham());
                                    gioHang.setTen(sanPham.getTen());
                                    gioHang.setGia(sanPham.getGiaBan());
                                    gioHang.setSoLuong(soLuong);
                                    MainFragment.gioHangList.add(gioHang);
                                }
                            } else {
                                SanPham sanPham = list.get(i);
                                GioHang gioHang = new GioHang();
                                gioHang.setMa(sanPham.getMaSanPham());
                                gioHang.setTen(sanPham.getTen());
                                gioHang.setGia(sanPham.getGiaBan());
                                gioHang.setSoLuong(soLuong);
                                MainFragment.gioHangList.add(gioHang);
                            }
                        } else {
                            tvSoLuongBanHang.setVisibility(View.INVISIBLE);
                        }
                        list.get(i).setSoLuong(list.get(i).getSoLuong() - soLuong);

                        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
                        lvList.setAdapter(sanPhamAdapter);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void timKiem() {
        edTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<SanPham> list = sanPhamDAO.getAllSanPhamTheoMa(edTimKiem.getText().toString());
                SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getActivity(), list);
                lvList.setAdapter(sanPhamAdapter);
                tvNull.setVisibility(View.INVISIBLE);
                if (edTimKiem.getText().toString().equalsIgnoreCase("")) {
                    doDuLieuTheoSpinner();
                }
                if (list.size() <= 0) {
                    tvNull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void anhXaView(View view) {
        activity = (MainActivity) getActivity();
        edTimKiem = view.findViewById(R.id.edTimKiemSanPham);
        lvList = view.findViewById(R.id.lvListMatHang);
        spnLocDanhSach = view.findViewById(R.id.spnLocTimKiem);
        tvNull = view.findViewById(R.id.tvNull);
        tvSoLuongBanHang = activity.findViewById(R.id.tvSoLuongGioHang);
    }

    public void doDuLieuTheoSpinner() {
        spnLocDanhSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == adapterView.getItemAtPosition(1)) {
                    list = sanPhamDAO.getAllSanPhamTheoGiaTangDan();
                } else {
                    list = sanPhamDAO.getAllSanPhamTheoGiaGiamDan();
                }
                sanPhamAdapter = new SanPhamAdapter(getContext(), list);
                lvList.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}