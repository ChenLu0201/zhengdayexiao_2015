package com.thoughtworks.repository;

import com.thoughtworks.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Integer> {

}
