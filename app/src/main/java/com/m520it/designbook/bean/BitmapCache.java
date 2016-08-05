package com.m520it.designbook.bean;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * @author 梁家明
 * @time 2016/7/30  19:07
 * @desc ${TODD}
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> mCache;
    public BitmapCache(){
        int maxSize = 4 * 512 * 512;
        mCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String s) {
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        if (bitmap!=null){
            mCache.put(s,bitmap);
        }
    }
}
