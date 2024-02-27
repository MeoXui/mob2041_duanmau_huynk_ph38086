package fpoly.huynkph38086.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

public class Welcome extends AppCompatActivity {
    EditText edMSV;
    Button btnNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }, 1500);

//        edMSV = findViewById(R.id.ed_msv);
//        btnNhap = findViewById(R.id.btn_nhap);
//
//        btnNhap.setOnClickListener(v -> {
//            String title, message;
//            boolean ph38086 = edMSV.getText().toString().equalsIgnoreCase("PH38086");
//            if(ph38086){
//                title = "Nhập thành công";
//                message = "OK";
//            } else {
//                title = "Nhập thất bại";
//                message = "Vui lòng nhập lại";
//            }
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(title);
//            builder.setMessage(message);
//            builder.setCancelable(true);
//            builder.setPositiveButton("OK", (dialog, which) -> {
//                if (ph38086) {
//                    startActivity(new Intent(getApplicationContext(), Login.class));
//                    finish();
//                } else dialog.cancel();
//            });
//            AlertDialog alert = builder.create();
//            if(!this.isFinishing()) alert.show();
//        });
    }
}