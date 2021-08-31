package com.kittel.wiederfinden.controller;

import com.kittel.wiederfinden.model.Item;
import com.kittel.wiederfinden.model.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    ItemRepo itemRepo;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable("id") Long id){
        itemRepo.deleteById(id);
    }

    @RequestMapping(value = "/awarded/{id}", method = RequestMethod.GET)
    public void awarded(@PathVariable("id") Long id, @RequestParam(name = "an") String an){
        Optional<Item> i = itemRepo.findById(id);
        i.get().setAwarded(an);
        itemRepo.save(i.get());
    }
}
