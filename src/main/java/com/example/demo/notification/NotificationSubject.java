package com.example.demo.notification;

public interface NotificationSubject {
    void addSubscriber(NotificationObserver observer);
    void removeSubscriber(NotificationObserver observer);
    void notifySubscribers(Notification notification);
}