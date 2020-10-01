package main;

import main.model.Affair;
import main.model.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    private AffairRepository affairRepository;

    @RequestMapping(name = "/")
    public String index(Model model) {
        Iterable<Affair> allAffairs = affairRepository.findAll();
        List<Affair> affairList = new ArrayList<>((Collection<? extends Affair>) allAffairs);
        model.addAttribute("affairs", affairList);
        model.addAttribute("affairCount", affairList.size());
        return "index";
    }
}
