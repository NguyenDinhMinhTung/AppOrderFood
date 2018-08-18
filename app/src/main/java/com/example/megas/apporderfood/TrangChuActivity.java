package com.example.megas.apporderfood;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.megas.apporderfood.FragmentApp.HienThiBanAnFragment;
import com.example.megas.apporderfood.FragmentApp.HienThiThucDonFragment;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    TextView txtTenNhanVienNavigation;
    android.support.v4.app.FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationviewTrangChu);
        toolbar = findViewById(R.id.toolbarTrangChu);

        View view = navigationView.getHeaderView(0);
        txtTenNhanVienNavigation = view.findViewById(R.id.txtTenNhanVienNavigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.mo, R.string.dong) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        Intent intent = getIntent();
        String tenDangNhap = intent.getStringExtra("tendangnhap");
        txtTenNhanVienNavigation.setText(tenDangNhap);

        fragmentManager=getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction tranHienThiBanAn=fragmentManager.beginTransaction();
        HienThiBanAnFragment hienThiBanAnFragment=new HienThiBanAnFragment();
        tranHienThiBanAn.replace(R.id.content,hienThiBanAnFragment);
        tranHienThiBanAn.commit();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itTrangChu:
                android.support.v4.app.FragmentTransaction tranHienThiBanAn=fragmentManager.beginTransaction();
                HienThiBanAnFragment hienThiBanAnFragment=new HienThiBanAnFragment();
                tranHienThiBanAn.replace(R.id.content,hienThiBanAnFragment);
                tranHienThiBanAn.commit();
                item.setChecked(true);
                getSupportActionBar().setTitle(R.string.trangchu);
                drawerLayout.closeDrawers();
                break;

            case R.id.itThucDon:
                android.support.v4.app.FragmentTransaction tranHienThiThucDon=fragmentManager.beginTransaction();
                HienThiThucDonFragment hienThiThucDonFragment=new HienThiThucDonFragment();
                tranHienThiThucDon.replace(R.id.content,hienThiThucDonFragment);
                tranHienThiThucDon.commit();
                item.setChecked(true);
                getSupportActionBar().setTitle(R.string.thucdon);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }
}
