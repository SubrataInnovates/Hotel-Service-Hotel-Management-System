package com.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>
{

}
