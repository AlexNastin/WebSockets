package by.test.sockets.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.test.sockets.domain.Post;
import by.test.sockets.sort.SortedPostsByDate;

@Controller
public class HomeController {
	
	public static List<Post> posts = new ArrayList<Post>();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Collections.sort(posts, new SortedPostsByDate());
		model.addAttribute("list", posts);		
		return "home";
	}
	
}
