package fpoly.huynkph38086.duanmau.fragment;

import android.app.Dialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fpoly.huynkph38086.duanmau.MainActivity;
import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.adapter.PhieuMuonAdapter;
import fpoly.huynkph38086.duanmau.adapter.SachSpinner;
import fpoly.huynkph38086.duanmau.adapter.ThanhVienSpinner;
import fpoly.huynkph38086.duanmau.dao.PhieuMuonDAO;
import fpoly.huynkph38086.duanmau.dao.SachDAO;
import fpoly.huynkph38086.duanmau.dao.ThanhVienDAO;
import fpoly.huynkph38086.duanmau.model.PhieuMuon;
import fpoly.huynkph38086.duanmau.model.Sach;
import fpoly.huynkph38086.duanmau.model.ThanhVien;

public class PhieuMuonFragment extends ListFragment {
    List<PhieuMuon> list;
    EditText edMaPM;
    Spinner spTV, spSach;
    CheckBox chkTrangThai;
    Button btnSave, btnCancel;
    public PhieuMuonDAO dao;
    public PhieuMuon item;

    ThanhVienSpinner TVS;
    List<ThanhVien> TVL;
    ThanhVienDAO TVD;
    ThanhVien TV;
    int TVp;

    SachSpinner SS;
    List<Sach> SL;
    SachDAO SD;
    Sach sach;
    int Sp;

//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date;

    @Override
    public void updateList() {
        list = dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        super.updateList();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater,container,savedInstanceState);
        dao = new PhieuMuonDAO(getActivity());

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
        TVD = new ThanhVienDAO(context);
        TVL = TVD.getAll();

        SD = new SachDAO(context);
        SL = SD.getAll();

        if(TVL.size() > 0 && SL.size() > 0) openDialog(context, type);
    }

    public void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phieu_muon);

        edMaPM = dialog.findViewById(R.id.ed_ma_pm);
        spTV = dialog.findViewById(R.id.sp_tv);
        spSach = dialog.findViewById(R.id.sp_sach);
        chkTrangThai = dialog.findViewById(R.id.chk_trang_thai);
        btnSave = dialog.findViewById(R.id.btn_save);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        TVS = new ThanhVienSpinner(context, TVL);
        spTV.setAdapter(TVS);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TV = TVL.get(position);
                TVp = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SS = new SachSpinner(context, SL);
        spSach.setAdapter(SS);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sach = SL.get(position);
                Sp = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaPM.setEnabled(false);

        if (type == 1){
            edMaPM.setText(String.valueOf(item.maPM));
            spTV.setSelection(TVp);
            spSach.setSelection(Sp);
            date = new Date(item.ngayMuon);
            chkTrangThai.setChecked(item.trangThai == 1);
        }

        btnSave.setOnClickListener(v -> {

            item = new PhieuMuon();
            item.maTT = MainActivity.thuThu.maTT;
            item.maTV = TV.maTV;
            item.maSach = sach.maSach;
            item.tienThue = sach.giaThue;

            if(chkTrangThai.isChecked()) item.trangThai = 1;
            else item.trangThai = 0;



            if(type == 0){
                date = Calendar.getInstance().getTime();
                item.ngayMuon = date.getTime();
                insert(context);
            }
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
        item.maPM =  Integer.parseInt(edMaPM.getText().toString());
        if(dao.update(item) > 0)
            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
    }
}
