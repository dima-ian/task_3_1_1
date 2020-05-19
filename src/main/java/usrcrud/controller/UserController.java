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

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ModelAndView updatePost(@ModelAttribute("admin/user") User user, @RequestParam Long roleId) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(roleId));
        user.setRoles(roleSet);
        userService.editUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/update")
    public ModelAndView updateGet(@RequestParam Long id, ModelAndView model) {
        model.setViewName("edit");
        User user = userService.getUserById(id);
        model.addObject("user", user);
        List<Role> roles = roleService.getRoles();
        model.addObject("roles", roles);
        return model;
    }

    @RequestMapping(value = "/admin/delete")
    public ModelAndView deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView infoPageUser(ModelAndView modelAndView, Principal principal) {
        String name = principal.getName();
        User user = userService.findByUserName(name);
        modelAndView.addObject(user);
        modelAndView.setViewName("user");
        return modelAndView;
    }

}
