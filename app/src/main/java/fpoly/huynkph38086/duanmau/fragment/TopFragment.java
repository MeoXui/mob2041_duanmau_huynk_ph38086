package fpoly.huynkph38086.duanmau.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fpoly.huynkph38086.duanmau.adapter.TopAdapter;
import fpoly.huynkph38086.duanmau.dao.ThongKeDAO;
import fpoly.huynkph38086.duanmau.model.Top;

public class TopFragment extends ListFragment {
    List<Top> list;
    ThongKeDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater,container,savedInstanceState);
        dao = new ThongKeDAO(getActivity());
        list = dao.getTop10();
        adapter = new TopAdapter(getActivity(),this,list);
        super.updateList();
        return view;
    }
}