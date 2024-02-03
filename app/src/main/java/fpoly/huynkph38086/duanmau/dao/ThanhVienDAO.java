package fpoly.huynkph38086.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class ThanhVienDAO extends DAO{
    public ThanhVienDAO(Context context) {
        super(context, "ThanhVien", "maTV");
    }

    void putValues(@NonNull ThanhVien tv){
        values = new ContentValues();
        values.put("hoTen", tv.tenTV);
        values.put("ngaySinh", tv.namSinh);
    }

    public long insert(@NonNull ThanhVien tv) {
        putValues(tv);
        return insert();
    }

    public int update(@NonNull ThanhVien tv) {
        putValues(tv);
        return update(String.valueOf(tv.maTV));
    }

    public List<ThanhVien> getAll() {
        return getData(saf);
    }

    public ThanhVien getID(String ID) {
        List<ThanhVien> list = getData(saf_w, ID);
        return list.get(0);
    }

    public List<ThanhVien> getData(String sql, String... selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2)));
            while (cursor.moveToNext());
        } else Toast.makeText(context, "Dữ liệu thành viên trống", Toast.LENGTH_SHORT).show();
        return list;
    }
}
