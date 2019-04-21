package com.crazyraccoons.mypet.viewmodels.setpetdata;

import com.crazyraccoons.mypet.data.model.PetItem;
import com.crazyraccoons.mypet.utils.Const;

public class SetPetsDataHelper {

    public PetItem initNewPetItem() {
        return new PetItem(null,
                Const.CAT,
                Const.BOY,
                null,
                null,
                null,
                null);
    }

    public PetItem setPetGender(PetItem petItem) {
        switch (petItem.getGender()) {
            case Const.BOY:
                petItem.setGender(Const.GIRL);
                break;
            case Const.GIRL:
                petItem.setGender(Const.BOY);
                break;
            default:
                throw new IllegalArgumentException("unknown state");
        }
        return petItem;

    }

    public PetItem setPetSort(PetItem petItem) {
        switch (petItem.getSort()) {
            case Const.CAT:
                petItem.setSort(Const.DOG);
                break;
            case Const.DOG:
                petItem.setSort(Const.RACCOON);
                break;
            case Const.RACCOON:
                petItem.setSort(Const.CAT);
                break;
            default:
                throw new IllegalArgumentException("unknown state");
        }
        return petItem;
    }
}
