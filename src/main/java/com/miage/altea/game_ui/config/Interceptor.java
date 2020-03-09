package com.miage.altea.game_ui.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Interceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        List<Locale.LanguageRange> language = new ArrayList<Locale.LanguageRange>();
        language.add(new Locale.LanguageRange("fr"));
        if (!headers.containsKey("Accept-language")) {
            headers.setAcceptLanguage(language);
        }
        return clientHttpRequestExecution.execute(httpRequest, bytes);

    }
}
