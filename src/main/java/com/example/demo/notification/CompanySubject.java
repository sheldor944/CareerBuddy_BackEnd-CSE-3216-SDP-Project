package com.example.demo.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class CompanySubject implements NotificationSubject {
    private List<NotificationObserver> observers = new ArrayList<>();

    @Override
    public void addSubscriber(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeSubscriber(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }
}
