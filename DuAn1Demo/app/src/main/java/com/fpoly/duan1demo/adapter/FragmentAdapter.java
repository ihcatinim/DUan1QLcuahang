package com.fpoly.duan1demo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fpoly.duan1demo.fragment.BanHangFragment;
import com.fpoly.duan1demo.fragment.HoaDonFragment;
import com.fpoly.duan1demo.fragment.ThemFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = BanHangFragment.newInstance();
                break;
            case 1:
                fragment = HoaDonFragment.newInstance();
                break;
            case 2:
                fragment = ThemFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
