package org.pgm.ootdproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@RequestMapping("/my")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/test")
//    public String testPage() {
//        log.info("aaa");
//        return "/my/test";
//    }

    // 내가 쓴 게시물 하나 조회
//    @GetMapping("/{boardId}")
//    public String readOneBoard(@PathVariable Long boardId, Model model) {
//        Optional<Board> board = boardService.readBoard(boardId);
//        board.ifPresent(value -> model.addAttribute("board", value));
//        return "/mypage/myBoardList" + boardId;
//    }

    // 내가 쓴 게시물 전체 조회
    @GetMapping("/myBoardList")
    public String readAllBoards(Model model) {
        List<Board> boards = boardService.readAllBoards();
        model.addAttribute("boards", boards);
        return "/my/myBoardList";
    }

    // 내 게시물 삭제
    @PostMapping("/delete/{boardId}")
    public String deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/myBoardList";
    }


}
