package org.launchcode.models.forms;

import org.launchcode.models.Menu;
import org.launchcode.models.Cheese;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    //fields to render the form
    private Menu menu;

    private Iterable<Cheese> cheeses;

    //fields to process the form
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    //Constructors
    //Default - needed for model binding to work
    public AddMenuItemForm(){

    }

    //Constructor
    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses){
        this.menu = menu;
        this.cheeses = cheeses;
    }


    //Getters (accessors):


    public Menu getMenu() {
        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {

        return cheeseId;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
