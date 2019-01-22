package com.wenjie.mvp.view.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.wenjie.R;
import com.wenjie.utils.StatusBarUtil;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DashboardFrament dashboardFrament;
    private HomeFragment homeFragment;
    private NotificationsFragment notificationsFragment;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        mContent = homeFragment;
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content, mContent);
        ft.commit();
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton floatingActionButton = findViewById(R.id.id_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(drawer, "点击了fab", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeActivity.this, "你点击撤销了", Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent));
                setSnackbarColor(snackbar, ContextCompat.getColor(HomeActivity.this, R.color.colorAccent), ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                snackbar.show();
            }
        });
        StatusBarUtil.setTransparentForImageView(this,toolbar);
        StatusBarUtil.setTransparentForDrawerLayout(this , drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {

        View view = snackbar.getView();//获取Snackbar的view
        view.setBackgroundColor(backgroundColor);//修改view的背景色
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);//获取Snackbar的message控件，修改字体颜色
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                    }
                    switchContent(homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    if (dashboardFrament == null) {
                        dashboardFrament = new DashboardFrament();
                    }
                    switchContent(dashboardFrament);
                    return true;
                case R.id.navigation_notifications:
                    if (notificationsFragment == null) {
                        notificationsFragment = new NotificationsFragment();
                    }
                    switchContent(notificationsFragment);
                    return true;
            }
            return false;
        }
    };


    private Fragment mContent;

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            startActivity(new Intent(this , AActivity.class));
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//            startActivity(new Intent(this , ViewPagerActivity.class));
//        } else if (id == R.id.nav_slideshow) {
//            startActivity(new Intent(this , ArcViewActivity.class));
//        } else if (id == R.id.nav_manage) {
//            startActivity(new Intent(this , StickyItemDecorationActivity.class));
//        } else if (id == R.id.nav_share) {
//            startActivity(new Intent(this,ConstraintActivity.class));
//        } else if (id == R.id.nav_send) {
//            startActivity(new Intent(this , SmartTabActivity.class));
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
