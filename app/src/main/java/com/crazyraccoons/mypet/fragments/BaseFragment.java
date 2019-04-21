package com.crazyraccoons.mypet.fragments;

import android.databinding.ObservableBoolean;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment {

    public final ObservableBoolean mShowProgress = new ObservableBoolean(false);

    public void showToast(int resId){
        Toast.makeText(this.requireContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public void showProgress(boolean show){
        mShowProgress.set(show);
    }

}
