package view.loaders.restaurant;

import implementations.loaders.restaurant.tripAvisor.PriceRange;
import org.jsoup.nodes.Document;

public interface PriceParser {
    PriceRange getPrice(Document doc);
}
