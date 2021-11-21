/*
 * Decompiled with CFR 0.150.
 */
package com.europa.client.modules.client.notifications;

import com.europa.client.modules.client.ModuleNotifications;
import com.europa.client.modules.client.notifications.Notification;
import java.util.ArrayList;

public class NotificationProcessor {
    public ArrayList<Notification> notifications = new ArrayList();

    /*
     * WARNING - void declaration
     */
    public void handleNotifications(int n) {
        for (int i = 0; i < this.getNotifications().size(); ++i) {
            void posY;
            if (this.getNotifications().get((int)i).animationUtils2.isDone() && !this.getNotifications().get((int)i).didThing) {
                this.getNotifications().get((int)i).animationUtils.reset();
                this.getNotifications().get((int)i).didThing = true;
            }
            if (this.getNotifications().get((int)i).animationUtils.isDone() && !this.getNotifications().get((int)i).isReversing && this.getNotifications().get((int)i).timer.hasReached(this.getNotifications().get((int)i).disableTime - (long)(ModuleNotifications.inOutTime.getValue().intValue() * 2))) {
                this.getNotifications().get((int)i).reverse.reset();
                this.getNotifications().get((int)i).reverse2.reset();
                this.getNotifications().get((int)i).isReversing = true;
            }
            if (this.getNotifications().get((int)i).isReversing && this.getNotifications().get((int)i).reverse.isDone() && !this.getNotifications().get((int)i).didFirstReverse) {
                this.getNotifications().get((int)i).reverse2.reset();
                this.getNotifications().get((int)i).didFirstReverse = true;
            }
            this.getNotifications().get(i).onDraw((int)posY);
            if (ModuleNotifications.addType.getValue()) {
                posY += 22;
                continue;
            }
            posY -= 22;
        }
    }

    /*
     * WARNING - void declaration
     */
    public void addNotification(String string, long l, long l2) {
        void inOutTime;
        void duration;
        void text;
        this.getNotifications().add(new Notification((String)text, (long)duration, (long)inOutTime));
    }

    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }
}

