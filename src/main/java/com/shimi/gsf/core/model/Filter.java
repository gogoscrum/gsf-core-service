package com.shimi.gsf.core.model;

import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter is an interface that defines the structure for filtering data.
 * It includes pagination, sorting, and a method to convert filter orders to Spring Data Sort.
 */
public interface Filter extends Serializable {
    int getPage();

    int getPageSize();

    List<Order> getOrders();

    default Sort toSort() {
        List<Order> filterOrders = new ArrayList<>(this.getOrders());

        // If no order specified, then sort by ID DESC by default
        if (CollectionUtils.isEmpty(filterOrders)) {
            filterOrders.add(new Order("id", Direction.DESC));
        }

        List<Sort.Order> orders = filterOrders.stream().map(order ->
                        new Sort.Order(Direction.ASC.equals(order.getDirection()) ? Sort.Direction.ASC : Sort.Direction.DESC, order.getProperty()))
                .collect(Collectors.toList());

        return Sort.by(orders);
    }

    class Order implements Serializable {
        @Serial
        private static final long serialVersionUID = 7377673307084094308L;
        private String property;
        private Direction direction = Direction.ASC;

        public Order() {
        }

        public Order(String property, Direction direction) {
            this.property = property;
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }

    enum Direction {
        ASC, DESC;
    }
}
