package fpoly.huynkph38086.duanmau.fragment;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.dao.ThongKeDAO;

public class DoanhThuFragment extends Fragment {
    Button btnBD, btnKT, btnDoanhThu;
    EditText edBD, edKT;
    TextView tvDoanhThu;
    ThongKeDAO dao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mY, mM, mD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        btnBD = view.findViewById(R.id.btn_bd);
        edBD = view.findViewById(R.id.ed_bd);
        btnKT = view.findViewById(R.id.btn_kt);
        edKT = view.findViewById(R.id.ed_kt);
        btnDoanhThu = view.findViewById(R.id.btn_doanh_thu);
        tvDoanhThu = view.findViewById(R.id.tv_doanh_thu);
        dao = new ThongKeDAO(getActivity());

        btnBD.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            mY = c.get(Calendar.YEAR);
            mM = c.get(Calendar.MONTH);
            mD = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateBD, mY, mM, mD);
            d.show();
        });

        btnKT.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            mY = c.get(Calendar.YEAR);
            mM = c.get(Calendar.MONTH);
            mD = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateKT, mY, mM, mD);
            d.show();
        });

        btnDoanhThu.setOnClickListener(v -> {
            String sBD = edBD.getText().toString(), sKT = edKT.getText().toString();
            tvDoanhThu.setText("Tổng doanh thu: " + dao.getDoanhThu(sBD, sKT) + " VND.");
        });

        return view;
    }

    DatePickerDialog.OnDateSetListener mDateBD = (view, year, month, day) -> {
        mY = year;
        mM = month;
        mD = day;
        GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
        edBD.setText(sdf.format(c.getTime()));
    };
    DatePickerDialog.OnDateSetListener mDateKT = (view, year, month, day) -> {
        mY = year;
        mM = month;
        mD = day;
        GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
        edKT.setText(sdf.format(c.getTime()));
    };
}