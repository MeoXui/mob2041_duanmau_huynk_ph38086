package fpoly.huynkph38086.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import fpoly.huynkph38086.duanmau.dao.ThuThuDAO;

public class Login extends AppCompatActivity {

    EditText edUsername, edPassword;
    String sUsername, sPassword;
    CheckBox chkRemember;
    Button btnLogin, btnCancel;
    ThuThuDAO daoTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(R.string.login);
        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        chkRemember = findViewById(R.id.chk_remember);
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);
        daoTT = new ThuThuDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edUsername.setText(pref.getString("USERNAME", ""));
        edPassword.setText(pref.getString("PASSWORD", ""));
        chkRemember.setChecked(pref.getBoolean("REMEMBER", false));

        btnLogin.setOnClickListener(view -> {
            checkLogin();
            finish();
        });
        btnCancel.setOnClickListener(view -> finish());
    }

    private void checkLogin() {
        sUsername = edUsername.getText().toString();
        sPassword = edPassword.getText().toString();
        if (sUsername.isEmpty()) Toast.makeText(this, "Vui vòng nhập Tên đăng nhập",
                Toast.LENGTH_SHORT).show();
        else if (sPassword.isEmpty()) Toast.makeText(this, "Vui vòng nhập Mật khẩu",
                Toast.LENGTH_SHORT).show();
        else if (daoTT.checkLogin(sUsername,sPassword) > 0){
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            remember(sUsername, sPassword, chkRemember.isChecked());
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("user", sUsername);
            startActivity(intent);
        } else Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng",
                Toast.LENGTH_SHORT).show();
    }

    private void remember(String username, String password, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (status) {
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
        } else editor.clear();
        editor.putBoolean("REMEMBER", status);
        editor.commit();
    }
}