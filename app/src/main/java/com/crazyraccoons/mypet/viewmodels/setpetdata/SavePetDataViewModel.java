package com.crazyraccoons.mypet.viewmodels.setpetdata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.crazyraccoons.mypet.data.model.PetItem;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SavePetDataViewModel extends AndroidViewModel {

    public SavePetDataViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<String> mImageUrl = new MutableLiveData<>();

    public LiveData<String> getImageUrl() {
        return mImageUrl;
    }

    public void savePetsImage(PetItem petData, Uri uri) {
        StorageReference filepath = FirebaseStorage.getInstance().getReference()
                .child(petData.getSort())
                .child(petData.getName());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap(uri).compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = filepath.putBytes(data);
        uploadTask.addOnFailureListener(e -> mImageUrl.postValue(null));
        uploadTask.addOnSuccessListener(taskSnapshot -> filepath.getDownloadUrl().addOnSuccessListener(url -> {
            if (url != null) {
                mImageUrl.postValue(url.toString());
            }
        }));
    }

    private Bitmap bitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media
                    .getBitmap(this.getApplication().getContentResolver(), uri);
        } catch (IOException e) {
            mImageUrl.setValue(null);
        }
        return bitmap;
    }
}

