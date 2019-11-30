package clientLoader;

import model.client.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLoaderCSV implements ClientLoader {

    public List<Client> load(String url,int rowNumber) {
        List<Client> clientList = new ArrayList<Client>();
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(url));
            int i = 0;
            while ((line = br.readLine()) != null && i < rowNumber) {

                // use comma as separator
                String[] data = line.split(",");
                clientList.add(new Client(data));
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientList;
    }
}