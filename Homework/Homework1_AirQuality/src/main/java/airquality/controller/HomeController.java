package airquality.controller;

import airquality.model.Location;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    @ApiOperation(value="Go to index")
    public String index(Model model){
        LOGGER.info("Go to index");
        model.addAttribute("location", new Location());
        return "index";
    }


    @GetMapping("/moreInfo")
    @ApiOperation(value="Search by location and date page")
    public String moreInfobyDate(Model model){
        LOGGER.info("more info");
        model.addAttribute("location", new Location());
        return "airPollutionByDate";
    }




}
