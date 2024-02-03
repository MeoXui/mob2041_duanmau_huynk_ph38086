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
import fpoly.huynkph38086.duanmau.adapter.ThanhVienAdapter;
import fpoly.huynkph38086.duanmau.dao.ThanhVienDAO;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class ThanhVienFragment extends ListFragment {
    List<ThanhVien> list;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    public ThanhVienDAO dao;
    public ThanhVien item;

    @Override
    public void updateList() {
        list = dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        super.updateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater,container,savedInstanceState);
        dao = new ThanhVienDAO(getActivity());

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
        dialog.setContentView(R.layout.dialog_thanh_vien);

        edMaTV = dialog.findViewById(R.id.ed_ma_tv);
        edTenTV = dialog.findViewById(R.id.ed_ten_tv);
        edNamSinh = dialog.findViewById(R.id.ed_nam_sinh);
        btnSave = dialog.findViewById(R.id.btn_save);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        edMaTV.setEnabled(false);

        if (type == 1){
            edMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText(item.tenTV);
            edNamSinh.setText(String.valueOf(item.namSinh));
        }

        btnSave.setOnClickListener(v -> {
            item = new ThanhVien();
            item.tenTV = edTenTV.getText().toString();
            item.namSinh = Integer.parseInt(edNamSinh.getText().toString());

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
        item.maTV =  Integer.parseInt(edMaTV.getText().toString());
        if(dao.update(item) > 0)
            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
    }

    int validate() {
        if(edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return -1;
        }try{
            Integer.parseInt(edNamSinh.getText().toString());
        } catch (Exception e){
            Toast.makeText(getContext(), "Vui lòng nhập đúng định dạng.", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}