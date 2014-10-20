package com.tenghu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Arvin on 2014/10/20.
 */
public class ZegapainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_zegapain);
    }
}
