package fpoly.huynkph38086.duanmau.model;

import android.widget.Toast;

import java.sql.Date;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV, maSach, trangThai, tienThue;
    public long ngayMuon;

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, int trangThai, int tienThue, long ngayMuon) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.trangThai = trangThai;
        this.tienThue = tienThue;
        this.ngayMuon = ngayMuon;
    }

    public PhieuMuon() {

    }
}
