package com.plazoleta.service.infrastructure.adapter.in.web.dto.response;

import java.time.OffsetDateTime;
import java.util.Map;

public record ErrorResponse(String message, int code, OffsetDateTime timestamp, String path,
							Map<String, String> details) {

}
