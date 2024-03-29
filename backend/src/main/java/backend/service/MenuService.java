package backend.service;

import backend.model.dto.ItemDto;
import backend.model.dto.MenuDto;
import backend.model.Menu;

import java.util.List;

public interface MenuService {
    Menu findByIdd(Integer id);
    Menu findByIdAll(Integer id);

    List<ItemDto> findAllItemsOfAMenu(Integer id);

    List<ItemDto> findAllValidItemsOfAMenu(Integer id);

    MenuDto findById(Integer id);

    MenuDto findByIdForManager(Integer id);

    List<MenuDto> findAll();

    List<MenuDto> findAllForAdmin();

    void deleteById(Integer id);

    List<MenuDto> deleteAll();

    void validateMenu(Menu menu);

    MenuDto save(MenuDto menuDto) throws Exception;

    MenuDto saveFromRepository(Menu menu);

    MenuDto convertMenuToDto(Menu menu);
}
