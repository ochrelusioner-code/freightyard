package com.spring.freightyard.repositories;

import com.spring.freightyard.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {
    Train findByManifestNumber(String manifestNumber);
}
