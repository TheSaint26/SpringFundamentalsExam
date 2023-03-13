package bg.softuni.exam.model.dto;

import bg.softuni.exam.model.enums.ConditionNameEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferAddDTO {
    @NotBlank
    @Size(min = 2, max = 50, message = "Description must be between 2 and 50 characters!")
    private String description;
    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;
    @NotNull(message = "You must select a condition!")
    private ConditionNameEnum condition;

    public OfferAddDTO() {
    }

    public String getDescription() {
        return description;
    }

    public OfferAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public OfferAddDTO setCondition(ConditionNameEnum condition) {
        this.condition = condition;
        return this;
    }
}
