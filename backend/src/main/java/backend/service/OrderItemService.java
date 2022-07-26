package backend.service;

import backend.dto.OrderItemDto;
import backend.model.OrderItem;

public interface OrderItemService {
    OrderItemDto convertToOrderItemDto(OrderItem orderItem);

    OrderItem findById(Integer id);
}
