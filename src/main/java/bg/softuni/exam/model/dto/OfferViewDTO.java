package bg.softuni.exam.model.dto;


import java.math.BigDecimal;

public class OfferViewDTO {
    private Long id;
    private String sellerName;
    private String condition;
    private BigDecimal price;
    private String description;

    public OfferViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public OfferViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSellerName() {
        return sellerName;
    }

    public OfferViewDTO setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public OfferViewDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
