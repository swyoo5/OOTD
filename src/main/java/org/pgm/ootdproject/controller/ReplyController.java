package org.pgm.ootdproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.DTO.ReplyDTO;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.service.BoardService;
import org.pgm.ootdproject.service.BoardServiceImpl;
import org.pgm.ootdproject.service.ReplyService;
import org.pgm.ootdproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/myreply")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {
    private final ReplyService replyService;
    private final BoardService boardService;
    private final UserService userService;

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

    // 특정 사용자의 댓글
    @GetMapping("/myReplyList/{userId}")
    public String readUserReplies(@PathVariable Long userId, Model model) {
        List<ReplyDTO> replies = replyService.readRepliesByUserId(userId);
        model.addAttribute("replies", replies);
        return "/my/myReplyList";
    }

    // 특정 게시물의 댓글 조회
    @GetMapping("/board/{boardId}")
    public List<ReplyDTO> getRepliesByBoard(@PathVariable Long boardId, Model model) {
        return replyService.readRepliesByBoardId(boardId);
    }

    // 댓글 생성
    @PostMapping("/create")
    public ResponseEntity<ReplyDTO> createReply(@RequestParam Long boardId,
                                                @RequestParam Long userId,
                                                @RequestParam String content) {
        Board board = (boardService.readBoard(boardId).get()).orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }
}
