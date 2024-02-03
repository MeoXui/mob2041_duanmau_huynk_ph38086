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
import fpoly.huynkph38086.duanmau.fragment.LoaiSachFragment;
import fpoly.huynkph38086.duanmau.model.LoaiSach;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    LoaiSachFragment fragment;
    List<LoaiSach> list;
    TextView tvMaLS, tvTenLS;
    ImageButton ibEdit, ibDel;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, List<LoaiSach> list) {
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
        view = inflater.inflate(R.layout.item_loai_sach,null);

        LoaiSach item = list.get(position);
        if(item == null) return view;

        tvMaLS = view.findViewById(R.id.tv_ma_ls);
        tvTenLS = view.findViewById(R.id.tv_ten_ls);
        ibEdit = view.findViewById(R.id.ib_edit);
        ibDel = view.findViewById(R.id.ib_delete);

        tvMaLS.setText("Mã loại: " + item.maLoai);
        tvTenLS.setText("Thể loại: " + item.tenLoai);

        String ID = String.valueOf(item.maLoai);

        ibEdit.setOnClickListener(v -> {
            fragment.item = item;
            fragment.openDialog(context, 1);
        });

        ibDel.setOnClickListener(v -> {
            fragment.updateList();
            fragment.delete(fragment.dao, ID);
        });

        return view;
    }
}
