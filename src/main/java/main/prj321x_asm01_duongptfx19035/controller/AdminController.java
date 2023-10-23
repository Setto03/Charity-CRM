package main.prj321x_asm01_duongptfx19035.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.prj321x_asm01_duongptfx19035.dto.UserDTO;
import main.prj321x_asm01_duongptfx19035.model.Donation;
import main.prj321x_asm01_duongptfx19035.model.User;
import main.prj321x_asm01_duongptfx19035.model.UserDonation;
import main.prj321x_asm01_duongptfx19035.service.DonationService;
import main.prj321x_asm01_duongptfx19035.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final DonationService donationService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUserList(Model model) {
        List<User> users = userService.getUserList();

        model.addAttribute("users", users);

        return "userList";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        model.addAttribute("user", new UserDTO());

        return "newUser";
    }

    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String processNewUser(@ModelAttribute(name = "user") @Valid UserDTO userDTO,
                                 BindingResult result,
                                 Model model) {
        if (result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
            model.addAttribute("error", "Email hoặc mật khẩu không hợp lệ");

            return "newUser";
        }

        userService.addUser(userDTO);

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    public String searchUsers(@RequestParam(name = "keyword") String keyword,
                              Model model) {
        List<User> users = userService.findUserByKeyword(keyword);

        model.addAttribute("users", users);

        return "userList";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public String getInfo(@RequestParam(name = "id") int id,
                          Model model) {
        model.addAttribute("user", userService.findUserById(id));

        return "userInfo";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public String userInfo(@RequestParam("id") int id,
                           Model model) {
        UserDTO user = userService.findById(id);

        model.addAttribute("user", user);

        return "updateUser";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUserInfo(@ModelAttribute(name = "user") @Valid UserDTO userDTO,
                                 BindingResult result,
                                 Model model) {
        if (result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
            model.addAttribute("error", "Email hoặc mật khẩu không hợp lệ");

            return "updateUser";
        }

        userService.updateUser(userDTO);

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(name = "id") int id) {
        userService.deleteUser(id);

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/lock", method = RequestMethod.GET)
    public String lockUser(@RequestParam(name = "id") int id) {
        userService.lockUser(id);

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/user/unlock", method = RequestMethod.GET)
    public String unlockUser(@RequestParam(name = "id") int id) {
        userService.unlockUser(id);

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/donations", method = RequestMethod.GET)
    public String getDonationsList(Model model) {
        List<Donation> donations = donationService.getDonationList();

        model.addAttribute("donations", donations);

        return "donationList";
    }

    @RequestMapping(value = "/donation/new", method = RequestMethod.GET)
    public String addNewDonation(Model model) {
        model.addAttribute("donation", new Donation());

        return "newDonation";
    }

    @RequestMapping(value = "/donation/new", method = RequestMethod.POST)
    public String processNewDonation(@ModelAttribute("donation") @Valid Donation donation,
                                     BindingResult result,
                                     Model model) {
        if (result.hasFieldErrors("name") || result.hasFieldErrors("organization")
                || result.hasFieldErrors("phone")) {
            model.addAttribute("error", "Phải có tên quỹ, tên tổ chức gây quỹ và số điện thoại.");

            return "newDonation";
        }

        donationService.addNewDonation(donation);

        return "redirect:/admin/donations";
    }

    @RequestMapping(value = "/donation/search", method = RequestMethod.GET)
    public String searchDonations(@RequestParam(name = "keyword") String keyword,
                                  Model model) {
        List<Donation> donations = donationService.searchDonations(keyword);

        model.addAttribute("donations", donations);

        return "donationList";
    }

    @RequestMapping(value = "/donation/info", method = RequestMethod.GET)
    public String donationInfo(@RequestParam(name = "id") int id,
                               Model model) {
        Donation donation = donationService.findDonationById(id);
        List<UserDonation> userDonations = donationService.getDonationHistory(id);

        model.addAttribute("donates", userDonations);
        model.addAttribute("donation", donation);

        return "donationInfo";
    }

    @RequestMapping(value = "/donation/update", method = RequestMethod.GET)
    public String updateDonation(@RequestParam(name = "id") int id,
                                 Model model) {
        Donation donation = donationService.findDonationById(id);

        model.addAttribute("donation", donation);

        return "updateDonation";
    }

    @RequestMapping(value = "/donation/update", method = RequestMethod.POST)
    public String updateDonation(@ModelAttribute(name = "donation") @Valid Donation donation,
                                 BindingResult result,
                                 Model model) {
        if (result.hasFieldErrors("name") || result.hasFieldErrors("organization")
                || result.hasFieldErrors("phone")) {
            model.addAttribute("error", "Phải có tên quỹ, tên tổ chức gây quỹ và số điện thoại.");

            return "newDonation";
        }

        donationService.updateDonation(donation);

        return "redirect:/admin/donations";
    }

    @RequestMapping(value = "/donation/delete", method = RequestMethod.GET)
    public String deleteDonation(@RequestParam(name = "id") int id) {
        donationService.deleteDonation(id);

        return "redirect:/admin/donations";
    }

    @RequestMapping(value = "/donation/end", method = RequestMethod.GET)
    public String endDonation(@RequestParam(name = "id") int id) {
        donationService.endDonation(id);

        return "redirect:/admin/donations";
    }
}
