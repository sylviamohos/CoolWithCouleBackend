package main.java.com.sequence.customer;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class GETCustomerByEmailActivity {
    private final CustomerDao dao;

    @Inject
    public GETCustomerByEmailActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
