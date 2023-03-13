package bg.softuni.exam.web;

import bg.softuni.exam.model.session.LoggedInUser;
import bg.softuni.exam.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final OfferService offerService;
    private final LoggedInUser loggedInUser;

    public HomeController(OfferService offerService, LoggedInUser loggedInUser) {
        this.offerService = offerService;
        this.loggedInUser = loggedInUser;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (loggedInUser.isNotLoggedIn()) {
            return "redirect:/users/login";
        }
        model.addAttribute("currentUserOffers", offerService.getCurrentUserOffers());
        model.addAttribute("currentUserBoughtOffers", offerService.getCurrentUserBoughtOffers());
        model.addAttribute("otherOffers", offerService.getOtherOffers());
        model.addAttribute("otherOffersCount", offerService.getOtherOffers().size());
        return "home";
    }
}
