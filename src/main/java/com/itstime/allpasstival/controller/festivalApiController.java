package com.itstime.allpasstival.controller;



import com.itstime.allpasstival.domain.dto.festivalSaveRequestDto;
import com.itstime.allpasstival.service.festivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class festivalApiController {

    private final festivalService fesposService;

    @PostMapping("/api/festivals")
    public festivalSaveRequestDto save(@RequestBody festivalSaveRequestDto requestDto){
        return fesposService.save(requestDto);
    }

    @PutMapping("/api{festivals")
    public int update(@PathVariable int festivalID, @RequestBody festivalSaveRequestDto requestDto){
        return fesposService.update(festivalID,requestDto);
    }

    @GetMapping("/api/festivals/festivalID")
    public festivalSaveRequestDto findById(@PathVariable int festivalID){
        return fesposService.findById(festivalID);
    }

    @GetMapping("/api/festivals/list")
    public String festivalList(Model model, @PageableDefault(page=0,size=10,sort="id",direction = Sort.festivalUpdateRequestDto.DESC)){

        model.addAttribute("list",festivalService.festivalList(pageable));
        return "List festival";
    }

}
