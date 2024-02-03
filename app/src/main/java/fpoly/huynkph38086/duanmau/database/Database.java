package fpoly.huynkph38086.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    static final String dbname = "PNL";
    static final int dbVer = 3;

    public Database(Context context) {
        super(context, dbname, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ThuThu(" +
                "maTT text primary key," +
                "hoTen text not null," +
                "matKhau text not null)");

        db.execSQL("create table ThanhVien(" +
                "maTV integer primary key autoincrement," +
                "hoTen text not null," +
                "ngaySinh text not null)");

        db.execSQL("create table LoaiSach(" +
                "maLoai integer primary key autoincrement," +
                "tenLoai text not null)");

        db.execSQL("create table Sach(" +
                "maSach integer primary key autoincrement," +
                "tenSach text not null," +
                "giaThue integer not null," +
                "maLoai integer references LoaiSach(maLoai))");

        db.execSQL("create table PhieuMuon(" +
                "maPM integer primary key autoincrement," +
                "maTT text references ThuThu(maTT)," +
                "maTV integer references ThanhVien(maTV)," +
                "maSach integer references Sach(maSach)," +
                "tienThue integer not null," +
                "ngayMuon integer not null," +
                "trangThai integer not null)");

        db.execSQL("INSERT INTO ThuThu (maTT, hoTen, matKhau)" +
                " VALUES ('admin', 'admin', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL("drop table if exists ThuThu");
            db.execSQL("drop table if exists ThanhVien");
            db.execSQL("drop table if exists LoaiSach");
            db.execSQL("drop table if exists Sach");
            db.execSQL("drop table if exists PhieuMuon");
            onCreate(db);
        }
    }
}
