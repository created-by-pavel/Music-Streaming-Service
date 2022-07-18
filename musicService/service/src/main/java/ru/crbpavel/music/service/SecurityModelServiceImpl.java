package ru.crbpavel.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.crbpavel.music.model.SecurityModel;
import ru.crbpavel.music.repository.SecurityModelRepository;
import ru.crbpavel.music.tool.MusicServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SecurityModelServiceImpl implements SecurityModelService, UserDetailsService {
    @Autowired
    SecurityModelRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SecurityModel securityModel = repository.findByName(name);
        if (securityModel == null) throw new MusicServiceException("cant find user");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(securityModel.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(securityModel.getName(), securityModel.getPassword(), authorities);
    }

    public SecurityModel save(SecurityModel securityModel) {
        securityModel.setPassword(passwordEncoder.encode(securityModel.getPassword()));
        return repository.save(securityModel);
    }

    public List<SecurityModel> getAll() {
        return repository.findAll();
    }

    public SecurityModel getById(long id) {
        return repository.findById(id).get();
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public SecurityModel findByName(String name) {
        return repository.findByName(name);
    }

    public void updateUser(SecurityModel securityModel) {
        SecurityModel existingSecurityModel = repository.findById(securityModel.getId()).orElse(null);
        if (existingSecurityModel == null) throw new MusicServiceException("cant find user");
        existingSecurityModel.setName(securityModel.getName());
        existingSecurityModel.setPassword(securityModel.getPassword());
        existingSecurityModel.setRole(securityModel.getRole());
        repository.save(existingSecurityModel);
    }
}
