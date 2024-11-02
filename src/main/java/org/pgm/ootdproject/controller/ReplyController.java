package org.pgm.ootdproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequestMapping("/myreply")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {
    private final ReplyService replyService;

//    @GetMapping("/test")
//    public String test() {
//        log.info("test");
//        return "/my/test";
//    }

    // 댓글 하나 보기
//    @GetMapping("/myReplyList/{replyId}")
//    public String readUserReply(@PathVariable Long replyId,
//                                Model model) {
//        Optional<Reply> reply = replyService.readReply(replyId);
//        reply.ifPresent(value -> model.addAttribute("reply", value));
//        return "/my/myReplyList";
//    }

    // 모든 댓글 보기
    @GetMapping("/myReplyList")
    public String readAllReply(Model model) {
        List<Reply> replies = replyService.readAllReplies();
        model.addAttribute("replies", replies);
        return "/my/myReplyList";
    }
}
