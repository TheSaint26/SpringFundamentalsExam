package bg.softuni.exam.model.entity;

import bg.softuni.exam.model.enums.ConditionNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class ConditionEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ConditionNameEnum conditionName;
    @Column(name = "description", nullable = false)
    private String description;

    public ConditionEntity() {
    }

    public ConditionNameEnum getConditionName() {
        return conditionName;
    }

    public ConditionEntity setConditionName(ConditionNameEnum conditionName) {
        this.conditionName = conditionName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ConditionEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
