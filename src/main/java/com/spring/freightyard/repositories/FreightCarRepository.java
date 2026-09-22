package com.spring.freightyard.repositories;

import com.spring.freightyard.models.FreightCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightCarRepository extends JpaRepository<FreightCar,Long> {

}
