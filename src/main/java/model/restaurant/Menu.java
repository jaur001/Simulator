package model.restaurant;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String url;
    private String menu;
    private List<Plate> plateList = new ArrayList<Plate>();

    public Menu(String url) {
        this.url = url;
    }

    public List<Plate> getPlateList() {
        return plateList;
    }

    public void addNewPlate(Plate plate){
        plateList.add(plate);
        System.out.println("Menu added");
    }

    public String getUrl() {
        return url;
    }
}
