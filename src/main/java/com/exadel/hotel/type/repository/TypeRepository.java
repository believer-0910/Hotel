package com.exadel.hotel.type.repository;

import com.exadel.hotel.type.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByType(String type);
}
