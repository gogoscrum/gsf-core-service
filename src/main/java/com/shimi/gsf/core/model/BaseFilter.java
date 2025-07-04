package com.shimi.gsf.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseFilter is a base class for all filter classes in the application.
 * It provides pagination and sorting capabilities.
 */
@SuppressWarnings("serial")
public abstract class BaseFilter implements Filter {
    private int page = 1;
    private int pageSize = 10;
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getPage() {
        return page > 1 ? page - 1 : 0;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
