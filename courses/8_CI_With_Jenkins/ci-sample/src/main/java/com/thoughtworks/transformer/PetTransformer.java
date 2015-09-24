package com.thoughtworks.transformer;

import com.thoughtworks.dto.PetDTO;
import com.thoughtworks.model.Gender;
import com.thoughtworks.model.Pet;

public class PetTransformer {
    public static Pet transform(PetDTO pet) {
        return new Pet(pet.getName(), Gender.valueOf(pet.getGender().toUpperCase()), pet.getType());
    }
}
