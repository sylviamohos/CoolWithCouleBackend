package main.java.com.sequence.customer;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class POSTCustomerActivity {
    private final CustomerDao dao;

    @Inject
    public POSTCustomerActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
