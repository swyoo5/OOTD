package org.pgm.ootdproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.entity.User;
import org.pgm.ootdproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// 마이페이지에서 사용자 수정(update), 조회(show), 탈퇴(delete)
@Controller
//@RequestMapping("/my")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    // 테스트
//    @GetMapping("/test")
//    public String testPage() {
//        log.info("aaa");
//        return "/mypage/test";
//    }

    // 마이페이지
    @GetMapping("/mypage")
    public String showMyPage(Model model) {
        Long userId = 2L;
        model.addAttribute("userId", userId);
        return "/my/mypage";
    }

    // 프로필 조회
    @GetMapping("/profile/{userId}")
    public String readUserProfile(@PathVariable Long userId, Model model) {
//        User user = userService.readUser(userId).orElse(null);
//        model.addAttribute("user", user);
//        return "/my/profile";
        Optional<User> user = userService.readUser(userId);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "/my/profile";
    }

    // 프로필 수정 폼
    @GetMapping("/profile/{userId}/updateMyProfile")
    public String updateProfileForm(@PathVariable Long userId, Model model) {
        Optional<User> user = userService.readUser(userId);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "/my/updateMyProfile";
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

    @GetMapping("/delete")
    public String deleteUser(Long userId) {
        log.info("delete");
        userService.deleteUser(userId);
        return "redirect:/profile";
    }
}
