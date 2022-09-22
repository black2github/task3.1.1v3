package ru.kata.CRUD_spring_boot.dao;

import ru.kata.CRUD_spring_boot.model.Role;

public interface RoleDao {
    Role save(Role user);
    Role find(Long id);
    void delete(Role id);
    Role update(long id, Role user);
}
