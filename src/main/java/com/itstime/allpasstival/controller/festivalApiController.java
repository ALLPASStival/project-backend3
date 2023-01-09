package com.itstime.allpasstival.controller;

import com.itstime.allpasstival.domain.dto.festivalUpdateRequestDto;
import com.itstime.allpasstival.domain.entity.festival;
import com.itstime.allpasstival.service.festivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class festivalApiController {

    private final festivalService fesposService = null;


    //글 작성처리
    @PostMapping("/api/festivals")
    public String festivalSave(festival festivals){
        return fesposService.write(festivals);
    }

    //게시글 수정기능
    @PutMapping("/api/festivals/{id}")
    public String Modify(@PathVariable ("id") Integer id, Model model){
        model.addAttribute("festivalmodify",festivalService.viewDetail(id));
        return "modify";
    }

    @PostMapping("/api/festivals/{id")
    public String Update(@PathVariable("id") Integer id, festival festivals, Model model, MultipartFile file){
        festivalUpdateRequestDto dto = festivalService.findByid(id);
        model.addAttribute("update",dto);

        return "redirect:i/festivals/list";
    }

    //게시글 리스트
    @GetMapping("/api/festivals/list")
    public String festivalList(Model model,  @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC)Pageable pageable, String keyWord){


        Page<festival> list = null;
        if(keyWord == null){
            //검색키워드가 안들어오면
            list = fesposService.fesList(pageable);
        }
        else{
            //들어오면
            list = fesposService.festivalSearch(keyWord, pageable);
        }


        int Now = list.getPageable().getPageNumber() + 1;//페이지는 0부터 시작하니까
        int start = Math.max(Now-4,1);
        int End = Math.min(Now+5, list.getTotalPages());
        model.addAttribute("list",list);
        model.addAttribute("now",Now);
        model.addAttribute("start",start);
        model.addAttribute("End",End);
        return "List festival";
    }

    //글 리스트 클릭해서 게시글 들어갔을 때 보이는거.
    @GetMapping("/api/festivals/viewDetails")
    public String viewDetails(Model model, Integer id){
        model.addAttribute("festival",festivalService.viewDetail(id));
        return "viewDetails";
    }

    //삭제기능
    @GetMapping("/api/festivals/delete")
    public String Delete(Integer id){
        fesposService.Delete(id);
        //삭제 후 리스트로 다시 돌아감
        return "redirect:i/festivals/list";
    }
}
