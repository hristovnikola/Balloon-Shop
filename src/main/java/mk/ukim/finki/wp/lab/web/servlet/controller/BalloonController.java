package mk.ukim.finki.wp.lab.web.servlet.controller;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(){
        return "access_denied";
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloons = this.balloonService.listAll();
        List<Type> types = List.of(Type.values());
        model.addAttribute("types", types);
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @GetMapping("/filter")
    public String filterBalloons(Model model, @RequestParam String balloonType){
        List<Balloon> balloons = this.balloonService.listAll();
        List<Type> types = List.of(Type.values());
        model.addAttribute("types", types);
        model.addAttribute("balloons", balloonService.filterAllBalloonsByType(balloonType));

        return "listBalloons";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Type> types = List.of(Type.values());
        model.addAttribute("types", types);
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
    }


    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer,
                              @RequestParam(required = false) Type balloonType){

        if(id != null){
            this.balloonService.edit(id, name, description, manufacturer, balloonType);
        }else{
            this.balloonService.save(name, description, manufacturer, balloonType);
        }
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        if(this.balloonService.findById(id).isPresent()){
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("balloon", balloon);
            model.addAttribute("manufacturers", manufacturers);
            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

}
