package fpoly.huynkph38086.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import fpoly.huynkph38086.duanmau.dao.ThuThuDAO;
import fpoly.huynkph38086.duanmau.fragment.AddUserFragment;
import fpoly.huynkph38086.duanmau.fragment.ChangePassFragment;
import fpoly.huynkph38086.duanmau.fragment.DoanhThuFragment;
import fpoly.huynkph38086.duanmau.fragment.LoaiSachFragment;
import fpoly.huynkph38086.duanmau.fragment.PhieuMuonFragment;
import fpoly.huynkph38086.duanmau.fragment.SachFragment;
import fpoly.huynkph38086.duanmau.fragment.ThanhVienFragment;
import fpoly.huynkph38086.duanmau.fragment.TopFragment;
import fpoly.huynkph38086.duanmau.model.ThuThu;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
//    Toolbar toolbar;
    View mHaederView;
    TextView edUser;
    ThuThuDAO ttDAO;
    public static ThuThu thuThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
//        toolbar = findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.baseline_menu_24);
//        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment pmFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.content, pmFragment)
                .commit();

        NavigationView nav = findViewById(R.id.nav);
        mHaederView = nav.getHeaderView(0);
        edUser = mHaederView.findViewById(R.id.tv_user);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        ttDAO = new ThuThuDAO(this);
        thuThu = ttDAO.getID(user);
        String username = thuThu.tenTT;
        edUser.setText("Chào mừng " + username + "!");

        if (user != null && user.equalsIgnoreCase("admin"))
            nav.getMenu().findItem(R.id.sub_addUser).setVisible(true);

        nav.setNavigationItemSelectedListener(item -> {
            int title = R.string.quan_ly_phieu_muon;
            Fragment frag = new PhieuMuonFragment();

            if(item.getItemId() == R.id.nav_phieuMuon) {
                title = R.string.quan_ly_phieu_muon;
                frag = new PhieuMuonFragment();
            }
            else if(item.getItemId() == R.id.nav_loaiSach) {
                title = R.string.quan_ly_loai_sach;
                frag = new LoaiSachFragment();
            } else if(item.getItemId() == R.id.nav_sach) {
                title = R.string.quan_ly_sach;
                frag = new SachFragment();
            } else if(item.getItemId() == R.id.nav_thanhVien) {
                title = R.string.quan_ly_thanh_vien;
                frag= new ThanhVienFragment();
            } else if(item.getItemId() == R.id.sub_top) {
                title = R.string.top_10_sach;
                frag = new TopFragment();
            } else if(item.getItemId() == R.id.sub_doanhThu) {
                title = R.string.doanh_thu;
                frag = new DoanhThuFragment();
            } else if(item.getItemId() == R.id.sub_addUser) {
                title = R.string.them_nguoi_dung;
                frag = new AddUserFragment();
            } else if(item.getItemId() == R.id.sub_pass) {
                title = R.string.doi_mat_khau;
                frag = new ChangePassFragment();
            } else if(item.getItemId() == R.id.sub_logout) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            } else drawer.openDrawer(GravityCompat.START);

            setTitle(title);
            manager.beginTransaction().replace(R.id.content, frag).commit();

            drawer.closeDrawers();

            return false;
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.home)
//            drawer.openDrawer(GravityCompat.START);
//        return super.onOptionsItemSelected(item);
//    }
}