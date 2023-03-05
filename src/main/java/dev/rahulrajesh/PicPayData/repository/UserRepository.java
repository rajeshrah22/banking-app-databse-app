package dev.rahulrajesh.PicPayData.repository;

import org.springframework.data.repository.CrudRepository;
import dev.rahulrajesh.PicPayData.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
