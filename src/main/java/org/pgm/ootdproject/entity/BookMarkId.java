package org.pgm.ootdproject.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class BookMarkId {
    private long boardId;
    private long userId;
    public BookMarkId() {}

    public BookMarkId(long boardId, long userId) {
        this.boardId = boardId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookMarkId that = (BookMarkId) o;
        return boardId == that.boardId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, userId);
    }
}
