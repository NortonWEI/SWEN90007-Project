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
//        Assert.assertNot(user.getId(), "id is null");
//        Assert.isTrue(!dirtyUsers.contains(user), "object is dirty");
//        Assert.isTrue(!deletedUsers.contains(user), "object is deleted");
//        Assert.isTrue(!newUsers.contains(user), "object is new");
//        newUsers.add(user);
    }

    public void registerDirty(User user) {
//        Assert.notNull(user.getId(), "id is null");
//
//        Assert.isTrue(!deletedUsers.contains(user), "object is deleted");
//        if (!dirtyUsers.contains(user) && !newUsers.contains(user)) {
//            dirtyUsers.add(user);
//        }
    }

    public void registerDeleted(User obj) {
//        Assert.notNull(obj.getId(), "id is null");
//        if (newUsers.remove(obj)) return;
//        dirtyUsers.remove(obj);
//        if (!deletedUsers.contains(obj)) {
//            deletedUsers.add(obj);
//        }
    }

    public void registerClean(User user) {
//        Assert.notNull(user.getId(), "id is null");
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
