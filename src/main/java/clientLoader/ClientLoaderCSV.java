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
        try {
            clientList = new ClientReaderCSV(new BufferedReader(new FileReader(url))).read(rowNumber);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return clientList;
    }
}