package com.resident.weddingsecretary;

import java.math.BigDecimal;
import java.util.UUID;

import io.realm.RealmObject;

/**
 * Created by resident on 17.06.18.
 */

public class BudgetItem extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private String section;
    private String description;
    private BigDecimal plan;
    private BigDecimal fact;

    public String getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPlan() {
        return plan;
    }

    public void setPlan(BigDecimal plan) {
        this.plan = plan;
    }

    public BigDecimal getFact() {
        return fact;
    }

    public void setFact(BigDecimal fact) {
        this.fact = fact;
    }
}
