package com.atms.repository;

import com.atms.model.DeveloperEffectiveness;
import com.atms.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Kazanovskiy.
 */
@Repository
public interface DeveloperEffectivenessRepository extends JpaRepository<DeveloperEffectiveness, Integer> {

    DeveloperEffectiveness findByKeyword(Keyword keyword);
}
