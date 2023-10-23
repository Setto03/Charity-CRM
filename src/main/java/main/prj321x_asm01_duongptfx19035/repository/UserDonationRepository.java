package main.prj321x_asm01_duongptfx19035.repository;

import main.prj321x_asm01_duongptfx19035.model.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {

    @Query("select u from UserDonation u where u.donation.id = ?1")
    public List<UserDonation> getUserDonationsByDonationId(int id);
}
