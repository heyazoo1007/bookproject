package com.heyazoo1007.book.web;

import com.heyazoo1007.book.config.auth.LoginUser;
import com.heyazoo1007.book.config.auth.dto.SessionUser;
import com.heyazoo1007.book.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){

        if(user!=null){
            model.addAttribute("userName", user.getName());
        }
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

    @GetMapping("/bookshelf/{bookTitle}")
    public String bookshelfBookTitle(){
        return "bookshelf-bookTitle";
    }

    @GetMapping("/bookshelf/update")
    public String bookshelfUpdate(){
        return "bookshelf-update";

    }



}



