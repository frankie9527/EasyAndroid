package org.easy.loader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/3
 * describe：
 **/
public class GlideUtils {
    private static int error;
    private static  int placeholder;
    public static void init(int placeholder,int error){
        GlideUtils.placeholder=placeholder;
        GlideUtils.error=error;
    }
    public static void loadPic(String str, ImageView img) {
        Glide.with(img.getContext()).
                load(str).
                centerCrop().placeholder(placeholder).into(img);
    }

    public static void loadCircular(final ImageView img,int url) {
        Glide.with(img.getContext()).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img);
    }



}
