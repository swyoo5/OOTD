package org.pgm.ootdproject.repository;

import org.pgm.ootdproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
    List<Board> findByUserUserId(Long userId);
}
