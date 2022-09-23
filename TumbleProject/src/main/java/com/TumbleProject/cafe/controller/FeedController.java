package com.TumbleProject.cafe.controller;

import com.TumbleProject.cafe.domain.Cafe;
import com.TumbleProject.cafe.domain.Files;
import com.TumbleProject.cafe.service.CafeService;
import com.TumbleProject.cafe.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedController {
    private final CafeService cafeService;
    private final FileService fileService;

    @GetMapping("/cafe/cafeMap")
    public String cafeMap(){
        return "cafeHtml/cafeMap";
    }
    @GetMapping("/cafe/enroll")
    public String cafeEnroll(Model model) {
        model.addAttribute("cafe", new Cafe());
        return "cafeHtml/cafeEnroll";
    }

    @PostMapping(value = "/cafe/enroll")
    public String create(@Valid Cafe cafeform, @RequestPart MultipartFile files, BindingResult bindingResult, Model model) throws IOException {
        model.addAttribute("cafe", cafeform);
        if (bindingResult.hasErrors()) {
            return "/cafeHtml/cafeEnroll";
        }
        Cafe cafe = new Cafe();
        cafe.setName(cafeform.getName());
        cafe.setAddress(cafeform.getAddress());
        cafe.setDiscount(cafeform.getDiscount());
        cafe.setPhoneNum(cafeform.getPhoneNum());
        cafe.setHour(cafeform.getHour());
        cafe.setIntroduce(cafeform.getIntroduce());
        cafe.setTime(String.valueOf(LocalDateTime.now()));

        Files file = new Files();
        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        FilenameUtils.removeExtension(sourceFileName);

        File destinationFile;
        String destinationFileName;
        //String fileUrl = "/Users/2sh/Desktop/spring/2022-hackathon/TumbleProject/src/main/resources/static/img/";
        String fileUrl = System.getProperty("user.dir") + "/src/main/resources/static/img/";
        do{
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        }while(destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileUrl(fileUrl);
        model.addAttribute("img", file);
        fileService.save(file);
        cafeService.join(cafe);

        return "redirect:/cafe";
    }

    @GetMapping(value = "/cafe")
    public String list(Model model) {
        List<Cafe> cafes = cafeService.findCafes();
        model.addAttribute("cafes", cafes);
        List<Files> file = fileService.findFiles();
        model.addAttribute("file", file);
        return "cafeHtml/cafe";
    }

    @GetMapping(value = "/cafe/modify/{id}")
    public String cafeModify(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("cafe", cafeService.findCafeOne(id));
        return "/cafeHtml/cafeModify";

    }

    @PostMapping(value = "/cafe/modify/{Id}")
    public String updateCafe(@ModelAttribute("Cafe") Cafe c){
        Cafe cafe = new Cafe();
        cafe.setId(c.getId());
        cafe.setName(c.getName());
        cafe.setAddress(c.getAddress());
        cafe.setPhoneNum(c.getPhoneNum());
        cafe.setDiscount(c.getDiscount());
        cafe.setHour(c.getHour());
        cafe.setIntroduce(c.getIntroduce());

        cafeService.join(cafe);
        return "redirect:/cafe";
    }

    @PostMapping("/cafe/delete/{id}")
    public String cafeDelete(Integer id) {
        cafeService.boardDelete(id);
        System.out.println("id = " + id);

        return "redirect:/cafe";
    }


}
