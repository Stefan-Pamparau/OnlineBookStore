package com.iquestgroup.database.builder;

import com.iquestgroup.model.Purchase;

import java.sql.Timestamp;

/**
 * Builder user to create {@link com.iquestgroup.model.Purchase} instances.
 *
 * @author Stefan Pamparau
 */
public class PurchaseBuilder {
    private Timestamp purchaseDate;

    public PurchaseBuilder setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Purchase build() {
        Purchase purchase = new Purchase();

        purchase.setPurchaseDate(purchaseDate);

        return purchase;
    }

    public Purchase build(Timestamp purchaseDate) {
        Purchase purchase = new Purchase();

        purchase.setPurchaseDate(purchaseDate);

        return purchase;
    }
}
