package ifparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FieldNameMapping {
    private final Map<String, String> mapping;

    public FieldNameMapping(String csvFilePath) throws IOException {
        this.mapping = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                mapping.put(parts[0].trim(), parts[1].trim());
            }
        }
    }

    public String getNewName(String oldName) {
        return mapping.getOrDefault(oldName, oldName);
    }
}
