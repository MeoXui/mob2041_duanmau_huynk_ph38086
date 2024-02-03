package fpoly.huynkph38086.duanmau.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class ThanhVienSpinner extends ArrayAdapter<ThanhVien> {
    Context context;
    List<ThanhVien> list;
    TextView tvTenTV;

    public ThanhVienSpinner(@NonNull Context context, List<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @SuppressLint({"WrongViewCast", "InflateParams", "ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = runcode(position);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = runcode(position);
        return view;
    }

    View runcode(int position){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_spinner,null);

        ThanhVien item = list.get(position);

        tvTenTV = view.findViewById(R.id.tv_ten);
        tvTenTV.setText(item.tenTV);

        return view;
    }
}
