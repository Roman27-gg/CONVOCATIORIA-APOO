package com.ut.sucursales.model.dto;

import java.util.List;

public record ProductWithMoreStock(String branchName, List<ProductStock> products) {
}
