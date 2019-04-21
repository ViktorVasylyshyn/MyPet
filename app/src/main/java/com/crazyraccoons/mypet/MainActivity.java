package com.crazyraccoons.mypet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyraccoons.mypet.databinding.ActivityMainBinding;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;

    private ActivityMainBinding mBinding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mNavController = Navigation.findNavController(this, R.id.navigation_container);

        if (savedInstanceState == null) {
            mNavController.navigate(R.id.addNewPetFragment);
        }

        initBottomNavigationMenu();
    }

    private void initBottomNavigationMenu() {
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int navDestination;
            switch (item.getItemId()) {
                case R.id.bnm_add_pet:
                    navDestination = R.id.action_global_addNewPetFragment;
                    break;
                case R.id.bnm_pets_list:
                    navDestination = R.id.action_global_myPetsListFragment;
                    break;
                case R.id.bnm_pets_list_extended:
                    navDestination = R.id.action_global_myPetsListExtendedFragment;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported destination");
            }
            mNavController.navigate(navDestination);
            return true;
        });
    }
}
