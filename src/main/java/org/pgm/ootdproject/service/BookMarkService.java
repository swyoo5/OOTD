package org.pgm.ootdproject.service;

import org.pgm.ootdproject.entity.BookMark;
import org.pgm.ootdproject.entity.BookMarkId;

import java.util.List;
import java.util.Optional;

public interface BookMarkService {
    Optional<BookMark> readBookMark(BookMarkId bookmarkId);
    List<BookMark> readUserBookmarks(Long userId);
    void deleteBookMark(BookMarkId bookmarkId);
}
