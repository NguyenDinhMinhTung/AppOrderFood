package com.example.megas.apporderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.megas.apporderfood.DAO.BanAnDAO;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edThemTenBanAn;
    Button btnDongYThemBanAn;
    BanAnDAO banAnDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);

        edThemTenBanAn = findViewById(R.id.edThemTenBanAn);
        btnDongYThemBanAn = findViewById(R.id.btnDongYThemBanAn);

        banAnDAO = new BanAnDAO(this);
        btnDongYThemBanAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTenBanAn = edThemTenBanAn.getText().toString();

        if (sTenBanAn != null && !sTenBanAn.equals("")) {
            boolean kiemtra = banAnDAO.themBanAn(sTenBanAn);
            Intent intent = new Intent();
            intent.putExtra("ketquathem", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
