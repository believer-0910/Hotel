package com.exadel.hotel.hotel.repository;

import com.exadel.hotel.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByName(String name);
}
