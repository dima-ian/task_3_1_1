package usrcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import usrcrud.model.Role;
import usrcrud.model.User;
import usrcrud.service.RoleService;
import usrcrud.service.UserService;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ModelAndView startPageAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "user", new User());
        modelAndView.addObject("users", this.userService.allUsers());
        modelAndView.addObject("roles", this.roleService.getRoles());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = {"admin/add"}, method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("user") User user, @RequestParam Long roleId ) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(roleId));
        user.setRoles(roleSet);
        userService.addUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute("admin/user") User user, @RequestParam Long roleId) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(roleId));
        user.setRoles(roleSet);
        userService.editUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editGet(@RequestParam Long userId, ModelAndView modelAndView) {
        modelAndView.setViewName("edit");
        //ModelAndView model = new ModelAndView("edit");
        User user = userService.getUserById(userId);
        modelAndView.addObject("user", user);
        List<Role> roles = roleService.getRoles();
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete")
    public ModelAndView deleteUser(@RequestParam Long userId) {
        System.out.println("User ID to DELETE = " + userId);
        userService.deleteUser(userId);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView infoPageUser(ModelAndView modelAndView, Principal principal) {
        String name = principal.getName();
        User user = userService.getUserByUserName(name);
        System.out.println(user);
        modelAndView.addObject(user);
        modelAndView.setViewName("the_user");
        return modelAndView;
    }

}
