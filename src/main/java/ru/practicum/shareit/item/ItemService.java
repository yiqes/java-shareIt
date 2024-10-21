package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item addItem(Item item);

    Item updateItem(Long itemId, Item item);

    Item getItem(Long itemId);

    List<Item> getAllItems();
}