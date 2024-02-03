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
import fpoly.huynkph38086.duanmau.fragment.TopFragment;
import fpoly.huynkph38086.duanmau.model.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    Context context;
    TopFragment fragment;
    List<Top> list;
    TextView tvTenSach, tvSoLuong;

    public TopAdapter(Context context, TopFragment fragment, List<Top> list){
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
        view = inflater.inflate(R.layout.item_top,null);

        Top item = list.get(position);
        if(item == null) return view;

        tvTenSach = view.findViewById(R.id.tv_ten_sach);
        tvSoLuong = view.findViewById(R.id.tv_so_luong);

        tvTenSach.setText("Sách: " + item.sach.tenSach);
        tvSoLuong.setText("Số lượng: " + item.soLuong);

        return view;
    }
}
