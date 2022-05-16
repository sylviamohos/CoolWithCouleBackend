package main.java.com.sequence.order;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class POSTCheckoutActivity {
    private final CustomerDao dao;

    @Inject
    public POSTCheckoutActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
