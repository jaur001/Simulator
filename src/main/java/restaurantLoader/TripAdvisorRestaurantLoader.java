package restaurantLoader;

import model.restaurant.Restaurant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripAdvisorRestaurantLoader implements RestaurantLoader{
    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();
    private String telephoneNumber = "";
    private String street = "";
    private String name;
    private int minPrice = 0;
    private int maxPrice = 0;
    private int numberTables = 20;
    private int numberWorkers = 10;
    private double socialCapital = 10000;

    public List<Restaurant> load(int j) throws IOException {


        Document doc = Jsoup.connect("https://www.tripadvisor.es/RestaurantSearch-g187471-oa"+ j*30 + "-a_date.2019__2D__11__2D__20-a_people.2-a_time.20%3A00%3A00-a_zur.2019__5F__11__5F__20-Gran_Ca.html#EATERY_LIST_CONTENTS").get();
        Element topRestaurant = doc.getElementById("component_2");
        Elements topRestaurantList = topRestaurant.getElementsByClass("restaurants-list-ListCell__restaurantName--2aSdo");

        for (Element i : topRestaurantList){
            doc = Jsoup.connect("https://www.tripadvisor.es"+i.attr("href")).get();

            //Name
            getName(doc);
            System.out.println(j + ") " + name);

            //Telephone number
            getTelephoneNumber(doc);

            //Street
            getStreet(doc);

            //Price
            getPrice(doc);

            restaurantList.add(new Restaurant(name,street,telephoneNumber,minPrice,maxPrice,numberTables,numberWorkers,socialCapital));
        }

        return restaurantList;
    }

    private void getName(Document doc){
        Elements nameList = doc.getElementsByClass("ui_header h1");
        name = nameList.first().text();
    }

    private void getTelephoneNumber(Document doc){
        Elements numberList = doc.getElementsByClass("detail  is-hidden-mobile");
        if (numberList.hasText()) {
            telephoneNumber = numberList.first().text();
        }
    }

    private void getStreet(Document doc) {
        Elements streetList = doc.getElementsByClass("street-address");
        if (streetList.hasText()) {
            street = streetList.first().text();
        }
    }

    private void getPrice(Document doc) {
        Elements els = doc.getElementsByClass("restaurants-detail-overview-cards-DetailsSectionOverviewCard__tagText--1OH6h");
        if(calculatePrice(els)) return;
        els = doc.getElementsByClass("restaurants-details-card-TagCategories__tagText--Yt3iG");
        if(calculatePrice(els)) return;
    }

    private boolean calculatePrice(Elements els) {
        if(els.size()>0){
            String text = els.first().text();
            if(text.contains("€")){
                String price = text.substring(0, text.indexOf("€", text.indexOf("-")) + 1); // Sometimes it has more details, not only price
                minPrice = Integer.parseInt(price.substring(0, price.indexOf("€") - 1));
                maxPrice = Integer.parseInt(price.substring(price.indexOf("-") + 2, price.length() - 2));
            }
            return true;
        }
        return false;
    }

}
