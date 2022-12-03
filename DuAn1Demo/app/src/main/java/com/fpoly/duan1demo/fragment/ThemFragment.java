package com.fpoly.duan1demo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.activity.KhachHangActivity;
import com.fpoly.duan1demo.activity.LoaiSanPhamActivity;
import com.fpoly.duan1demo.activity.SanPhamActivity;

public class ThemFragment extends Fragment {

    LinearLayout layout1, layout2, layout3;

    public ThemFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ThemFragment newInstance() {
        ThemFragment fragment = new ThemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_them, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXaView(view);
        //chuyển sang sản phẩm activity
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(SanPhamActivity.class);
            }
        });
        //chuyển sang loai san pham activity
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(LoaiSanPhamActivity.class);
            }
        });
        //chuyển sang khach hang activity
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenAct(KhachHangActivity.class);
            }
        });
    }

    public void anhXaView(View view) {
        layout1 = view.findViewById(R.id.LinearLayoutSP);
        layout2 = view.findViewById(R.id.LinearLayoutLSP);
        layout3 = view.findViewById(R.id.LinearLayoutKH);
    }

    public void chuyenAct(Class aClass) {
        Intent intent = new Intent(getActivity(), aClass);
        startActivity(intent);
    }
}