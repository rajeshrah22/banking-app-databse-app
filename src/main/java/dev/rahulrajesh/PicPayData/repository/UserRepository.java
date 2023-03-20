package dev.rahulrajesh.PicPayData.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import dev.rahulrajesh.PicPayData.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    //@Query("SELECT * FROM user WHERE CONCAT(user.fname, user.lname) LIKE '%:nameSubstring%'")
    public List<User> findByFnameIsContainingIgnoreCaseOrLnameIsContainingIgnoreCase
            (String fnameSubstring, String lnameSubstring, Pageable pageable);

    public User findByEmail(String email);
}
