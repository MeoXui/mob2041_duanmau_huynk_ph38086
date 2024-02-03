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
import fpoly.huynkph38086.duanmau.fragment.ThanhVienFragment;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ThanhVienFragment fragment;
    List<ThanhVien> list;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageButton ibEdit, ibDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, List<ThanhVien> list) {
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
        view = inflater.inflate(R.layout.item_thanh_vien,null);

        ThanhVien item = list.get(position);
        if(item == null) return view;

        tvMaTV = view.findViewById(R.id.tv_ma_tv);
        tvTenTV = view.findViewById(R.id.tv_ten_tv);
        tvNamSinh = view.findViewById(R.id.tv_nam_sinh);
        ibEdit = view.findViewById(R.id.ib_edit);
        ibDel = view.findViewById(R.id.ib_delete);

        tvMaTV.setText("Mã thành viên: " + item.maTV);
        tvTenTV.setText(item.tenTV);
        tvNamSinh.setText("Sinh năm: " + item.namSinh);

        String ID = String.valueOf(item.maTV);

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
