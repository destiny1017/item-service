package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itemA = new Item("itemA", 10000, 10);

        //when
        itemRepository.save(itemA);

        //then
        Item savedItem = itemRepository.findById(itemA.getId());
        assertThat(itemA).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 10000, 10);

        //when
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        List<Item> findItems = itemRepository.findAll();

        //then
        assertThat(findItems.size()).isEqualTo(2);
        assertThat(findItems).contains(itemA, itemB);
    }

    @Test
    void updateItem() {
        //given
        Item itemA = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(itemA);
        savedItem.setItemName("itemB");
        savedItem.setPrice(20000);
        savedItem.setQuantity(20);

        itemRepository.update(savedItem.getId(), savedItem);
        Item updatedItem = itemRepository.findById(itemA.getId());

        //then
        assertThat(savedItem.getItemName()).isEqualTo("itemB");
        assertThat(savedItem.getPrice()).isEqualTo(20000);
        assertThat(savedItem.getQuantity()).isEqualTo(20);
    }
}