package com.alexjdevman.asset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Index page controller
 *
 * @author Aleksey Gorbachev
 */
@Controller
public class IndexController {

    /**
     * Redirecting to index page
     *
     * @return index page
     */
    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }
}
