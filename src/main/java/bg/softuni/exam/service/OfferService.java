package bg.softuni.exam.service;

import bg.softuni.exam.model.dto.OfferAddDTO;
import bg.softuni.exam.model.dto.OfferViewDTO;
import bg.softuni.exam.model.entity.ConditionEntity;
import bg.softuni.exam.model.entity.OfferEntity;
import bg.softuni.exam.model.entity.UserEntity;
import bg.softuni.exam.model.session.LoggedInUser;
import bg.softuni.exam.repository.ConditionRepository;
import bg.softuni.exam.repository.OfferRepository;
import bg.softuni.exam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final LoggedInUser loggedInUser;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, ConditionRepository conditionRepository, UserRepository userRepository, LoggedInUser loggedInUser) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.loggedInUser = loggedInUser;
    }

    public void addOffer(OfferAddDTO offerAddDTO) {
        ConditionEntity condition = conditionRepository.findByConditionName(offerAddDTO.getCondition()).get();
        UserEntity user = userRepository.findById(loggedInUser.getId()).get();
        OfferEntity newOffer = modelMapper.map(offerAddDTO, OfferEntity.class);
        newOffer.setCondition(condition);
        newOffer.setSeller(user);
        user.addNewOffer(newOffer);
        offerRepository.save(newOffer);
        userRepository.save(user);
    }

    public List<OfferViewDTO> getCurrentUserOffers() {
        UserEntity user = userRepository.findById(loggedInUser.getId()).get();
        Set<OfferEntity> offers = user.getOffers();

        return offers.stream()
                .map(this::map).toList();
    }

    public List<OfferViewDTO> getCurrentUserBoughtOffers() {
        UserEntity user = userRepository.findById(loggedInUser.getId()).get();
        Set<OfferEntity> offers = user.getBoughtOffers();
        return offers.stream()
                .map(this::map).toList();
    }

    public List<OfferViewDTO> getOtherOffers() {
        return offerRepository.findAllBySellerIdNot(loggedInUser.getId())
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


    public void removeOffer(Long id) {
        OfferEntity offer = offerRepository.findById(id).get();
        UserEntity user = userRepository.findById(loggedInUser.getId()).get();
        user.removeOffer(offer);
        userRepository.save(user);
        offerRepository.delete(offer);
    }

    private OfferViewDTO map(OfferEntity offer) {
        return new OfferViewDTO()
                .setId(offer.getId())
                .setSellerName(userRepository.findById(loggedInUser.getId()).get().getUsername())
                .setCondition(conditionToString(offer.getCondition()))
                .setDescription(offer.getDescription())
                .setPrice(offer.getPrice());
    }

    private String conditionToString(ConditionEntity condition) {
        String condStr = condition.getConditionName().name();
        return condStr.substring(0, 1) + condStr.substring(1).toLowerCase();
    }
}
