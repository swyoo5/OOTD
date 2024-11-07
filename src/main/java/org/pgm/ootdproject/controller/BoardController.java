package org.pgm.ootdproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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

//     내가 쓴 게시물 하나 조회
    @GetMapping("/myBoard/{userId}/{boardId}")
    public String readOneBoard(@PathVariable("boardId") Long boardId,
                               Model model) {
        boardService.increaseVisitCount(boardId);
        Optional<BoardDTO> board = boardService.readBoard(boardId);
        if (board.isPresent()) {
            model.addAttribute("board", board.get());
        }
        return "/my/myBoard";
    }

    // 내가 쓴 게시물 전체 조회
    @GetMapping("/myBoardList/{userId}")
    public String readAllBoards(Model model,
                                @PathVariable("userId") Long userId) {
        List<BoardDTO> boards = boardService.readBoardsByUserId(userId);
        model.addAttribute("boards", boards);
        return "/my/myBoardList";
    }

    // 내 게시물 삭제
    @PostMapping("/delete/{userId}/{boardId}")
    public String deleteBoard(@PathVariable("userId") Long userId,
                              @PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/myBoardList/" + userId;
    }

    // 인기 게시물 10개 가져옴
    @GetMapping("/popularBoards")
    public String getPopularBoards(Model model) {
        List<BoardDTO> popularBoards = boardService.getPopularBoards();
        model.addAttribute("popularBoards", popularBoards);
        return "/layout/basic";
    }
}
