package org.pgm.ootdproject.service;

import org.pgm.ootdproject.DTO.BookmarkDTO;
import org.pgm.ootdproject.entity.BookMarkId;

import java.util.List;
import java.util.Optional;

public interface BookMarkService {
    Optional<BookmarkDTO> readBookMark(BookMarkId bookmarkId);
    List<BookmarkDTO> readUserBookmarks(Long userId);
    void deleteBookMark(BookMarkId bookmarkId);
}
