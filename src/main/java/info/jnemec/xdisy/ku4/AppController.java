package info.jnemec.xdisy.ku4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimeZone;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class AppController {

    private Service tzb = new Service();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("tz", TimeZone.getDefault());
        return "index";
    }

    @RequestMapping(value = "/getRandom", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<ActualTime> getRandom() throws IOException {
        return tzb.getRandom();
    }

    @RequestMapping(value = "/getActive", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<ActualTime> getActive() throws IOException {
        return tzb.getActive();
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public
    @ResponseBody
    int add() {
        return tzb.clear();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public
    @ResponseBody
    TimeZone add(@RequestParam(value = "tz") String timeZoneId) {
        return tzb.add(timeZoneId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public
    @ResponseBody
    ActualTime get(@RequestParam(value = "tz") String timeZoneId) {
        return tzb.get(timeZoneId);
    }

}