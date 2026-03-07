package com.campustab.source.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campustab.source.domain.Source;

public interface SourceRepository extends JpaRepository<Source, Long> {
}
