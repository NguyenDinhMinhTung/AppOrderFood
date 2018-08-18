package com.example.megas.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.megas.apporderfood.DTO.MonAnDTO;
import com.example.megas.apporderfood.Database.CreateDatabase;

public class MonAnDAO {
    SQLiteDatabase database;

    public MonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean themMonAn(MonAnDTO monAnDTO) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, monAnDTO.getTenMonAn());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN, monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI, monAnDTO.getMaLoai());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH, monAnDTO.getHinhAnh());

        long kiemtra = database.insert(CreateDatabase.TB_MONAN, null, contentValues);
        if (kiemtra != 0)
            return true;
        else
            return false;
    }
}
