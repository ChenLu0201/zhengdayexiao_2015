package com.thoughtworks.transformer;

import com.thoughtworks.dto.PetDTO;
import com.thoughtworks.model.Gender;
import com.thoughtworks.model.Pet;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class PetTransformerTest{

    @Test
    public void shouldTransformPetDTOToPet() throws Exception {
        PetDTO petDTO = new PetDTO("Obama", "FEMALE", "HUMAN");
        Pet pet = PetTransformer.transform(petDTO);
        Assert.assertThat(pet.name, is("Obama"));
        Assert.assertThat(pet.gender, is(Gender.FEMALE));
        Assert.assertThat(pet.type, is("HUMAN"));
    }
}