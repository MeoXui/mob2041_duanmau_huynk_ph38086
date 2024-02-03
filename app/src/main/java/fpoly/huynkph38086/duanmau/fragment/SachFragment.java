package fpoly.huynkph38086.duanmau.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.adapter.SachAdapter;
import fpoly.huynkph38086.duanmau.adapter.LoaiSachSpinner;
import fpoly.huynkph38086.duanmau.dao.LoaiSachDAO;
import fpoly.huynkph38086.duanmau.dao.SachDAO;
import fpoly.huynkph38086.duanmau.model.LoaiSach;
import fpoly.huynkph38086.duanmau.model.Sach;

public class SachFragment extends ListFragment {
    List<Sach> list;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spLoaiSach;
    Button btnSave, btnCancel;
    public SachDAO dao;
    public Sach item;

    LoaiSachSpinner LSS;
    List<LoaiSach> LSL;
    LoaiSachDAO LSD;
    LoaiSach LS;
    int p;

    @Override
    public void updateList() {
        list = dao.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        super.updateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater,container,savedInstanceState);
        dao = new SachDAO(getActivity());

        updateList();

        fab.setOnClickListener(v -> beforeDialog(getActivity(),0));

//        lv.setOnItemLongClickListener((parent, view1, position, id) -> {
//            item = list.get(position);
//            openDialog(getActivity(), 1);
//            return false;
//        });

        return view;
    }

    public void beforeDialog(Context context, int type){
        LSD = new LoaiSachDAO(context);
        LSL = LSD.getAll();

        if(LSL.size() > 0) openDialog(context, type);
    }

    public void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);

        edMaSach = dialog.findViewById(R.id.ed_ma_sach);
        edTenSach = dialog.findViewById(R.id.ed_ten_sach);
        edGiaThue = dialog.findViewById(R.id.ed_gia_thue);
        spLoaiSach = dialog.findViewById(R.id.sp_ls);
        btnSave = dialog.findViewById(R.id.btn_save);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        LSS = new LoaiSachSpinner(context, LSL);
        spLoaiSach.setAdapter(LSS);
        spLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LS = LSL.get(position);
                p = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaSach.setEnabled(false);

        if (type == 1){
            edMaSach.setText(String.valueOf(item.maSach));
            edTenSach.setText(item.tenSach);
            edGiaThue.setText(String.valueOf(item.giaThue));
            spLoaiSach.setSelection(p);
        }

        btnSave.setOnClickListener(v -> {
            item = new Sach();
            item.tenSach = edTenSach.getText().toString();
            item.giaThue = Integer.parseInt(edGiaThue.getText().toString());
            item.maLoai = LS.maLoai;

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
        item.maLoai =  Integer.parseInt(edMaSach.getText().toString());
        if(dao.update(item) > 0)
            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
    }

    int validate() {
        if(edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
            return -1;
        }
        try{
            Integer.parseInt(edGiaThue.getText().toString());
        } catch (Exception e){
            Toast.makeText(getContext(), "Vui lòng nhập đúng định dạng.", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}