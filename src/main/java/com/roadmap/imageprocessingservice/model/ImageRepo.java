package com.roadmap.imageprocessingservice.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity,Integer>
{
    public ImageEntity findById(int id);

}
