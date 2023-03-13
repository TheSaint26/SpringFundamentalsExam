package bg.softuni.exam.service;

import bg.softuni.exam.model.dto.UserLoginDTO;
import bg.softuni.exam.model.dto.UserRegisterDTO;
import bg.softuni.exam.model.entity.OfferEntity;
import bg.softuni.exam.model.entity.UserEntity;
import bg.softuni.exam.model.session.LoggedInUser;
import bg.softuni.exam.repository.OfferRepository;
import bg.softuni.exam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedInUser loggedInUser;
    private final OfferRepository offerRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedInUser loggedInUser, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedInUser = loggedInUser;
        this.offerRepository = offerRepository;
    }

    public boolean register(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> byUsername = userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername.isPresent()) {
            System.out.printf("User with username: %s already exists!\n", userRegisterDTO.getUsername());
            return false;
        }

        Optional<UserEntity> byEmail = userRepository.findByEmail(userRegisterDTO.getEmail());
        if (byEmail.isPresent()) {
            System.out.printf("User with email: %s already exists!\n", userRegisterDTO.getEmail());
            return false;
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            System.out.println("Passwords don't match!");
            return false;
        }

        UserEntity newUser = modelMapper.map(userRegisterDTO, UserEntity.class);
        userRepository.save(newUser);
        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        UserEntity user = userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null);
        if (user == null) {
            System.out.printf("User with username: %s does not exist!\n", userLoginDTO.getUsername());
            return false;
        }

        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            System.out.println("Invalid username or password!");
            return false;
        }

        loggedInUser.login(user);
        return true;
    }

    public void buyOffer(Long offerId) {
        OfferEntity offer = offerRepository.findById(offerId).get();
        UserEntity seller = userRepository.findById(offer.getSeller().getId()).get();
        seller.removeOffer(offer);
        userRepository.save(seller);
        UserEntity buyer = userRepository.findById(loggedInUser.getId()).get();
        buyer.buyOffer(offer);
        userRepository.save(buyer);
    }
}
