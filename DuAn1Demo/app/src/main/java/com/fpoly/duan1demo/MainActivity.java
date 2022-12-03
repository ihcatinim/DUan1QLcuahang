package com.fpoly.duan1demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpoly.duan1demo.activity.DonHangActivity;
import com.fpoly.duan1demo.fragment.BaoCaoFragment;
import com.fpoly.duan1demo.fragment.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    View view;
    TextView tvUser;
    ImageView imgAvatar, imgGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.id_DrawerLayout);
        toolbar = findViewById(R.id.id_Toolbar);
        imgGioHang = findViewById(R.id.imgGioHang);
        //Đóng mở drawerLayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_dr, R.string.close_dr);
        toggle.syncState();
        navigationView = findViewById(R.id.id_NavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //Thay đổi khi có user
        view = navigationView.getHeaderView(0);
        imgAvatar = view.findViewById(R.id.imgImageAvatar);
        tvUser = view.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        tvUser.setText("Welcome " + user + " ! ");

        //Fragment tablayout
        toolbar.setTitle("Trang chủ");
        replaceFrament(MainFragment.newInstance());

        //Den gio hang
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DonHangActivity.class));
            }
        });
    }


    //Lựa chọn
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_Home) {
            toolbar.setTitle("Trang chủ");
            replaceFrament(MainFragment.newInstance());
        } else if (id == R.id.menu_BaoCao) {
            toolbar.setTitle("Báo cáo");
            replaceFrament(BaoCaoFragment.newInstance());
        } else if (id == R.id.menu_Exit) {
            finish();
        } else {
            return false;
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    //Chuyển fragment
    public void replaceFrament(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_FrameLayout, fragment);
        transaction.commit();
    }

    //Check drawerLayout
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView);
            } else {
                finish();
            }
        }
        return true;
    }
}