package main.java.com.sequence.product;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class DELETEProductActivity {
    private final CustomerDao dao;

    @Inject
    public DELETEProductActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
