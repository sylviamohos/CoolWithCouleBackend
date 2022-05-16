package main.java.com.sequence.product;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class PUTProductActivity {
    private final CustomerDao dao;

    @Inject
    public PUTProductActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
