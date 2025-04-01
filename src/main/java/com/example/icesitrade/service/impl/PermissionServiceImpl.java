package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.Permission;
import com.example.icesitrade.repository.PermissionRepository;
import com.example.icesitrade.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}
