package org.pgm.ootdproject.repository;

import org.pgm.ootdproject.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findById(Long id);
    List<Reply> findByUserUserId(Long userId);
}
