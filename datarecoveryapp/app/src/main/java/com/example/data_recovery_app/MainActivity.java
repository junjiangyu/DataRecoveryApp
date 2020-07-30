package com.example.data_recovery_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar bottomNavigationBar;
    private MainFragment mMainFragment;
    private VipFragment vVipFragment;
    private OrderFragment oOrderFragment;
    private MyFragment mMyFragment;
    private LoginOrderFragment lLoginOrderFragment;
    int loginStatus = LoginStatusData.getA();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * bottomNavigation 设置
         */

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#1ccbae");//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.shouye, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.huiyuan, "会员"))
                .addItem(new BottomNavigationItem(R.drawable.ding_huabanfuben, "订单"))
                .addItem(new BottomNavigationItem(R.drawable.wode, "我的"))
                .setFirstSelectedPosition(0)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

          setDefaultFragment();

        int id = getIntent().getIntExtra("id", 0);


        if (id == 1) {
            bottomNavigationBar.selectTab(0);
        }

        if (id == 2) {
            bottomNavigationBar.selectTab(1);
        }
       //判断loginstatus
        if (id == 3) {
                bottomNavigationBar.selectTab(2);

        }

        if (id == 4) {

            bottomNavigationBar.selectTab(3);
        }

    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        mMainFragment = MainFragment.newInstance("首页");
        transaction.replace(R.id.tb,mMainFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();

        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mMainFragment == null) {
                    mMainFragment = mMainFragment.newInstance("首页");
                }
                transaction.replace(R.id.tb, mMainFragment);
                break;
            case 1:
                if (vVipFragment == null) {
                    vVipFragment = vVipFragment.newInstance("会员");
                }
                transaction.replace(R.id.tb, vVipFragment);
                break;
            case 2:
                //显示未登录fragment
                if (loginStatus==0){
                    if (oOrderFragment == null) {
                        oOrderFragment = oOrderFragment.newInstance("订单");
                    }
                    transaction.replace(R.id.tb, oOrderFragment);
                    break;
                }
                //显示已登录fragment
                else if (loginStatus==1){
                    if (lLoginOrderFragment == null) {
                        lLoginOrderFragment = lLoginOrderFragment.newInstance("已登录订单");
                    }
                    transaction.replace(R.id.tb, lLoginOrderFragment);
                    break;
                }


            case 3:
                if (mMyFragment == null) {
                    mMyFragment = mMyFragment.newInstance("我的");
                }
                transaction.replace(R.id.tb, mMyFragment);
                break;




            default:
                break;
        }

        transaction.commit();// 事务提交
    }
    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
