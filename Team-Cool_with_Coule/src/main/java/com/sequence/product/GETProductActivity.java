package main.java.com.sequence.product;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class GETProductActivity {
    private final CustomerDao dao;

    @Inject
    public GETProductActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
