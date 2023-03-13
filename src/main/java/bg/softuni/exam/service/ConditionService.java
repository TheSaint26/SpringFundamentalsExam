package bg.softuni.exam.service;

import bg.softuni.exam.model.entity.ConditionEntity;
import bg.softuni.exam.model.enums.ConditionNameEnum;
import bg.softuni.exam.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void initConditions() {
        if (conditionRepository.count() > 0) {
            return;
        }

        List<ConditionEntity> conditions = Arrays.stream(ConditionNameEnum.values())
                .map(value -> {
                    ConditionEntity condition = new ConditionEntity().setConditionName(value);
                    switch (value.name()) {
                        case "EXCELLENT" -> condition.setDescription("In perfect condition");
                        case "GOOD" -> condition.setDescription("Some signs of wear and tear or minor defects");
                        case "ACCEPTABLE" ->
                                condition.setDescription("The item is fairly worn but continues to function properly");
                    }
                    return condition;
                }).toList();

        conditionRepository.saveAll(conditions);
    }
}
