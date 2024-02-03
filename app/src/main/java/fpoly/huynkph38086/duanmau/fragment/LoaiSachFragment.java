package fpoly.huynkph38086.duanmau.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.adapter.LoaiSachAdapter;
import fpoly.huynkph38086.duanmau.dao.LoaiSachDAO;
import fpoly.huynkph38086.duanmau.model.LoaiSach;

public class LoaiSachFragment extends ListFragment {
    List<LoaiSach> list;
    EditText edMaLS, edTenLS;
    Button btnSave, btnCancel;
    public LoaiSachDAO dao;
    public LoaiSach item;

    @Override
    public void updateList() {
        list = dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        super.updateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater,container,savedInstanceState);
        dao = new LoaiSachDAO(getActivity());

        updateList();

        fab.setOnClickListener(v -> openDialog(getActivity(),0));

//        lv.setOnItemLongClickListener((parent, view1, position, id) -> {
//            item = list.get(position);
//            openDialog(getActivity(), 1);
//            return false;
//        });

        return view;
    }

    public void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loai_sach);

        edMaLS = dialog.findViewById(R.id.ed_ma_ls);
        edTenLS = dialog.findViewById(R.id.ed_ten_ls);
        btnSave = dialog.findViewById(R.id.btn_save);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        edMaLS.setEnabled(false);

        if (type == 1){
            edMaLS.setText(String.valueOf(item.maLoai));
            edTenLS.setText(item.tenLoai);
        }

        btnSave.setOnClickListener(v -> {
            item = new LoaiSach();
            item.tenLoai = edTenLS.getText().toString();

            if(validate() <= 0) return;
            if(type == 0) insert(context);
            if(type == 1) update(context);

            updateList();
            dialog.dismiss();
        });
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    void insert(Context context) {
        if(dao.insert(item) > 0)
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
    }

    public void update(Context context) {
        item.maLoai =  Integer.parseInt(edMaLS.getText().toString());
        if(dao.update(item) > 0)
            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
    }

    int validate() {
        if(edTenLS.getText().length() == 0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}