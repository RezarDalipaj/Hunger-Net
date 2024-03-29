package backend.service;

import backend.model.dto.ItemDto;
import backend.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto findById(Integer id);

    Item findByIdd(Integer id);

    Item findByIdValid(Integer id);

    List<ItemDto> findAllValid();

    List<ItemDto> findAllForClients();

    void deleteById(Integer id) throws Exception;

    List<ItemDto> deleteAll() throws Exception;

    ItemDto save(ItemDto itemDto) throws Exception;
}
