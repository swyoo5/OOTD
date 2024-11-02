package org.pgm.ootdproject.service;

import java.util.List;
import java.util.Optional;

public interface BookMarkService {
    Optional<BookMark> readBookMark(BookMarkId bookmarkId);
    List<BookMark> readAllBookmarks();
    void deleteBookMark(BookMarkId bookmarkId);
}
