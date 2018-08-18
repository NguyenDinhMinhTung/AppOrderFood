package com.example.megas.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.megas.apporderfood.DTO.BanAnDTO;
import com.example.megas.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {
    SQLiteDatabase database;

    public BanAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean themBanAn(String tenban){
        ContentValues contentValues=new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,tenban);
        contentValues.put((CreateDatabase.TB_BANAN_TINHTRANG),"false");

        long kiemtra=database.insert(CreateDatabase.TB_BANAN,null,contentValues);
        if (kiemtra!=0){
            return true;
        }
        else
            return false;
    }

    public List<BanAnDTO> layTatCaBanAn(){
        List<BanAnDTO> list=new ArrayList<>();
        String truyvan="SELECT * FROM "+CreateDatabase.TB_BANAN;
        Cursor cursor=database.rawQuery(truyvan,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int maBan=cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN));
            String tenBan=cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN));

            BanAnDTO banAnDTO=new BanAnDTO(maBan,tenBan);
            list.add(banAnDTO);

            cursor.moveToNext();
        }
        return list;
    }
}
