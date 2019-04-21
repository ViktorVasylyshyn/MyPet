package com.crazyraccoons.mypet.bindings;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.crazyraccoons.mypet.R;
import com.crazyraccoons.mypet.utils.Const;

public class AppBindingAdapter {

    private AppBindingAdapter() {

    }

    @BindingAdapter("android:src")
    public static void loadProfileImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.profile_image_placeholder))
                .into(view);
    }

    @BindingAdapter({"gender"})
    public static void showPetsGender(ImageView view, String gender) {
        int resId;
        switch (gender) {
            case Const.BOY:
                resId = R.drawable.gender_sign_boy;
                break;
            case Const.GIRL:
                resId =  R.drawable.gender_sign_girl;
                break;
            default:
                throw new IllegalArgumentException("unknown state");
        }
        view.setImageResource(resId);
    }

    @BindingAdapter({"sort"})
    public static void showPetsSort(ImageView view, String sort) {
        int resId;
        switch (sort) {
            case Const.CAT:
                resId = R.drawable.cat_icon;
                break;
            case Const.DOG:
                resId = R.drawable.dog_icon;
                break;
            case Const.RACCOON:
                resId = R.drawable.raccoon_icon;
                break;
            default:
                throw new IllegalArgumentException("unknown state");
        }
        view.setImageResource(resId);
    }
}


