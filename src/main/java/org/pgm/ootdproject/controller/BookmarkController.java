package org.pgm.ootdproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.ootdproject.entity.BookMark;
import org.pgm.ootdproject.entity.BookMarkId;
import org.pgm.ootdproject.service.BookMarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/bookmark")
@RequiredArgsConstructor
@Log4j2
public class BookmarkController {
    private final BookMarkService bookMarkService;

    // 북마크 게시물 하나
    @GetMapping("/bookmark/{userId}/{boardId}")
    public String readOndBookmark(@PathVariable("userId") Long userId,
                                  @PathVariable("boardId") Long boardId,
                                  Model model) {
        BookMarkId bookmarkId = new BookMarkId(userId, boardId);
        Optional<BookMark> bookmark = bookMarkService.readBookMark(bookmarkId);
        bookmark.ifPresent(value -> model.addAttribute("bookmark", value));
        return "/my/bookmark/myBookmarkList";
    }

    @GetMapping("/myBookmarkList")
    public String readAllBookmarks(Model model) {
        List<BookMark> bookmarks = bookMarkService.readAllBookmarks();
        model.addAttribute("bookmarks", bookmarks);
        return "/my/myBookmarkList";
    }

    @GetMapping("/delete/{userId}/{boardId}")
    public String deleteBookmark(@PathVariable("userId") Long userId,
                                 @PathVariable("boardId") Long boardId) {
        BookMarkId bookmarkId = new BookMarkId(userId, boardId);
        bookMarkService.deleteBookMark(bookmarkId);
        return "redirect:/myBookmarkList";
    }
}
