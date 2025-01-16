package ru.practicum.shareit.item;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface Item repository.
 */
public interface ItemRepository  extends JpaRepository<Item, Long> {
    /**
     * Find by owner id list.
     *
     * @param ownerId the owner id
     * @return the list
     */
    List<Item> findByOwnerId(Long ownerId);

    /**
     * Gets items by search query.
     *
     * @param text the text
     * @return the items by search query
     */
    @Query(" select i from Item i " +
            "where lower(i.name) like lower(concat('%', :search, '%')) " +
            " or lower(i.description) like lower(concat('%', :search, '%')) " +
            " and i.available = true")
    List<Item> getItemsBySearchQuery(@Param("search") String text);

    /**
     * Find all by request id list.
     *
     * @param requestId the request id
     * @param sort      the sort
     * @return the list
     */
    List<Item> findAllByRequestId(Long requestId, Sort sort);
}
