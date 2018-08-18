package com.example.megas.apporderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.megas.apporderfood.DAO.NhanVienDAO;
import com.example.megas.apporderfood.DTO.NhanVienDTO;
import com.example.megas.apporderfood.Database.CreateDatabase;
import com.example.megas.apporderfood.FragmentApp.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edTenDangNhapDK, edMatKhauDK, edNgaySinhDK, edCMNDDK;
    Button btnDongYDK, btnThoatDK;
    RadioGroup rgGioiTinh;
    String sGioiTinh;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edTenDangNhapDK = findViewById(R.id.edTenDangNhapDK);
        edMatKhauDK = findViewById(R.id.edMatKhauDK);
        edNgaySinhDK = findViewById(R.id.edNgaySinhDK);
        edCMNDDK = findViewById(R.id.edCMNDDK);

        btnDongYDK = findViewById(R.id.btnDongYDK);
        btnThoatDK = findViewById(R.id.btnThoatDK);

        rgGioiTinh = findViewById(R.id.rgGioiTinh);

        btnDongYDK.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);
        edNgaySinhDK.setOnFocusChangeListener(this);

        nhanVienDAO=new NhanVienDAO(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDongYDK:
                String sTenDangNhap = edTenDangNhapDK.getText().toString();
                String sMatKhau = edMatKhauDK.getText().toString();

                switch (rgGioiTinh.getCheckedRadioButtonId()) {
                    case R.id.rdNam:
                        sGioiTinh = "Nam";
                        break;

                    case R.id.rdNu:
                        sGioiTinh = "Nu";
                        break;
                }

                String sNgaySinh = edNgaySinhDK.getText().toString();
                String sCMND = edCMNDDK.getText().toString();

                if (sTenDangNhap == null || sTenDangNhap == "") {
                    Toast.makeText(this, getResources().getString(R.string.nhaptendangnhap), Toast.LENGTH_SHORT).show();
                } else if (sMatKhau == null || sMatKhau == "") {
                    Toast.makeText(this, getResources().getString(R.string.nhapmatkhau), Toast.LENGTH_SHORT).show();
                }else{
                    NhanVienDTO nhanVienDTO=new NhanVienDTO();
                    nhanVienDTO.setTENDN(sTenDangNhap);
                    nhanVienDTO.setMATKHAU(sMatKhau);
                    nhanVienDTO.setCMND(sCMND);
                    nhanVienDTO.setGIOITINH(sGioiTinh);
                    nhanVienDTO.setNGAYSINH(sNgaySinh);

                    nhanVienDAO.ThemNhanVien(nhanVienDTO);
                    finish();
                }
                break;

            case R.id.btnThoatDK:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.edNgaySinhDK:
                if (hasFocus) {
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getFragmentManager(), "Ngay Sinh");
                }
                break;
        }
    }
}
