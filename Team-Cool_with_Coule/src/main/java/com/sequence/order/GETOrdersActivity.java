package main.java.com.sequence.order;

import main.java.com.obj.dao.CustomerDao;

import javax.inject.Inject;

public class GETOrdersActivity {
    private final CustomerDao dao;

    @Inject
    public GETOrdersActivity(CustomerDao dao) {
        this.dao = dao;
    }
}
