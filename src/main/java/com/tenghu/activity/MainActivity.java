package com.tenghu.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    private String tabHome,tabZegapain,tabTools;//Tab标签
    private TabHost tabHost;
    private Intent mainHome;//首页
    private Intent mainZegapain;//机器人页
    private Intent mainTools;//工具页
    private RadioGroup mainMenusGroup;//菜单组
    private RadioButton rbHome;//首页按钮
    private RadioButton rbZegapain;//机器人按钮
    private RadioButton rbTools;//工具按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();//初始化View
        initIntent();//初始化Intent对象
        addSpec();//添加TabSpec
    }

    /**
     * 初始化View
     */
    private void initView(){
        tabHost=getTabHost();//获取TabHost对象
        tabHome=getResources().getString(R.string.tab_home);//首页标签
        tabZegapain=getResources().getString(R.string.tab_zegapain);//机器人标签
        tabTools=getResources().getString(R.string.tab_tools);//工具标签
        mainMenusGroup= (RadioGroup) findViewById(R.id.main_menus_rg);
        mainMenusGroup.setOnCheckedChangeListener(rgOnCheckedChangeListener);//设置点击事件
        rbHome= (RadioButton) findViewById(R.id.rb_home);//首页按钮
        rbZegapain= (RadioButton) findViewById(R.id.rb_zegapain);//机器人按钮
        rbTools= (RadioButton) findViewById(R.id.rb_tools);//工具按钮
    }

    RadioGroup.OnCheckedChangeListener rgOnCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            dynamicChangeMenu(checkedId);
            switch (checkedId){
                //首页
                case R.id.rb_home:
                    tabHost.setCurrentTabByTag(tabHome);
                    break;
                //机器人
                case R.id.rb_zegapain:
                    tabHost.setCurrentTabByTag(tabZegapain);
                    break;
                //工具
                case R.id.rb_tools:
                    tabHost.setCurrentTabByTag(tabTools);
                    break;
            }
        }
    };

    /**
     * 动态改变底部菜单
     * @param checkedId 选中的菜单id
     */
    private void dynamicChangeMenu(int checkedId){
        //首页未选中
        Drawable homeNo=getResources().getDrawable(R.drawable.main_home_no);
        homeNo.setBounds(0,0,homeNo.getMinimumWidth(),homeNo.getMinimumHeight());
        //首页选中
        Drawable homeSe=getResources().getDrawable(R.drawable.main_home_se);
        homeSe.setBounds(0,0,homeSe.getMinimumWidth(),homeSe.getMinimumHeight());
        //机器人未选中
        Drawable zegapainNo=getResources().getDrawable(R.drawable.main_zegapain_no);
        zegapainNo.setBounds(0,0,zegapainNo.getMinimumWidth(),zegapainNo.getMinimumHeight());
        //首页选中
        Drawable zegapainSe=getResources().getDrawable(R.drawable.main_zegapain_se);
        zegapainSe.setBounds(0,0,zegapainNo.getMinimumWidth(),zegapainSe.getMinimumHeight());
        //工具未选中
        Drawable toolsNo=getResources().getDrawable(R.drawable.main_tools_no);
        toolsNo.setBounds(0,0,homeNo.getMinimumWidth(),toolsNo.getMinimumHeight());
        //首页选中
        Drawable toolsSe=getResources().getDrawable(R.drawable.main_tools_se);
        toolsSe.setBounds(0,0,toolsSe.getMinimumWidth(),toolsSe.getMinimumHeight());
        int[] menusIds={R.id.rb_home,R.id.rb_zegapain,R.id.rb_tools};//菜单id数组
        RadioButton[] menusRB={rbHome,rbZegapain,rbTools};//菜单数组
        for (int i=0;i<menusIds.length;i++){
            if(checkedId==menusIds[i]){
                menusRB[i].setTextColor(getResources().getColor(R.color.dark_blue));//选中的字体颜色
                //设置选中的图标
                switch (i){
                    case 0:menusRB[i].setCompoundDrawables(null,homeSe,null,null); break;
                    case 1:menusRB[i].setCompoundDrawables(null,zegapainSe,null,null);break;
                    case 2:menusRB[i].setCompoundDrawables(null,toolsSe,null,null);break;
                }

            }else{
                menusRB[i].setTextColor(getResources().getColor(R.color.dark_grey));//为选中的字体颜色
                //设置未选中的图标
                switch (i){
                    case 0:menusRB[i].setCompoundDrawables(null,homeNo,null,null);break;
                    case 1:menusRB[i].setCompoundDrawables(null,zegapainNo,null,null);break;
                    case 2:menusRB[i].setCompoundDrawables(null,toolsNo,null,null);break;
                }
            }
        }
    }

    /**
     * 初始化Intent对象
     */
    private void initIntent(){
        mainHome=new Intent(this,HomeActivity.class);
        mainZegapain=new Intent(this,ZegapainActivity.class);
        mainTools=new Intent(this,ToolsActivity.class);
    }

    /**
     * 添加TabSpec
     */
    private void addSpec(){
        tabHost.addTab(this.buildTabSpec(tabHome,R.string.main_home_str,mainHome));//添加首页TabSpec
        tabHost.addTab(this.buildTabSpec(tabZegapain, R.string.main_zegapain_str, mainZegapain));//添加机器人TabSpec
        tabHost.addTab(this.buildTabSpec(tabTools,R.string.main_tools_str,mainTools));//添加工具TabSpec
    }

    /**
     * 获取TabSpec
     * @param tabName
     * @param tagTable
     * @param intent
     * @return
     */
    private TabHost.TabSpec buildTabSpec(String tabName,int tagTable,Intent intent){
        return this.tabHost.newTabSpec(tabName).setIndicator(getResources().getString(tagTable)).setContent(intent);
    }
}
