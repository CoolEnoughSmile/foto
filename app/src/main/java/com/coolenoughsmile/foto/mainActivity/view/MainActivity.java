package com.coolenoughsmile.foto.mainActivity.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.base.BaseActivity;
import com.coolenoughsmile.foto.liftActivity.view.LiftActivity;
import com.coolenoughsmile.foto.liftRecieveActivity.view.LiftRecieveActivity;
import com.coolenoughsmile.foto.loginActivity.model.User;
import com.coolenoughsmile.foto.loginActivity.view.LoginActivity;
import com.coolenoughsmile.foto.mainActivity.presenter.MainPresenter;
import com.coolenoughsmile.foto.mainActivity.presenter.MainPresenterImpl;
import com.coolenoughsmile.foto.modifyPasswordActivity.view.ModifyPasswordActivity;
import com.coolenoughsmile.foto.modifyPersonalInfoActivity.view.ModifyPersonalInfoActivity;
import com.coolenoughsmile.foto.owerIdentifyActivity.view.OwerIdentifyActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

import cn.bmob.v3.BmobUser;

import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

/**
 * Created by CoolEnoughSmile on 2017/7/20.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener,MainView{

    private DrawerLayout drawerLayout;
    private Button sliding_menu_btn,lift_btn,recieve_order_btn;
    private LinearLayout menu_modify_information_btn,menu_owner_identification_btn,menu_modify_password_btn,menu_logout_btn;
    private SimpleDraweeView menu_logo,content_logo;
    private MainPresenter mainPresenter;
    private TextView username_view;

    private static String DEFAULT_HEAD ="http://bmob-cdn-13292.b0.upaiyun.com/2017/08/04/46e29336d6d24f929416380afb03aa33.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter=new MainPresenterImpl(this);
        initView();
        setListener();
        loadUserLogo();
        loadElseName();
    }

    private void loadElseName() {
        User user=BmobUser.getCurrentUser(User.class);
        if (user.getmElsename()!=null&&!"".equals(user.getmElsename())){
            username_view.setText(user.getmElsename());
        }
    }

    private void loadUserLogo() {
        User user=BmobUser.getCurrentUser(User.class);
        String logo=user.getmLogo();
        if (logo != null && !logo.equals("")) {
            Uri uri = Uri.parse(logo);
            menu_logo.setImageURI(uri);
            content_logo.setImageURI(uri);
        } else {
            menu_logo.setImageURI(DEFAULT_HEAD);
            content_logo.setImageURI(DEFAULT_HEAD);
        }
    }

    private void setListener() {
        sliding_menu_btn.setOnClickListener(this);
        lift_btn.setOnClickListener(this);
        recieve_order_btn.setOnClickListener(this);
        menu_owner_identification_btn.setOnClickListener(this);
        menu_modify_password_btn.setOnClickListener(this);
        menu_modify_information_btn.setOnClickListener(this);
        menu_logout_btn.setOnClickListener(this);
        menu_logo.setOnClickListener(this);
    }

    private void initView() {
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        sliding_menu_btn= (Button) findViewById(R.id.sliding_menu_btn);
        lift_btn= (Button) findViewById(R.id.lift_btn);
        recieve_order_btn= (Button) findViewById(R.id.recieve_order_btn);
        menu_owner_identification_btn= (LinearLayout) findViewById(R.id.menu_owner_identification_btn);
        menu_modify_password_btn= (LinearLayout) findViewById(R.id.menu_modify_password_btn);
        menu_modify_information_btn= (LinearLayout) findViewById(R.id.menu_modify_information_btn);
        menu_logout_btn= (LinearLayout) findViewById(R.id.menu_logout_btn);
        menu_logo= (SimpleDraweeView) findViewById(R.id.menu_logo);
        content_logo= (SimpleDraweeView) findViewById(R.id.content_logo);
        username_view= (TextView) findViewById(R.id.username_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sliding_menu_btn:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.lift_btn:
                startActivity(new Intent(this, LiftActivity.class));
                break;
            case R.id.recieve_order_btn:
                startActivity(new Intent(this, LiftRecieveActivity.class));
                break;
            case R.id.menu_logo:
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(MainActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .previewVideo(false)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .compressGrade(Luban.FIRST_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(true)// 是否裁剪 true or false
                        .compress(true)// 是否压缩 true or false
                        .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                        .glideOverride(200,200)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(1,1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                        .isGif(false)// 是否显示gif图片 true or false
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .openClickSound(false)// 是否开启点击声音 true or false
                        .selectionMedia(null)// 是否传入已选图片 List<LocalMedia> list
                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                        .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                        .compressMaxKB(Luban.FIRST_GEAR)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
//                        .compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
//                        .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                        .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                        .videoQuality()// 视频录制质量 0 or 1 int
//                        .videoSecond()// 显示多少秒以内的视频or音频也可适用 int
//                        .recordVideoSecond()//视频秒数录制 默认60s int
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            case R.id.menu_owner_identification_btn:
                startActivity(new Intent(this, OwerIdentifyActivity.class));
                break;
            case R.id.menu_modify_password_btn:
                startActivity(new Intent(this, ModifyPasswordActivity.class));
                break;
            case R.id.menu_modify_information_btn:
                startActivity(new Intent(this, ModifyPersonalInfoActivity.class));
                break;
            case R.id.menu_logout_btn:
                BmobUser.logOut();   //清除缓存用户对象
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    String path =selectList.get(0).getCompressPath();
                    mainPresenter.updateLogo(path);
                    break;
            }
        }
    }

    @Override
    public void success() {
        loadUserLogo();
        PictureFileUtils.deleteCacheDirFile(MainActivity.this);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
