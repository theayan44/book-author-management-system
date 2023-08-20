package com.example.mockspring2.repository;

import com.example.mockspring2.model.Publish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishRepository extends JpaRepository<Publish, Integer> {
    public List<Publish> findByYear(String year);

    @Query(value = "select * from publish where year >= :fromYear AND year <= :toYear")
    public List<Publish> findBookInRange(String fromYear, String toYear);
}
