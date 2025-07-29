package com.folkadev.folka_subs.domain.dto;

public record ErrorResponse(
    int statusCode,
    String errType,
    String errDetails) {
}
