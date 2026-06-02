package web.dataManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ajay Talpur
 */
public final class TestDataManager {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Map<String, JsonNode> CACHE =
            new ConcurrentHashMap<>();

    private TestDataManager() {
        throw new IllegalStateException(
                "Utility class");
    }

    private static JsonNode loadFile(String fileName) {

        return CACHE.computeIfAbsent(fileName, file -> {

            try (InputStream inputStream =
                         TestDataManager.class
                                 .getClassLoader()
                                 .getResourceAsStream(
                                         "testdata/" + file + ".json")) {

                if (inputStream == null) {
                    throw new RuntimeException(
                            "Test data file not found: "
                                    + file + ".json");
                }

                return OBJECT_MAPPER.readTree(inputStream);

            } catch (Exception exception) {

                throw new RuntimeException(
                        "Failed to load test data file: "
                                + file,
                        exception);
            }
        });
    }

    private static JsonNode getNode(
            String fileName,
            String... path) {

        JsonNode currentNode = loadFile(fileName);

        for (String key : path) {

            currentNode = currentNode.get(key);

            if (currentNode == null) {

                throw new RuntimeException(
                        "Invalid path in "
                                + fileName
                                + ".json -> "
                                + String.join(".", path));
            }
        }

        return currentNode;
    }

    public static <T> T getObject(
            String fileName,
            Class<T> clazz,
            String... path) {

        try {

            return OBJECT_MAPPER.treeToValue(
                    getNode(fileName, path),
                    clazz);

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Unable to map JSON to "
                            + clazz.getSimpleName(),
                    exception);
        }
    }

    public static String getValue(
            String fileName,
            String... path) {

        return getNode(fileName, path).asText();
    }

    public static int getInt(
            String fileName,
            String... path) {

        return getNode(fileName, path).asInt();
    }

    public static boolean getBoolean(
            String fileName,
            String... path) {

        return getNode(fileName, path).asBoolean();
    }

    public static void clearCache() {
        CACHE.clear();
    }
}