package com.tis.in.BanX.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

	Optional<UserType> findByUserTypeName(String name);
	
	Optional<UserType> findByUserTypeId(long id);

}
