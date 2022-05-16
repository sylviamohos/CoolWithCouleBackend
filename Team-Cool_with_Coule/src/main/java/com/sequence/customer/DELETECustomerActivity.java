package main.java.com.sequence.customer;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class DELETECustomerActivity {
    private final CustomerDao dao;

    @Inject
    public DELETECustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
