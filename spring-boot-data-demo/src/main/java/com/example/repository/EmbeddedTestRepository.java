package com.example.repository;

import com.example.entity.EmbeddedId;
import com.example.entity.EmbeddedTest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbeddedTestRepository extends JpaRepository<EmbeddedTest, EmbeddedId> {

    @Modifying
    @Transactional
    @Query(value = "delete from embedded_test where version_id = ?1", nativeQuery = true)
    int deleteByVersionId(String versionId);
}
