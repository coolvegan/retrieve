package com.kittel.wiederfinden.controller;

import com.kittel.wiederfinden.model.Item;
import com.kittel.wiederfinden.model.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    ItemRepo itemRepo;

    private final String imgDirectory = "images";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model, @RequestParam(name = "option", required = false) String option, @RequestParam(name = "search", required = false) String search) {
        List<Item> books = itemRepo.findAll();
        if (search != null) {
            books = itemRepo.getByName(search);
            model.addAttribute("showRows", true);
        }
        model.addAttribute("name", "marco");
        model.addAttribute("book", books);

        if (option != null) {
            if (option.equals("showAddNewElement")) {
                model.addAttribute("showAddNewElement", true);
            }
            if (option.equals("showRows")) {
                model.addAttribute("showRows", true);
            }
        } else {
            model.addAttribute("showRows", true);
        }

        return "main";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "Accept=application/x-www-form-urlencoded")
    public String upload(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String location, @RequestParam(required = false) MultipartFile filepath) {
        String filePath = imgDirectory + "/" + filepath.getOriginalFilename();
        try {
            InputStream inputStream = filepath.getInputStream();
            Path path = Paths.get(imgDirectory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            path = Paths.get(filePath);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item item = new Item();
        item.setFilepath(filePath);
        item.setLocation(location);
        item.setName(name);

        itemRepo.save(item);

        model.addAttribute("name", name);
        model.addAttribute("location", location);
        model.addAttribute("filepath", filepath);
        return "redirect:/";
    }


}
