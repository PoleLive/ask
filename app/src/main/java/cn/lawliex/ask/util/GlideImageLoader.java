package cn.lawliex.ask.util;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

import cn.lawliex.ask.R;

/**
 * Created by Terence on 2017/1/2.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.gallery_pick_photo)
                .centerCrop()
                .into(galleryImageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
