package ru.crbpavel.music.service;

import ru.crbpavel.music.model.SecurityModel;

import java.util.List;

public interface SecurityModelService {
    SecurityModel save(SecurityModel securityModel);

    SecurityModel findByName(String name);

    List<SecurityModel> getAll();

    SecurityModel getById(long id);

    void deleteById(long id);

    void updateUser(SecurityModel securityModel);
}
