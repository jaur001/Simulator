package implementations.loaders.restaurant;

import model.restaurant.Restaurant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import view.loaders.restaurant.RestaurantLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripAdvisorRestaurantLoader implements RestaurantLoader {
    public static final String WEBPAGE = "https://www.tripadvisor.es";
    public static final String HREF = "href";
    public static final String RESTAURANT_COLLECTION = "component_2";
    public static final String RESTAURANT_SELECTOR = "_15_ydu6b";
    public static final String RESTAURANT_WEBPAGE_SEARCHER = "https://www.tripadvisor.es/RestaurantSearch-g187471-oa";
    public static final String HTML_FILE = "-a_date.2019__2D__11__2D__20-a_people.2-a_time.20%3A00%3A00-a_zur.2019__5F__11__5F__20-Gran_Ca.html#EATERY_LIST_CONTENTS";
    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();

    public List<Restaurant> load(int j) throws IOException {

        Document doc = connect(j);
        Elements topRestaurantList = getRestaurantList(doc);
        for (Element i : topRestaurantList){
            restaurantList.add(loadRestaurant(i));
            System.out.println(j + ") " + restaurantList.get(restaurantList.size()-1).getName());
        }

        return restaurantList;
    }

    private Restaurant loadRestaurant(Element i) throws IOException {
        Document doc = connectToRestaurantPage(i);
        return new TripAdvisorRestaurantDataLoader().loadData(doc);
    }

    private Document connectToRestaurantPage(Element i) throws IOException {
        Document doc;
        doc = Jsoup.connect(WEBPAGE +i.attr(HREF)).get();
        return doc;
    }

    private Elements getRestaurantList(Document doc) {
        Element topRestaurant = doc.getElementById(RESTAURANT_COLLECTION);
        return topRestaurant.getElementsByClass(RESTAURANT_SELECTOR);
    }

    private Document connect(int j) throws IOException {
        return Jsoup.connect(RESTAURANT_WEBPAGE_SEARCHER + j*30 + HTML_FILE).get();
    }

}
