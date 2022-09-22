package ru.kata.CRUD_spring_boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.kata.CRUD_spring_boot.model.User;
import ru.kata.CRUD_spring_boot.service.UserService;

import java.util.List;

@Component
public class IniUsersData {
    private static final Logger log = LoggerFactory.getLogger(IniUsersData.class);

//    private UserService userService;

    public IniUsersData(UserService userService) {
        // добавление пользователей при первом запуске, если еще не созданы
        log.debug(":: <-");
        List<User> users = userService.listAll();
        if (!users.iterator().hasNext()) {
            for (int i = 0; i < 5; i++) {
                User user = userService.create(new User("firstName" + i, "secondName" + i, i));
                log.debug("added:" + user);
            }
        }
    }
}
