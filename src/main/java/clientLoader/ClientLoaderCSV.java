package clientLoader;

import model.client.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLoaderCSV implements ClientLoader {

    public List<Client> load(String url) {
        List<Client> clientList = new ArrayList<Client>();
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(url));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(",");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientList;
    }
}