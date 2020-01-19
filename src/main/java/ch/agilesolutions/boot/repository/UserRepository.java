package ch.agilesolutions.boot.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ch.agilesolutions.boot.model.User;


@RepositoryRestResource
public interface UserRepository extends CrudRepository <User, Integer> {
}