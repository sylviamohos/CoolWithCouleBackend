package main.java.com.sequence.product;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class POSTProductActivity {
    private final CustomerDao dao;

    @Inject
    public POSTProductActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
