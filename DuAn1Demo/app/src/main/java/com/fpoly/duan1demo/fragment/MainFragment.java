package com.fpoly.duan1demo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.adapter.FragmentAdapter;
import com.fpoly.duan1demo.object.GioHang;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    FragmentAdapter adapter;
    public static List<GioHang> gioHangList;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.TabLayout);
        viewPager2 = view.findViewById(R.id.ViewPager2);
        adapter = new FragmentAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        if (gioHangList == null) {
            gioHangList = new ArrayList<>();
        }

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.iconbanhang); //Ảnh tương ứng
                        tab.setText("Bán hàng"); //Tên tương ứng
                        break;
                    case 1:
                        tab.setIcon(R.drawable.iconhoadon); //Ảnh tương ứng
                        tab.setText("Hóa đơn"); //Tên tương ứng
                        break;
                    case 2:
                        tab.setIcon(R.drawable.iconthem); //Ảnh tương ứng
                        tab.setText("Thêm"); //Tên tương ứng
                        break;
                    //Tương tự
                }
            }
        });
        mediator.attach();
    }
}