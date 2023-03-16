package dev.rahulrajesh.PicPayData.repository;

import dev.rahulrajesh.PicPayData.model.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, String> {
}
