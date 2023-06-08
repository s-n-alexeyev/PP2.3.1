package mvc.controller;

import mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import mvc.service.UserService;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "list"})
    public String showAllUsers(Model model, @ModelAttribute("flashMessage") String flashAttribute) {
        model.addAttribute("users", userService.getAllUsers());
        return "list";
    }

    @GetMapping(value = "/new")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "form";
    }

    @GetMapping("/{id}/edit")
    public String edidtUserForm(@PathVariable(value = "id") long id, Model model,
                                RedirectAttributes attributes) {
        User user = userService.readUser(id);

        if (null == user) {
            attributes.addFlashAttribute("flashMessage", "Пользователь отсутствует!");
            return "redirect:/users";
        }

        model.addAttribute("user", userService.readUser(id));
        return "form";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        userService.createOrUpdateUser(user);
        attributes.addFlashAttribute("flashMessage",
                "Пользователь " + user.getFirstName() + " успешно создан/обновлен!");
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", defaultValue = "") long id, RedirectAttributes attributes) {
        User user = userService.deleteUser(id);

        attributes.addFlashAttribute("flashMessage",
                (user == null) ?
                        "Пользователь не найден!" :
                        "Пользователь " + user.getFirstName() + " успешно удален!");

        return "redirect:/users";
    }
}