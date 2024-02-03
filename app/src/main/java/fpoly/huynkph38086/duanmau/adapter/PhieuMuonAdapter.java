package fpoly.huynkph38086.duanmau.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.dao.SachDAO;
import fpoly.huynkph38086.duanmau.dao.ThanhVienDAO;
import fpoly.huynkph38086.duanmau.fragment.PhieuMuonFragment;
import fpoly.huynkph38086.duanmau.model.PhieuMuon;
import fpoly.huynkph38086.duanmau.model.Sach;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    Context context;
    PhieuMuonFragment fragment;
    List<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvTrangThai, tvNgayMuon;
    ImageButton ibEdit, ibDel;

    ThanhVienDAO TVdao;
    SachDAO Sdao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(Context context, PhieuMuonFragment fragment, List<PhieuMuon> list){
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @SuppressLint({"WrongViewCast", "InflateParams", "ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phieu_muon, null);
        }

        PhieuMuon item = list.get(position);
        if(item == null) return view;

        tvMaPM = view.findViewById(R.id.tv_ma_pm);
        tvTenTV = view.findViewById(R.id.tv_ten_tv);
        tvTenSach = view.findViewById(R.id.tv_ten_sach);
        tvTienThue = view.findViewById(R.id.tv_tien_thue);
        tvTrangThai = view.findViewById(R.id.tv_trang_thai);
        tvNgayMuon = view.findViewById(R.id.tv_ngay_muon);
        ibEdit = view.findViewById(R.id.ib_edit);
        ibDel = view.findViewById(R.id.ib_delete);

        TVdao = new ThanhVienDAO(context);
        Sdao = new SachDAO(context);

        ThanhVien tv = TVdao.getID(String.valueOf(item.maTV));
        Sach sach = Sdao.getID(String.valueOf(item.maSach));

        Date date = new Date(item.ngayMuon);

        tvMaPM.setText("Mã phiếu: " + item.maTT);
        tvTenTV.setText("Người mượn: " + tv.tenTV);
        tvTenSach.setText("Sách: " + sach.tenSach);
        tvTienThue.setText(item.tienThue + "VND");
        if (item.trangThai == 1) {
            tvTrangThai.setTextColor(Color.BLACK);
            tvTrangThai.setText("Đã trả sách.");
        }
        else {
            tvTrangThai.setTextColor(Color.RED);
            tvTrangThai.setText("Chưa trả sách.");
        }
        tvNgayMuon.setText(sdf.format(date));

        String ID = String.valueOf(item.maPM);

        ibEdit.setOnClickListener(v -> {
            fragment.item = item;
            fragment.beforeDialog(context, 1);
        });

        ibDel.setOnClickListener(v -> {
            fragment.updateList();
            fragment.delete(fragment.dao, ID);
        });

        return view;
    }
}
