package com.fpoly.duan1demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fpoly.duan1demo.R;
import com.fpoly.duan1demo.object.HoaDonChiTiet;

import java.util.List;

public class HoaDonChiTietAdaper extends BaseAdapter {
    final Context context;
    final List<HoaDonChiTiet> list;

    public HoaDonChiTietAdaper(Context context, List<HoaDonChiTiet> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_don_hang, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvSanPham = view.findViewById(R.id.tvSanPhamGH);
            viewHolder.tvGia = view.findViewById(R.id.tvGiaGH);
            viewHolder.tvSoluong = view.findViewById(R.id.tvSoLuongGH);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HoaDonChiTiet hoaDonChiTiet = list.get(i);
        viewHolder.tvSanPham.setText("" + hoaDonChiTiet.getGioHang().getTen());
        viewHolder.tvGia.setText("" + Math.round(hoaDonChiTiet.getGioHang().getGia() * hoaDonChiTiet.getGioHang().getSoLuong()));
        viewHolder.tvSoluong.setText("" + hoaDonChiTiet.getGioHang().getSoLuong());
        return view;
    }

    private static class ViewHolder {
        TextView tvSanPham, tvSoluong, tvGia;
    }
}

