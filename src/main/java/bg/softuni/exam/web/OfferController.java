package bg.softuni.exam.web;

import bg.softuni.exam.model.dto.OfferAddDTO;
import bg.softuni.exam.model.session.LoggedInUser;
import bg.softuni.exam.service.OfferService;
import bg.softuni.exam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final UserService userService;
    private final LoggedInUser loggedInUser;

    public OfferController(OfferService offerService, UserService userService, LoggedInUser loggedInUser) {
        this.offerService = offerService;
        this.userService = userService;
        this.loggedInUser = loggedInUser;
    }

    @ModelAttribute("offerAddDTO")
    public OfferAddDTO initOfferAddDTO() {
        return new OfferAddDTO();
    }

    @GetMapping("/offer-add")
    public String addOffer() {
        if (loggedInUser.isNotLoggedIn()) {
            return "redirect:/users/login";
        }
        return "offer-add";
    }

    @PostMapping("/offer-add")
    public String addOffer(@Valid OfferAddDTO offerAddDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (loggedInUser.isNotLoggedIn()) {
            return "redirect:/users/login";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDTO", offerAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO", bindingResult);
            return "redirect:/offers/offer-add";
        }
        offerService.addOffer(offerAddDTO);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable("id") Long id) {
        if (loggedInUser.isNotLoggedIn()) {
            return "redirect:/users/login";
        }
        offerService.removeOffer(id);
        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") Long id) {
        if (loggedInUser.isNotLoggedIn()) {
            return "redirect:/users/login";
        }
        userService.buyOffer(id);
        return "redirect:/home";
    }
}
