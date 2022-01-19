package com.heyazoo1007.book.web;

import com.heyazoo1007.book.config.auth.LoginUser;
import com.heyazoo1007.book.config.auth.dto.SessionUser;
import com.heyazoo1007.book.service.posts.PostsService;
import com.heyazoo1007.book.web.dto.PostsListResponseDto;
import com.heyazoo1007.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("posts",postsService.findAllAsc());

        return "bookShelf";
    }

    @GetMapping("/bookshelf/save")
    public String bookshelfSave(){

        return "bookshelf-save";
    }

    @GetMapping("/bookshelf/{id}")
    public String bookshelfId( @PathVariable Long id,Model model){
        model.addAttribute("posts",postsService.findById(id));

        return "bookshelf-id";
    }

    @GetMapping("/bookshelf/update/{id}")
    public String bookshelfUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post",dto);
        return "bookshelf-update";

    }
}



