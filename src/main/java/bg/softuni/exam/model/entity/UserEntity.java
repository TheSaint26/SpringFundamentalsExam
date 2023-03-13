package bg.softuni.exam.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;
    @OneToMany
    private Set<OfferEntity> offers;
    @OneToMany
    private Set<OfferEntity> boughtOffers;

    public UserEntity() {
        this.offers = new HashSet<>();
        this.boughtOffers = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<OfferEntity> getOffers() {
        return offers;
    }

    public UserEntity setOffers(Set<OfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public Set<OfferEntity> getBoughtOffers() {
        return boughtOffers;
    }

    public UserEntity setBoughtOffers(Set<OfferEntity> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }

    public void addNewOffer(OfferEntity offer) {
        this.offers.add(offer);
    }

    public void buyOffer(OfferEntity offer) {
        this.boughtOffers.add(offer);
    }

    public void removeOffer(OfferEntity offer) {
        this.offers.remove(offer);
    }
}
