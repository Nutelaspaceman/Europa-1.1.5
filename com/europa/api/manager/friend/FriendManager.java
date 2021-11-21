/*
 * Decompiled with CFR 0.150.
 */
package com.europa.api.manager.friend;

import com.europa.api.manager.friend.Friend;
import java.util.ArrayList;

public class FriendManager {
    public ArrayList<Friend> friends = new ArrayList();

    public ArrayList<Friend> getFriends() {
        return this.friends;
    }

    /*
     * WARNING - void declaration
     */
    public Friend getFriend(String string) {
        void name;
        return this.friends.stream().filter(arg_0 -> FriendManager.lambda$getFriend$0((String)name, arg_0)).findFirst().orElse(null);
    }

    /*
     * WARNING - void declaration
     */
    public boolean isFriend(String string) {
        for (Friend friend : this.getFriends()) {
            void name;
            if (!friend.getName().equals(name)) continue;
            return true;
        }
        return false;
    }

    /*
     * WARNING - void declaration
     */
    public void addFriend(String string) {
        void name;
        this.friends.add(new Friend((String)name));
    }

    /*
     * WARNING - void declaration
     */
    public void removeFriend(String string) {
        void name;
        if (this.getFriend((String)name) != null) {
            this.friends.remove(this.getFriend((String)name));
        }
    }

    public void clearFriends() {
        this.friends.clear();
    }

    /*
     * WARNING - void declaration
     */
    public static boolean lambda$getFriend$0(String string, Friend friend) {
        String name;
        void f;
        return f.getName().equals(name);
    }
}

