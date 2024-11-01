package org.pgm.ootdproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.entity.User;
import org.pgm.ootdproject.service.UserService;
import org.pgm.ootdproject.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// 마이페이지에서 사용자 수정(update), 조회(show), 탈퇴(delete)
@Controller
//@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;
//    private final UserServiceImpl userServiceImpl;

//    public UserController(UserService userService, UserServiceImpl userServiceImpl) {
//        this.userService = userService;
//        this.userServiceImpl = userServiceImpl;
//    }

//    @GetMapping("/test")
//    public String testPage() {
//        log.info("aaa");
//        return "/mypage/test";
//    }

    @GetMapping("/profile")
    public String getProfilePage(Model model,
                                 @RequestParam Long userId) {
        User user = userService.getUserById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "/mypage/profile"; // 뷰 이름을 반환
    }

    // 프로필 조회
    @GetMapping("/profile/{userId}")
    public String showUserProfile(@PathVariable Long userId, Model model) {
        Optional<User> user = userService.getUserById(userId);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "/profile";
    }

    // 프로필 수정 폼
    @GetMapping("/profile/{userId}/updateMyProfile")
    public String updateProfileForm(@PathVariable Long userId, Model model) {
        Optional<User> user = userService.getUserById(userId);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "/updateMyProfile";
    }

    // 프로필 수정 처리
    @PostMapping("/profile/{userId}/updateMyProfile")
    public String updateProfile(@PathVariable Long userId,
                                @ModelAttribute User updatedUser,
                                Model model) {
        User user = userService.updateUser(userId, updatedUser);
        model.addAttribute("user", user);
        return "redirect:/profile/" + userId;
    }
}
