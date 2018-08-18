package com.example.megas.apporderfood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.megas.apporderfood.CustomAdapter.AdapterHienThiLoaiMonAn;
import com.example.megas.apporderfood.DAO.LoaiMonAnDAO;
import com.example.megas.apporderfood.DAO.MonAnDAO;
import com.example.megas.apporderfood.DTO.LoaiMonAnDTO;
import com.example.megas.apporderfood.DTO.MonAnDTO;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_CODE_THEMLOAITHUCDON = 113;
    public static int REQUEST_CODE_MOHINH = 114;
    ImageButton imThemLoaiThucDon;
    Spinner spinLoaiThucDon;
    AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;
    LoaiMonAnDAO loaiMonAnDAO;
    MonAnDAO monAnDAO;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ImageView imHinhThucDon;
    Button btnDongYThemMonAn, btnThoatThemMonAn;
    String duongDanHinh;
    EditText edTenMonAn, edGiaTien;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        monAnDAO=new MonAnDAO(this);

        imThemLoaiThucDon = findViewById(R.id.imThemLoaiThucDon);
        spinLoaiThucDon = findViewById(R.id.spinnerLoaiMonAn);
        imHinhThucDon = findViewById(R.id.imHinhThucDon);
        btnDongYThemMonAn = findViewById(R.id.btnDongYThemMonAn);
        btnThoatThemMonAn = findViewById(R.id.btnThoatThemMonAn);
        edTenMonAn = findViewById(R.id.edThemTenMonAn);
        edGiaTien = findViewById(R.id.edThemGiaTien);

        hienThiSpinnerLoaiMonAn();

        btnDongYThemMonAn.setOnClickListener(this);
        btnThoatThemMonAn.setOnClickListener(this);
        imHinhThucDon.setOnClickListener(this);
        imThemLoaiThucDon.setOnClickListener(this);

    }

    void hienThiSpinnerLoaiMonAn() {
        loaiMonAnDTOList = loaiMonAnDAO.layDanhSachLoaiMonAn();
        adapterHienThiLoaiMonAn = new AdapterHienThiLoaiMonAn(this, R.layout.layout_custom_spinloaithucdon, loaiMonAnDTOList);
        spinLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imThemLoaiThucDon:
                Intent iThemLoaiMonAn = new Intent(ThemThucDonActivity.this, ThemLoaiThucDonActivity.class);
                startActivityForResult(iThemLoaiMonAn, REQUEST_CODE_THEMLOAITHUCDON);
                break;

            case R.id.imHinhThucDon:
                Intent iMoHinh = new Intent();
                iMoHinh.setType("image/*");
                iMoHinh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(iMoHinh, "MO HINH"), REQUEST_CODE_MOHINH);
                break;

            case R.id.btnDongYThemMonAn:
                int vitri = spinLoaiThucDon.getSelectedItemPosition();
                int maloai = loaiMonAnDTOList.get(vitri).getMaLoai();

                String tenMonAn = edTenMonAn.getText().toString();
                String giaTien = edGiaTien.getText().toString();

                if (tenMonAn != null && giaTien != null && !tenMonAn.equals("") && !giaTien.equals("")) {
                    MonAnDTO monAnDTO=new MonAnDTO();
                    monAnDTO.setGiaTien(giaTien);
                    monAnDTO.setHinhAnh(duongDanHinh);
                    monAnDTO.setMaLoai(maloai);
                    monAnDTO.setTenMonAn(tenMonAn);

                    boolean kiemtra = monAnDAO.themMonAn(monAnDTO);

                    if (kiemtra){
                        Toast.makeText(this,"Them thanh cong",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Them that bai",Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btnThoatThemMonAn:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_THEMLOAITHUCDON && resultCode == Activity.RESULT_OK) {
            Intent duLieu = data;
            boolean kiemtra = duLieu.getBooleanExtra("kiemtraloaithucdon", false);
            if (kiemtra) {
                hienThiSpinnerLoaiMonAn();
                Toast.makeText(this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_MOHINH && resultCode == Activity.RESULT_OK && data != null) {
            //imHinhThucDon.setImageURI(data.getData());      CÁCH KHÁC
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imHinhThucDon.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            duongDanHinh = data.getData().toString();
        }
    }
}
