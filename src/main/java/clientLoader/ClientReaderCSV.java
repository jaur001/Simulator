package clientLoader;

import model.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientReaderCSV implements ClientReader{
    private BufferedReader br;

    public ClientReaderCSV(BufferedReader br) {
        this.br = br;
    }

    public List<Client> read(int rowNumber) {
        List<Client> clientList = new ArrayList<Client>();
        String line;
        int i = 0;
        try{
            while ((line = br.readLine()) != null && i < rowNumber) {
                String[] data = line.split(",");
                clientList.add(new Client(data));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientList;
    }
}
