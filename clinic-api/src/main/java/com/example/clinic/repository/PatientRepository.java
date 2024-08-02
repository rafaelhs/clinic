package com.example.clinic.repository;

import com.example.clinic.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findOneByDocument(String document);

    Optional<Patient> findOneByEmail(String email);


    @Query("select p from Patient p where (" +
            "lower(p.name) like lower(concat('%', :search, '%')) or " +
            "lower(p.document) like lower(concat('%', :search, '%')) or " +
            "lower(p.email) like lower(concat('%', :search, '%'))" +
            ")")
    Page<Patient> search(@Param("search") String search, Pageable pageable);
}
