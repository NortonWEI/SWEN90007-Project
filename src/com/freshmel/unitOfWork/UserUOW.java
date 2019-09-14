package com.freshmel.unitOfWork;

import com.freshmel.model.User;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.List;

public class UserUOW {
    private static ThreadLocal current = new ThreadLocal();

    private List<User> newUsers = new ArrayList<User>();
    private List<User> dirtyUsers = new ArrayList<User>();
    private List<User> deletedUsers = new ArrayList<User>();

    public static void newCurrent() {
        setCurrent(new UserUOW());
    }

    public static void setCurrent(UserUOW userUOW) {
        current.set(userUOW);
    }

    public static UserUOW getCurrent() {
        return (UserUOW) current.get();
    }

    public void registerNew(User user) {
        Assert.assertNotNull ("id is null", user.getId());
        Assert.assertFalse("object is dirty", dirtyUsers.contains(user));
        Assert.assertFalse("object is deleted", deletedUsers.contains(user));
        Assert.assertFalse("object is new", newUsers.contains(user));
        newUsers.add(user);
    }

    public void registerDirty(User user) {
        Assert.assertNotNull("id is null", user.getId());

        Assert.assertFalse("object is deleted", deletedUsers.contains(user));
        if (!dirtyUsers.contains(user) && !newUsers.contains(user)) {
            dirtyUsers.add(user);
        }
    }

    public void registerDeleted(User user) {
        Assert.assertNotNull("id is null", user.getId());
        if (newUsers.remove(user)) return;
        dirtyUsers.remove(user);
        if (!deletedUsers.contains(user)) {
            deletedUsers.add(user);
        }
    }

    public void registerClean(User user) {
        Assert.assertNotNull("id is null", user.getId());
    }

    public void commit() {
        for (User obj : newUsers) {
//            DataMapper.getMapper(obj.getClass()).insert(obj);
        }
        for (User obj : dirtyUsers) {
//            DataMapper.getMapper(obj.getClass()).update(obj);
        }
        for (User obj : deletedUsers) {
//            DataMapper.getMapper(obj.getClass()).delete(obj);
        }
    }
}
