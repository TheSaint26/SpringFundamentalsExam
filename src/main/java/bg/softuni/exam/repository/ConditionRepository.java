package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.ConditionEntity;
import bg.softuni.exam.model.enums.ConditionNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity, Long> {
    Optional<ConditionEntity> findByConditionName(ConditionNameEnum name);
}
