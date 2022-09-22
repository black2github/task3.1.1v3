package ru.kata.CRUD_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.CRUD_spring_boot.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kata.CRUD_spring_boot.dao.UserDao;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        log.debug("create: <- " + user);
        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> list(int count) {
        log.debug("list: <- count = " + count);
        return userDao.list(count);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listAll() {
        log.debug("listAll: <-");
        List<User> list = userDao.listAll();
        log.debug("listAll: -> " + Arrays.toString(list.toArray()));
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public User find(Long id) {
        log.debug("find: <- id=" + id);
        return userDao.find(id);
    }

    @Override
    public void delete(User user) {
        log.debug("delete: <- " + user);
        if (user==null || user.getId()==null) {
            log.warn("User or id must not be null");
            return;
        }
        userDao.delete(user);
    }

    @Override
    public void delete(Long id) {
        log.debug("delete: <- id=" + id);
        User usr = find(id);
        if (usr != null) {
            delete(usr);
        } else {
            log.warn("delete: User with id=" + id + " not found");
        }
    }

    @Override
    public User update(long id, User user) {
        log.debug(String.format("update: <- id=%d, user=%s", id, user));

        if (user == null) {
            log.warn("update: User must not be null");
            return null;
        }
        User u = userDao.find(id);
        if (u == null) {
            log.warn("update: User with id=" + id + " not found");
            return null;
        }
        if (user != null) {
            u.setFirstName(user.getFirstName());
            u.setSecondName(user.getSecondName());
            u.setAge(user.getAge());
            u.setRoles(user.getRoles());
            u = userDao.save(u);
        }

        log.debug("update: -> " + u);
        return u;
    }
}
