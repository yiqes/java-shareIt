package ru.practicum.shareit.item;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Comment repository.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Find all by item id list.
     *
     * @param itemId the item id
     * @param sort   the sort
     * @return the list
     */
    List<Comment> findAllByItemId(Long itemId, Sort sort);
}
