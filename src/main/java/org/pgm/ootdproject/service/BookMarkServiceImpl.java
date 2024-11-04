package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.pgm.ootdproject.entity.BookMark;
import org.pgm.ootdproject.entity.BookMarkId;
import org.pgm.ootdproject.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    public Optional<BookMark> readBookMark(BookMarkId bookMarkId) {
        Optional<BookMark> bookmark = bookmarkRepository.findById(bookMarkId);
        bookmark.ifPresent(b -> {
            Hibernate.initialize(b.getBoard());
            Hibernate.initialize(b.getUser());
        });
        return bookmarkRepository.findById(bookMarkId);
    }

    @Override
    public List<BookMark> readUserBookmarks(Long userId) {
        return bookmarkRepository.findByUserUserId(userId);
    }

    @Override
    public void deleteBookMark(BookMarkId bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }
}
