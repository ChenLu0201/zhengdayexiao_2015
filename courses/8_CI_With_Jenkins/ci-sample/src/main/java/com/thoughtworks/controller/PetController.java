package com.thoughtworks.controller;

import com.google.common.collect.Lists;
import com.thoughtworks.dto.PetDTO;
import com.thoughtworks.model.Pet;
import com.thoughtworks.repository.PetRepository;
import com.thoughtworks.transformer.PetTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Pet> list() {
        return Lists.newArrayList(petRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pet fetch(@PathVariable int id) {
        return petRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Pet save(@RequestBody PetDTO pet) {
        Pet savedPet = petRepository.save(PetTransformer.transform(pet));
        return savedPet;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Pet update(@PathVariable int id, @RequestBody PetDTO pet) {
        Pet one = petRepository.findOne(id);
        Pet transformedPet = PetTransformer.transform(pet);

        one.name = transformedPet.name;
        one.gender = transformedPet.gender;
        one.type = transformedPet.type;
        return petRepository.save(one);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        petRepository.delete(id);
    }
}
