package com.heyazoo1007.book.web;

import com.heyazoo1007.book.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/bookshelf")
    public String bookshelf(Model model){
        model.addAttribute("posts",postsService.findAllDesc());

        return "bookShelf";
    }

    @GetMapping("/bookshelf/save")
    public String bookshelfSave(){
        return "bookshelf-save";
    }


}
