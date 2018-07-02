package com.emrekose.famula.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.emrekose.famula.util.GlideApp;

public class ImageBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        if (url != null && !url.equals("")) {
            GlideApp.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }
}
