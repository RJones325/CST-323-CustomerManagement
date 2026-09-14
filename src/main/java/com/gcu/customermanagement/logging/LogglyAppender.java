package com.gcu.customermanagement.logging;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogglyAppender extends AppenderBase<ILoggingEvent> {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void append(ILoggingEvent event) {

        if (url == null || url.isBlank()) {
            return;
        }

        try {
            String message =
                    event.getLevel() + " " +
                    event.getLoggerName() + " - " +
                    event.getFormattedMessage();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "text/plain")
                    .POST(HttpRequest.BodyPublishers.ofString(message))
                    .build();

            HttpClient.newHttpClient()
                    .sendAsync(request, HttpResponse.BodyHandlers.discarding());

        } catch (Exception e) {
            addError("Unable to send log to Loggly", e);
        }
    }
}