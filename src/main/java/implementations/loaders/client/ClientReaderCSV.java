package implementations.loaders.client;

import model.client.Client;
import view.loaders.client.ClientReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientReaderCSV implements ClientReader {
    public static final String COMMA = ",";
    private BufferedReader br;

    public ClientReaderCSV(BufferedReader br) {
        this.br = br;
    }

    public List<Client> read(int rowNumber) {
        List<Client> clientList = new ArrayList<Client>();
        int i = 0;
        iterateFile(rowNumber, clientList, i);
        return clientList;
    }

    private void iterateFile(int rowNumber, List<Client> clientList, int i) {
        String line;
        try{
            while ((line = br.readLine()) != null && i < rowNumber) {
                readLine(clientList, i, line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLine(List<Client> clientList, int i, String line) {
        String[] data = line.split(COMMA);
        clientList.add(new Client(data));
    }
}
