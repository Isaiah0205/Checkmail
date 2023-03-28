package com.example.demo.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationRepository locationRepository;

    @GetMapping
    List<Location> getLocations() {
        return locationRepository.findAll();
    }

    @PostMapping
    public void registerNewLocation(@RequestBody Location location) {
        locationRepository.save(location);
    }

    @GetMapping({"/showlo", "/", "/list"})
    public ModelAndView showLocations() {
        ModelAndView mav =new ModelAndView("index");
        List<Location> list = locationRepository.findAll();
        mav.addObject("location",list);
        return mav;
    }
}