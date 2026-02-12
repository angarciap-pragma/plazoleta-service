package com.plazoleta.service.domain.model;

public record Restaurant(Long id, String name, String nit, String address, String phone, String urlLogo, Long ownerId) {

}
