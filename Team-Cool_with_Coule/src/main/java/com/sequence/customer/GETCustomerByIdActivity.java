package main.java.com.sequence.customer;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class GETCustomerByIdActivity {
    private final CustomerDao dao;

    @Inject
    public GETCustomerByIdActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
