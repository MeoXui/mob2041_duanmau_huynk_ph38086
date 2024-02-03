package fpoly.huynkph38086.duanmau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.dao.DAO;

public class ListFragment extends Fragment {
    ListView lv;
    FloatingActionButton fab;
    Dialog dialog;
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        lv = view.findViewById(R.id.lv);
        fab = view.findViewById(R.id.fab);

        return view;
    }

    public void updateList() {
        lv.setAdapter(adapter);
    }

    public void delete(DAO dao, String ID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", (dialog1, which) -> {
            dao.delete(ID);
            updateList();
            dialog.cancel();
        });
        builder.setNegativeButton("Không",(dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}