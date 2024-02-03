package fpoly.huynkph38086.duanmau.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.huynkph38086.duanmau.R;
import fpoly.huynkph38086.duanmau.dao.ThuThuDAO;
import fpoly.huynkph38086.duanmau.model.ThuThu;

public class ChangePassFragment extends Fragment {
    EditText edOldPassword, edNewPassword, edReNewPass;
    Button btnSvae, btnCancel;
    ThuThuDAO ttDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);

        edOldPassword = v.findViewById(R.id.ed_old_password);
        edNewPassword = v.findViewById(R.id.ed_new_password);
        edReNewPass = v.findViewById(R.id.ed_re_new_pass);
        btnSvae = v.findViewById(R.id.btn_save);
        btnCancel = v.findViewById(R.id.btn_cancel);
        ttDao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(view -> {
            edOldPassword.setText("");
            edNewPassword.setText("");
            edReNewPass.setText("");
        });

        btnSvae.setOnClickListener(view -> {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            if (validate()>0) {
                ThuThu tt = ttDao.getID(user);
                tt.matKhau = edNewPassword.getText().toString();
                if(ttDao.update(tt)>0){
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    edOldPassword.setText("");
                    edNewPassword.setText("");
                    edReNewPass.setText("");
                } else Toast.makeText(getActivity(), "Thay dổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private int validate() {
        if(edOldPassword.length()==0 || edNewPassword.length()==0 || edReNewPass.length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return -1;
        }
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String oldPass = pref.getString("PASSWORD", ""),
                newPass = edOldPassword.getText().toString(),
                reNewPass = edNewPassword.getText().toString();
        if (!oldPass.equals(edOldPassword.getText().toString())) {
            Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if (!newPass.equals(reNewPass)){
            Toast.makeText(getContext(), "Mật khẩu nhập lại chưa chính xác", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return 1;
    }
}