package com.TejasThombare20.order_service.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse; 

public class InventoryClientStub {

    public static void stubInventoryCall(String skuCode, Integer quantity) {
        if (quantity <= 100) {
            stubFor(get(urlEqualTo("/api/inventory/" + skuCode + "/" + quantity))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody("true")));
        } else {
            stubFor(get(urlEqualTo("/api/inventory/" + skuCode + "/" + quantity))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody("false")));
        }
    }
}