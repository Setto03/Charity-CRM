package main.prj321x_asm01_duongptfx19035.repository;

import main.prj321x_asm01_duongptfx19035.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email like %?1% " +
            "or u.fullname like %?1% " +
            "or u.phone like %?1% " +
            "or u.username like %?1%")
    List<User> findUsersByKeyword(String keyword);

    User findUserById(int id);

    @Query("select u from User u where u.username = ?1")
    Optional<User> findUserByName(String name);
}
