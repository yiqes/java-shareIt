package ru.practicum.shareit.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Request repository.
 */
public interface    RequestRepository extends JpaRepository<Request, Long> {
    /**
     * Find all by requestor id list.
     *
     * @param requestorId the requestor id
     * @param sort        the sort
     * @return the list
     */
    List<Request> findAllByRequestorId(Long requestorId, Sort sort);

    /**
     * Find all by requestor id not page.
     *
     * @param userId   the user id
     * @param pageable the pageable
     * @return the page
     */
    Page<Request> findAllByRequestorIdNot(Long userId, Pageable pageable);

    /**
     * Find all by requestor id not order by created desc list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Request> findAllByRequestorIdNotOrderByCreatedDesc(Long userId);
}
