package io.trzcinski.sse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Jakub Trzcinski jakub@trzcinski.io
 * @since 29-06-2023
 */
public class SseClient {
    private final URL url;
    private final Map<String, String> headers;

    public SseClient(URL url, Map<String, String> headers) {
        this.url = url;
        this.headers = headers;
    }

    public SseClient(URL url) {
        this.url = url;
        this.headers = Collections.emptyMap();
    }

    public void start(Consumer<SseMessage> messageConsumer) throws IOException {

        URLConnection connection = url.openConnection();
        headers.forEach((k, v) -> {
            connection.addRequestProperty(k, v);
        });
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        String inputLine;
        String buffer = "";

        while ((inputLine = in.readLine()) != null) {
            buffer += inputLine + "\n";
            if (inputLine == null || inputLine.isBlank()) {
                var entry = parse(buffer);
                messageConsumer.accept(entry);
                buffer = "";
            }
        }

        in.close();

    }

    SseMessage parse(String raw) {
        var data = new HashMap<String, String>();

        Arrays.stream(raw.split("\n"))
                .map(e -> e.split(":", 2)).forEach(row -> {
                    data.put(row[0].trim(), row[1].trim());
                });
        return new SseMessage(data.get("event"), data.get("data"));
    }
}
