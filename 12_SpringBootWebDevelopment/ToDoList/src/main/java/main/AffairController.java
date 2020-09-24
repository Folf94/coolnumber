package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Affair;

@RestController
public class AffairController {
    @RequestMapping(value = "/affairs/", method = RequestMethod.GET)
    public void affairList() {
        Storage.getAllAffair();
    }

    @RequestMapping(value = "/affairs/{id}", method = RequestMethod.GET)
    public Affair affairById(Affair id) {
        return Storage.getAffairById(id);
    }

    @RequestMapping(value = "/affairs/", method = RequestMethod.POST)
    public int addCase(Affair affair) {
        return Storage.addAffair(affair);
    }

    @RequestMapping(value = "/affairs/{id}", method = RequestMethod.PUT)
    public void affairUpdateById(Affair affair, int id) {
      Storage.updateAffairById(affair, id);
    }
    @RequestMapping(value = "/affairs/", method = RequestMethod.DELETE)
    public void deleteAffairs(){
        Storage.deleteAllAffair();
    }
    @RequestMapping(value = "/affairs/{id}", method = RequestMethod.DELETE)
    public void deleteAffairById(Affair id){
        Storage.deleteAffairById(id);
    }
}


