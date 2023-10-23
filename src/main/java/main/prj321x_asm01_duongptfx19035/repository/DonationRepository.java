package main.prj321x_asm01_duongptfx19035.repository;

import main.prj321x_asm01_duongptfx19035.model.Donation;
import main.prj321x_asm01_duongptfx19035.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

    @Query("select u from Donation u where u.name like %?1% " +
            "or u.organization like %?1% " +
            "or u.phone like %?1% " +
            "or u.description like %?1% " +
            "or u.code like %?1%")
    List<Donation> findDonationsByKeyword(String keyword);
}
