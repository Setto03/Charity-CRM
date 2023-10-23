package main.prj321x_asm01_duongptfx19035.service;

import lombok.RequiredArgsConstructor;
import main.prj321x_asm01_duongptfx19035.model.Donation;
import main.prj321x_asm01_duongptfx19035.model.UserDonation;
import main.prj321x_asm01_duongptfx19035.repository.DonationRepository;
import main.prj321x_asm01_duongptfx19035.repository.UserDonationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    private final UserDonationRepository userDonationRepository;


    public List<Donation> getDonationList() {
        return donationRepository.findAll();
    }

    public void addNewDonation(Donation donation) {
        donation.setCreated(String.valueOf(new Date(System.currentTimeMillis())));
        donation.setTotal(0);

        donationRepository.save(donation);
    }

    public List<Donation> searchDonations(String keyword) {

        return donationRepository.findDonationsByKeyword(keyword);
    }

    public Donation findDonationById(int id) {
        return donationRepository.findById(id).orElseThrow();
    }

    public void updateDonation(Donation input) {

        Donation donation = findDonationById(input.getId());

        donation.setName(input.getName());
        donation.setStart(input.getStart());
        donation.setEnd(input.getEnd());
        donation.setOrganization(input.getOrganization());
        donation.setPhone(input.getPhone());
        donation.setDescription(input.getDescription());
        donation.setCode(input.getCode());
        donation.setStatus(input.getStatus());

        donationRepository.save(donation);
    }

    public void deleteDonation(int id) {
        donationRepository.deleteById(id);
    }

    public void endDonation(int id) {
        Donation donation = findDonationById(id);

        donation.setStatus("Ended");

        donationRepository.save(donation);
    }

    public void processDonate(int id, UserDonation userDonation) {
        Donation donation = findDonationById(id);

        var donate = UserDonation.builder()
                .donation(donation)
                .money(userDonation.getMoney())
                .name(userDonation.getName())
                .note(userDonation.getNote())
                .created(String.valueOf(new Date(System.currentTimeMillis())))
                .build();

        int current = donation.getTotal();
        donation.setTotal(current + userDonation.getMoney());

        userDonationRepository.save(donate);
        donationRepository.save(donation);
    }

    public List<UserDonation> getDonationHistory(int id) {
        return userDonationRepository.getUserDonationsByDonationId(id);
    }
}
