package main.prj321x_asm01_duongptfx19035.controller;

import lombok.RequiredArgsConstructor;
import main.prj321x_asm01_duongptfx19035.model.Donation;
import main.prj321x_asm01_duongptfx19035.model.UserDonation;
import main.prj321x_asm01_duongptfx19035.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final DonationService donationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        List<Donation> donations = donationService.getDonationList();

        model.addAttribute("donations", donations);
        model.addAttribute("donate", new UserDonation());

        return "userDonationList";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchDonations(@RequestParam(name = "keyword") String keyword,
                                  Model model) {
        List<Donation> donations = donationService.searchDonations(keyword);

        model.addAttribute("donations", donations);
        model.addAttribute("donate", new UserDonation());

        return "userDonationList";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String donateInfo(Model model,
                             @RequestParam(name = "id") int id) {
        Donation donation = donationService.findDonationById(id);
        List<UserDonation> userDonations = donationService.getDonationHistory(id);

        model.addAttribute("donation", donation);
        model.addAttribute("donates", userDonations);
        model.addAttribute("donate", new UserDonation());

        return "userDonateInfo";
    }

    @RequestMapping(value = "/donate", method = RequestMethod.POST)
    public String processDonateInfo(@ModelAttribute(name = "donate") UserDonation userDonation,
                                    @RequestParam(name = "id") int id) {
        if (userDonation.getMoney() == 0) {
            return "redirect:/user/";
        }

        donationService.processDonate(id, userDonation);

        return "redirect:/user/";
    }
}
