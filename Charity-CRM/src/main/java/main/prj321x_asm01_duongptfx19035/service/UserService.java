package main.prj321x_asm01_duongptfx19035.service;

import lombok.RequiredArgsConstructor;
import main.prj321x_asm01_duongptfx19035.dto.UserDTO;
import main.prj321x_asm01_duongptfx19035.model.User;
import main.prj321x_asm01_duongptfx19035.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public List<User> findUserByKeyword(String keyword) {
        return userRepository.findUsersByKeyword(keyword);
    }

    public UserDTO findById(int id) {
        User user = userRepository.findUserById(id);

        return UserDTO.builder()
                .fullname(user.getFullname())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .username(user.getUsername())
                .note(user.getNote())
                .id(id)
                .build();
    }

    public void addUser(UserDTO userDTO) {

        var user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .fullname(userDTO.getFullname())
                .username(userDTO.getUsername())
                .phone(userDTO.getPhone())
                .address(userDTO.getAddress())
                .note(userDTO.getNote())
                .created(String.valueOf(new Date(System.currentTimeMillis())))
                .status("Active")
                .build();

        if (userDTO.getRole().equals("admin")) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        userRepository.save(user);
    }

    public void updateUser(UserDTO userDTO) {
        User user = findUserById(userDTO.getId());

        user.setEmail(userDTO.getEmail());
        user.setFullname(userDTO.getFullname());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());
        user.setNote(userDTO.getNote());
        user.setAddress(userDTO.getAddress());

        userRepository.save(user);
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void lockUser(int id) {
        User user = findUserById(id);

        user.setStatus("Locked");

        userRepository.save(user);
    }

    public void unlockUser(int id) {
        User user = findUserById(id);

        user.setStatus("Active");

        userRepository.save(user);
    }

    public Optional<User> findUSerByName(String name) {
        return userRepository.findUserByName(name);
    }
}
