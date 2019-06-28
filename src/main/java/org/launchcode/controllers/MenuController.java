package org.launchcode.controllers;

import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.launchcode.models.Cheese;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("menu", new Menu());
        return "menu/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST )
    public String add(Model mode, @ModelAttribute @Valid Menu menu, Errors errors){

        if (errors.hasErrors()){
            return "menu/add";
        } else{
            Menu m = menuDao.save(menu);
            return "redirect:view/" + menu.getId();
        }

    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){
        Menu m = menuDao.findOne(menuId);
        model.addAttribute("menu", m);
        return "menu/view";

    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId){
        Menu m = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(m, cheeseDao.findAll());
        model.addAttribute("title", "Add item to menu: " + m.getName());
        model.addAttribute("form", form);
        return "menu/add-item";

    }

    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors, @RequestParam int menuId){
        if (errors.hasErrors()){
            return "menu/add-item";
        } else {
            Cheese c = cheeseDao.findOne(form.getCheeseId());
            Menu m = menuDao.findOne(form.getMenuId());
            m.addItem(c);
            menuDao.save(m);
        } return "redirect:/menu/view/" + form.getMenuId();
    }
}
