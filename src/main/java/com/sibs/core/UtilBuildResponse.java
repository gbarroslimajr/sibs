package com.sibs.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author geraldobarrosjr
 */

public final class UtilBuildResponse {

    private UtilBuildResponse() {}

    public static URI getURILocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    public static ResponseEntity<?> buildResponse(Long id) {
        URI uri = getURILocation(id);
        return ResponseEntity.created(uri).build();
    }
}
