package main.java.com.sequence.customer;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class PUTCustomerActivity {
    private final CustomerDao dao;

    @Inject
    public PUTCustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
