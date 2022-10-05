package backend.service.impl;

import backend.model.dto.OrderItemDto;
import backend.model.OrderItem;
import backend.repository.OrderItemRepository;
import backend.service.OrderItemService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ComponentScan(basePackages = {"backend"})
@EnableJpaRepositories(basePackages = { "backend.repository" })
@EntityScan(basePackages = { "backend.model" })
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItemDto convertToOrderItemDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        if (orderItem.getItem()!=null)
            orderItemDto.setItemName(orderItem.getItem().getName());
        if (orderItem.getItem()!=null && orderItem.getItem().getMenu() != null)
            orderItemDto.setMenu(orderItem.getItem().getMenu().getName());
        if (orderItem.getItem()!=null && orderItem.getItem().getMenu() != null
                && orderItem.getItem().getMenu().getRestaurant() != null)
            orderItemDto.setRestaurant(orderItem.getItem().getMenu().getRestaurant().getName());
        if (orderItem.getItem()!=null && orderItem.getItem().getMenu() != null)
            orderItemDto.setMenuType(orderItem.getItem().getMenu().getMenuType().getMenuType());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
    @Override
    public OrderItem findById(Integer id){
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);
        return optionalOrderItem.orElse(null);
    }
}
