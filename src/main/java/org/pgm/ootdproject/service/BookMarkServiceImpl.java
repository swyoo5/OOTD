package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.pgm.ootdproject.DTO.BookmarkDTO;
import org.pgm.ootdproject.entity.BookMark;
import org.pgm.ootdproject.entity.BookMarkId;
import org.pgm.ootdproject.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@Transactional
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    public Optional<BookmarkDTO> readBookMark(BookMarkId bookMarkId) {
        Optional<BookMark> bookmark = bookmarkRepository.findById(bookMarkId);
        bookmark.ifPresent(b -> {
            Hibernate.initialize(b.getBoard());
            Hibernate.initialize(b.getUser());
        });
        return bookmark.map(this::convertEntityToDTO);
    }

    @Override
    public List<BookmarkDTO> readUserBookmarks(Long userId) {
        return bookmarkRepository.findByUserUserId(userId).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBookMark(BookMarkId bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }

    private BookmarkDTO convertEntityToDTO(BookMark bookmark) {
        return BookmarkDTO.builder()
                .boardId(bookmark.getBoard().getBoardId())
                .userId(bookmark.getUser().getUserId())
                .bookmarkedDate(bookmark.getBookmarkedDate())
                .userNickname(bookmark.getUser().getNickname())
                .title(bookmark.getBoard().getTitle())
                .content(bookmark.getBoard().getContent())
                .purchaseLink(bookmark.getBoard().getPurchaseLink())
                .regDate(bookmark.getBoard().getRegDate())
                .build();
    }
}
