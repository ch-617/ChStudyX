package com.xi.gua.chstudyx.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xi.gua.chstudyx.R;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFalseColorFilter;
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

    public Bitmap getGPUinageFromAssets(int progress,Bitmap bitmap, int flag){
        // 使用GPUImage处理图像
        gpuImage.setImage(bitmap);
        if (flag == 1) {
            //设置饱和度
            gpuImage.setFilter(new GPUImageSaturationFilter(progress * 0.02f));
        } else if (flag == 2) {
            //要实现亮度的调整仅需将Filter改为GPUImageBrightnessFilter，并且在设置progress的时候在区间0-1之间设置便可
            gpuImage.setFilter(new GPUImageBrightnessFilter(progress * 0.02f - 1));
        } else if (flag == 3) {
            // 对比度
            gpuImage.setFilter(new GPUImageContrastFilter(progress * 0.04f));
        }
        bitmap = gpuImage.getBitmapWithFilterApplied();
        return bitmap;

    }
}
