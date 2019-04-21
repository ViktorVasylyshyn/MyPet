package com.crazyraccoons.mypet.fragments.addnewpet;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyraccoons.mypet.R;
import com.crazyraccoons.mypet.data.model.PetItem;
import com.crazyraccoons.mypet.databinding.AddNewPetFragmentBinding;
import com.crazyraccoons.mypet.fragments.BaseFragment;
import com.crazyraccoons.mypet.utils.Const;
import com.crazyraccoons.mypet.viewmodels.PetItemsViewModel;
import com.crazyraccoons.mypet.viewmodels.setpetdata.SavePetDataViewModel;
import com.crazyraccoons.mypet.viewmodels.SetPetsDataViewModel;

import static com.crazyraccoons.mypet.utils.NetworkUtil.hasInternetConnection;

public class AddNewPetFragment extends BaseFragment {

    private AddNewPetFragmentBinding mBinding;
    private PetItem mPetItem;
    private Uri mResultUri;

    private SavePetDataViewModel mSavePetDataViewModel;
    private SetPetsDataViewModel mSetPetsDataViewModel;

    private AddNewPetCallback mAddNewPetCallback = new AddNewPetCallback() {
        @Override
        public void addNewPet() {
            performPetAdding();
        }

        @Override
        public void setProfileImage() {
            chooseProfileImage();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.add_new_pet_fragment, container, false);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSavePetDataViewModel = ViewModelProviders.of(this).get(SavePetDataViewModel.class);
        mSetPetsDataViewModel = ViewModelProviders.of(this).get(SetPetsDataViewModel.class);
        mBinding.setFragmentCallback(mAddNewPetCallback);
        mBinding.setSetPetsDataViewModel(mSetPetsDataViewModel);
        mBinding.setShowProgress(mShowProgress);

        initNewPetItem();
        initObservables();
    }

    private void initObservables() {
        mSavePetDataViewModel.getImageUrl().observe(this, this::applyImageUrl);
    }

    private void applyImageUrl(String imageUrl) {
        if (imageUrl != null) {
            mPetItem.setImageUrl(imageUrl);
            savePetItemToLocalDb();
        } else {
            showProgress(false);
            showToast(R.string.saving_failed);
        }
    }

    private void performPetAdding() {
        showProgress(true);
        if (checkInputData()) {
            attemptToSavePet();
        } else {
            showProgress(false);
            showToast(R.string.fields_input_error);
        }
    }

    private void initNewPetItem() {
        mSetPetsDataViewModel.getPetItem().observe(this, item -> {
            mPetItem = item;
            mBinding.setPetItem(mPetItem);
            changeGenderIcon(mPetItem.getGender());
            changeSortIcon(mPetItem.getSort());
        });
    }

    private void changeGenderIcon(String gender) {
        if (gender.equals(Const.BOY)) {
            mBinding.addPetGender.setImageResource(R.drawable.gender_sign_boy);
        } else {
            mBinding.addPetGender.setImageResource(R.drawable.gender_sign_girl);
        }
    }

    private void changeSortIcon(String sort) {
        switch (sort) {
            case Const.CAT:
                mBinding.addPetSort.setImageResource(R.drawable.cat_icon);
                return;
            case Const.DOG:
                mBinding.addPetSort.setImageResource(R.drawable.dog_icon);
                return;
            case Const.RACCOON:
                mBinding.addPetSort.setImageResource(R.drawable.raccoon_icon);
                return;
            default:
                throw new IllegalArgumentException("illegal argument");
        }
    }

    private void attemptToSavePet() {
        if (mResultUri == null) {
            savePetItemToLocalDb();
            return;
        }
        if (!hasInternetConnection(requireContext())) {
            showProgress(false);
            showToast(R.string.no_internet_connection);
            return;
        }
        mSavePetDataViewModel.savePetsImage(mPetItem, mResultUri);
    }

    private void savePetItemToLocalDb() {
        ViewModelProviders.of(this).get(PetItemsViewModel.class).insert(mPetItem);
        showProgress(false);
        showToast(R.string.saving_successful);
    }

    private boolean checkInputData() {
        return !mBinding.addPetName.getText().toString().isEmpty() &&
                !mBinding.addPetHeight.getText().toString().isEmpty() &&
                !mBinding.addPetWeight.getText().toString().isEmpty();
    }

    private void chooseProfileImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(getString(R.string.local_images_path));
        startActivityForResult(intent, Const.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Const.REQUEST_CODE) {
            mResultUri = data.getData();
            try {
                if (mResultUri != null) {
                    mPetItem.setImageUrl(mResultUri.toString());
                }
            } catch (NullPointerException e) {
                showToast(R.string.error);
            }
            mBinding.setPetItem(mPetItem);
        }
    }
}
