package backend.service;

import backend.dto.OrderDto;
import backend.dto.OrderStatusDto;
import backend.model.Order;

import java.util.List;

public interface OrderService {
    Order findByIdValid(Integer id);

    OrderDto findValidById(Integer id);

    OrderDto deleteByIdUser(Integer id) throws Exception;

    List<OrderDto> findOrdersOfAUser(Integer id);

    List<OrderDto> findOrdersOfARestaurantByStatus(String status, Integer id) throws Exception;

    List<OrderDto> findOrdersOfARestaurant(Integer id);

    OrderDto deleteByIdManager(Integer id);

    OrderDto updateStatus(OrderStatusDto orderStatusDto) throws Exception;

    Order findById(Integer id);

    Order findByIdCreated(Integer id);

    OrderDto save(OrderDto orderDto) throws Exception;
}
