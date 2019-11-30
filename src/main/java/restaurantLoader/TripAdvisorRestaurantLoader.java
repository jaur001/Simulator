package restaurantLoader;

import model.restaurant.Menu;
import model.restaurant.Restaurant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripAdvisorRestaurantLoader implements RestaurantLoader{
    public List<Restaurant> load(int j) throws IOException {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        String telephoneNumber = "";
        String street = "";
        String name;
        Menu menu = null;
        int minPrice = 0;
        int maxPrice = 0;

        Document doc = Jsoup.connect("https://www.tripadvisor.es/RestaurantSearch-g187471-oa"+ Integer.toString(j*30) + "-a_date.2019__2D__11__2D__20-a_people.2-a_time.20%3A00%3A00-a_zur.2019__5F__11__5F__20-Gran_Ca.html#EATERY_LIST_CONTENTS").get();
        Element topRestaurant = doc.getElementById("component_2");
        Elements topRestaurantList = topRestaurant.getElementsByClass("restaurants-list-ListCell__restaurantName--2aSdo");

        for (Element i : topRestaurantList){
            doc = Jsoup.connect("https://www.tripadvisor.es"+i.attr("href")).get();

            //Name
            Elements nameList = doc.getElementsByClass("ui_header h1");
            name = nameList.first().text();
            System.out.println(j + ") " + name);

            //menu
            Elements menuList = doc.getElementsByClass("is-hidden-mobile blEntry menu  ui_link");
            if (menuList.hasText()) {
                String menuStr = menuList.first().attr("onclick");
                menuStr = menuStr.indexOf("https:")>=0?menuStr.substring(menuStr.indexOf("https:"),menuStr.length()-2):"";
                menu = new Menu(menuStr);
            }

            //Telephone number
            Elements numberList = doc.getElementsByClass("detail  is-hidden-mobile");
            if (numberList.hasText()) {
                telephoneNumber = numberList.first().text();
            }

            //Street
            Elements streetList = doc.getElementsByClass("street-address");
            if (streetList.hasText()) {
                street = streetList.first().text();
            }

            //Price
            Elements els = doc.getElementsByClass("restaurants-detail-overview-cards-DetailsSectionOverviewCard__tagText--1OH6h");
            if(els.size()>0){
                String text = els.first().text();
                if(text.indexOf("€")>=0){
                    String price = text.substring(0,text.indexOf("€",text.indexOf("-"))+1);
                    minPrice = Integer.parseInt(price.substring(0,price.indexOf("€")-1));
                    maxPrice = Integer.parseInt(price.substring(price.indexOf("-")+2,price.length()-2));
                }
            } else {
                els = doc.getElementsByClass("restaurants-details-card-TagCategories__tagText--Yt3iG");
                if(els.size()>0){
                    String text = els.first().text();
                    if (text.indexOf("€")>=0){
                        String price = text.substring(0,text.indexOf("€",text.indexOf("-"))+1);
                        minPrice = Integer.parseInt(price.substring(0,price.indexOf("€")-1));
                        maxPrice = Integer.parseInt(price.substring(price.indexOf("-")+2,price.length()-2));
                    }
                }
            }
            if (menu!=null){
                restaurantList.add(new Restaurant(name,street,telephoneNumber, menu,minPrice,maxPrice,20,10,10000));
            } else {
                restaurantList.add(new Restaurant(name,street,telephoneNumber,minPrice,maxPrice,20,10,10000));
            }

        }


        return restaurantList;
    }


    public static void main(String[] args) throws IOException {
        TripAdvisorRestaurantLoader loader = new TripAdvisorRestaurantLoader();
        List<Restaurant> list = loader.load(0);
        for(Restaurant i : list){
            if(i.hasMenu()) {
                System.out.println(i.getMenu().getUrl());
            }
        }
    }
}
