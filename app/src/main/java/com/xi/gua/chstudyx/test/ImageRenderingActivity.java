package com.xi.gua.chstudyx.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xi.gua.chstudyx.R;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBulgeDistortionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;

public class ImageRenderingActivity extends BaseActivity {
    private ImageView iv_forest;
    private SeekBar sb_bhd, sb_ld, sb_dbd;
    private GPUImage gpuImage;
    private Bitmap bitmap;
    private TextView tv_bhd, tv_ld, tv_dbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_rendering);
        setTitleName("Image Rendering");
        initView();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_forest);
        iv_forest.setImageBitmap(bitmap);
        gpuImage = new GPUImage(this);
    }

    private void initView() {
        iv_forest = findViewById(R.id.iv_forest);
        sb_bhd = findViewById(R.id.sb_bhd);
        sb_ld = findViewById(R.id.sb_ld);
        sb_dbd = findViewById(R.id.sb_dbd);
        tv_bhd = findViewById(R.id.tv_bhd);
        tv_ld = findViewById(R.id.tv_ld);
        tv_dbd = findViewById(R.id.tv_dbd);


        sb_bhd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度条的值改变
                iv_forest.setImageBitmap(getGPUinageFromAssets(progress, bitmap, 1));
                tv_bhd.setText("饱和度(0.0 - 2.0)： " + (progress * 0.02f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_ld.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iv_forest.setImageBitmap(getGPUinageFromAssets(progress, bitmap, 2));
                tv_ld.setText("亮度(-1.0 to 1.0)： " + (progress * 0.02f - 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_dbd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iv_forest.setImageBitmap(getGPUinageFromAssets(progress, bitmap, 3));
                tv_dbd.setText("对比度(0.0 to 4.0)： " + (progress * 0.04f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private float saturation = 1.0f;// 饱和度saturation: The degree of saturation or desaturation to apply to the image (0.0 - 2.0, with 1.0 as the default)
    private float brighness =  0.0f;// 亮度brightness value ranges from -1.0 to 1.0, with 0.0 as the normal level
    private float contrast = 1.0f;  // 对比度contrast value ranges from 0.0 to 4.0, with 1.0 as the normal level

    public Bitmap getGPUinageFromAssets(int progress,Bitmap mBitmap, int flag){
        if (flag == 1) {
            //设置饱和度
            saturation = progress * 0.02f;
//            gpuImage.setFilter(new GPUImageSaturationFilter(saturation));
        } else if (flag == 2) {
            //设置亮度
            brighness = progress * 0.02f - 1;
//            gpuImage.setFilter(new GPUImageBrightnessFilter(brighness));
        } else if (flag == 3) {
            // 对比度
            contrast = progress * 0.04f;
//            gpuImage.setFilter(new GPUImageContrastFilter(contrast));
        }

        // 使用GPUImage处理图像
//        Bitmap bitmap = mBitmap;
//        gpuImage.setImage(bitmap);
//        gpuImage.setFilter(new GPUImageSaturationFilter(saturation));
//        bitmap = gpuImage.getBitmapWithFilterApplied();
//
//        gpuImage.setImage(bitmap);
//        gpuImage.setFilter(new GPUImageBrightnessFilter(brighness));
//        bitmap = gpuImage.getBitmapWithFilterApplied();
//
//        gpuImage.setImage(bitmap);
//        gpuImage.setFilter(new GPUImageContrastFilter(contrast));
//        bitmap = gpuImage.getBitmapWithFilterApplied();

        // 使用组合滤镜处理
        gpuImage.setImage(mBitmap);
        GPUImageFilterGroup gpuImageFilterGroup = new GPUImageFilterGroup();
        gpuImageFilterGroup.addFilter(new GPUImageSaturationFilter(saturation));
        gpuImageFilterGroup.addFilter(new GPUImageBrightnessFilter(brighness));
        gpuImageFilterGroup.addFilter(new GPUImageContrastFilter(contrast));

        gpuImage.setFilter(gpuImageFilterGroup);
        mBitmap = gpuImage.getBitmapWithFilterApplied();

        return mBitmap;

    }

    public void btn_gray(View view) {
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageGrayscaleFilter());
        Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();
        iv_forest.setImageBitmap(bitmap);
    }

    public void btn_fish_eye(View view) {
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageBulgeDistortionFilter());
        Bitmap bitmap = gpuImage.getBitmapWithFilterApplied();
        iv_forest.setImageBitmap(bitmap);
    }

    public void btn_gray_and_fish_eye(View view) {
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageGrayscaleFilter());
        Bitmap bitmap1 = gpuImage.getBitmapWithFilterApplied();

        gpuImage.setImage(bitmap1);
        gpuImage.setFilter(new GPUImageBulgeDistortionFilter());
        Bitmap bitmap2 = gpuImage.getBitmapWithFilterApplied();
        iv_forest.setImageBitmap(bitmap2);
    }


    public void btn_reset(View view) {
        sb_bhd.setProgress(50);
        sb_ld.setProgress(50);
        sb_dbd.setProgress(25);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_forest);
        iv_forest.setImageBitmap(bitmap);
    }
}
