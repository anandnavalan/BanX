package com.tis.in.BanX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.AdminUser;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser , Long > {

	Optional<AdminUser> findByAdminId(long id);

	Optional<AdminUser> findByAdminName(String name);
}
