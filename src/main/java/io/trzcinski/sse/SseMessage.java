package io.trzcinski.sse;

import java.util.Objects;

/**
 * @author Jakub Trzcinski jakub@trzcinski.io
 * @since 29-06-2023
 */
public class SseMessage {
    private final String type;
    private final String data;

    public SseMessage(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "SseMessage{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SseMessage that = (SseMessage) o;
        return Objects.equals(type, that.type) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, data);
    }
}
