package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();
    Item findById(Long id);
    Item save(Item item);
    Item updateItem(Item item, Long id);
}