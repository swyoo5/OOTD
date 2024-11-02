package org.pgm.ootdproject.repository;

import org.pgm.ootdproject.entity.BookMark;
import org.pgm.ootdproject.entity.BookMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<BookMark, Long> {
    Optional<BookMark> findById(BookMarkId bookmarkId);
    void deleteById(BookMarkId bookmarkId);
}
