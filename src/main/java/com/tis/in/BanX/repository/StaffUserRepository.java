package com.tis.in.BanX.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tis.in.BanX.domain.StaffUser;

@Repository
public interface StaffUserRepository extends JpaRepository<StaffUser, Long> {

	//Optional<StaffUser> (StaffUserId(long id);

	
	Optional<StaffUser> findByStaffId(long id);
	
	Optional<StaffUser> findByStaffFirstName(String name);
}