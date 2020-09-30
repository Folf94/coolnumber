package main;

import main.model.Affair;
import main.model.AffairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AffairController {
    @Autowired
    private AffairRepository affairRepository;

    @GetMapping(value = "/affairs/")
    public List<Affair> affairList() {
        Iterable<Affair> affairIterable = affairRepository.findAll();
        ArrayList<Affair> affairArrayList = new ArrayList<>();
        for (Affair affairs : affairIterable){
            affairArrayList.add(affairs);
        }
        return affairArrayList;
    }

    @GetMapping(value = "/affairs/{id}")
    public ResponseEntity affairById(Integer id) {
        Optional<Affair> optionalAffair = affairRepository.findById(id);
        if (optionalAffair.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalAffair.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/affairs/")
        public int addAffair(Affair affair) {
        Affair newAffair = affairRepository.save(affair);
        return newAffair.getId();
    }

    @PutMapping(value = "/affairs/")
    public void affairUpdate(List<Affair> affairs) {
        Iterable<Affair> affairIterable = affairRepository.findAll();
        affairRepository.saveAll(affairIterable);
    }

    @PutMapping(value = "/affairs/{id}")
    public void affairUpdateById(Affair affair, Integer id) {
       if (affairRepository.existsById(id)){
           Affair newAffair = affairRepository.save(affair);
       }
    }

    @DeleteMapping(value = "/affairs/")
    public void deleteAffairs() {
        affairRepository.deleteAll();
    }

    @DeleteMapping(value = "/affairs/{id}")
    public void deleteAffairById(Integer id) {
        affairRepository.deleteById(id);
    }
}


