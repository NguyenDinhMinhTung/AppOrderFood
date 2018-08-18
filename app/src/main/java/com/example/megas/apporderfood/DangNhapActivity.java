package com.example.megas.apporderfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.megas.apporderfood.DAO.NhanVienDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDangKyDN, btnDongYDN;
    EditText edtTenDangNhapDN, edtMatKhauDN;
    NhanVienDAO nhanVienDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDangKyDN = findViewById(R.id.btnDangKyDN);
        btnDongYDN = findViewById(R.id.btnDongYDN);
        edtMatKhauDN = findViewById(R.id.edMatKhauDN);
        edtTenDangNhapDN = findViewById(R.id.edTenDN);

        btnDangKyDN.setOnClickListener(this);
        btnDongYDN.setOnClickListener(this);

        nhanVienDAO = new NhanVienDAO(this);
        hienThiButtonDangKyVsDongY();
    }

    void hienThiButtonDangKyVsDongY() {
        boolean kiemtra = nhanVienDAO.kiemTraNhanVien();

        if (kiemtra) {
            btnDongYDN.setVisibility(View.VISIBLE);
            btnDangKyDN.setVisibility(View.GONE);
        } else {
            btnDangKyDN.setVisibility(View.VISIBLE);
            btnDongYDN.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangKyDN:
                Intent intent = new Intent(this, DangKyActivity.class);
                startActivity(intent);
                break;

            case R.id.btnDongYDN:
                String tenDangNhap = edtTenDangNhapDN.getText().toString();
                String matKhau = edtMatKhauDN.getText().toString();
                boolean kiemtra = nhanVienDAO.kiemTraDangNhap(tenDangNhap, matKhau);

                Intent intent1 = new Intent(this, TrangChuActivity.class);
                intent1.putExtra("tendangnhap", edtTenDangNhapDN.getText().toString());
                startActivity(intent1);

                /*if (kiemtra) {
                    Toast.makeText(this, "DangNhapThanhCong", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(this, TrangChuActivity.class);
                    intent1.putExtra("tendangnhap", edtTenDangNhapDN.getText().toString());
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "DangNhapThatBai", Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hienThiButtonDangKyVsDongY();
    }
}
