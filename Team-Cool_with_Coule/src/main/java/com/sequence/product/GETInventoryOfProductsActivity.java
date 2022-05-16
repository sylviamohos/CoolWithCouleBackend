package main.java.com.sequence.product;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class GETInventoryOfProductsActivity {
    private final CustomerDao dao;

    @Inject
    public GETInventoryOfProductsActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
