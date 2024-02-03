package fpoly.huynkph38086.duanmau.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.dao.LoaiSachDAO;
import fpoly.huynkph38086.duanmau.fragment.SachFragment;
import fpoly.huynkph38086.duanmau.model.LoaiSach;
import fpoly.huynkph38086.duanmau.model.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoaiSach;
    ImageButton ibEdit, ibDel;

    public SachAdapter(@NonNull Context context, SachFragment fragment, List<Sach> list){
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @SuppressLint({"WrongViewCast", "InflateParams", "ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_sach,null);

        Sach item = list.get(position);
        if(item == null) return view;

        tvMaSach = view.findViewById(R.id.tv_ma_sach);
        tvTenSach = view.findViewById(R.id.tv_ten_sach);
        tvGiaThue = view.findViewById(R.id.tv_gia_thue);
        tvLoaiSach = view.findViewById(R.id.tv_loai_sach);
        ibEdit = view.findViewById(R.id.ib_edit);
        ibDel = view.findViewById(R.id.ib_delete);

        LoaiSachDAO LSDAO = new LoaiSachDAO(context);
        LoaiSach LS = LSDAO.getID(String.valueOf(item.maLoai));

        tvMaSach.setText("Mã sách: " + item.maSach);
        tvTenSach.setText("Tên sách: " + item.tenSach);
        tvGiaThue.setText("Giá thuê: " + item.giaThue);
        tvLoaiSach.setText("Thể loại: " + LS.tenLoai);

        String ID = String.valueOf(item.maSach);

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
