package org.bahri;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataManagement {
    public <T> T readDataFromLocaleStorage(String databasePath, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(databasePath), type);
        } catch (IOException e) {
            System.err.println("Error reading data from " + databasePath + ": " + e.getMessage());
            return null; // or you could throw a custom exception
        }
    }

    public <T> ArrayList<T> readListDataFromLocaleStorage(String databasePath, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(new File(databasePath)); // Read JSON array from file
            // replace the node with an empty array list in case data is null
            if(node == null) node = mapper.valueToTree(new ArrayList<>());
            // Ensure the node is an array
            if (node.isArray()) {
                // Deserialize JSON array to ArrayList<T>
                return mapper.readValue(node.toString(), mapper.getTypeFactory()
                        .constructCollectionType(ArrayList.class, type));
            } else throw new IOException("JSON data is not an array");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }

    public <T> void writeDataToLocaleStorage(ArrayList<T> data, String databasePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Write the JSON object to a file with pretty printing
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(databasePath), data);
        } catch (IOException e) {
            System.out.println("Error writing data to " + databasePath + ": " + e.getMessage());
        }
    }

    public <T> void writeDataToLocaleStorage(T data, String databasePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Write the JSON object to a file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(databasePath), data);
        } catch (IOException e) {
            System.out.println("Error writing data to " + databasePath + ": " + e.getMessage());
        }
    }
}
