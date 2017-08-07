package com.coolenoughsmile.foto.base;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobApplication;

import cn.bmob.v3.Bmob;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public class BaseApplication extends MobApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        //初始化Bmob
        Bmob.initialize(this, "45c70b17a45cb72f0fd1681ee4274256");
        Fresco.initialize(this);
    }
}
