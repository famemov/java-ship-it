package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParcelDeliveryCostTest {
    @Test
    void standardCostForWeightFive() {
        StandardParcel parcel = new StandardParcel("Книга", 5, "Москва", 10);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void standardCostForWeightTwelve() {
        StandardParcel parcel = new StandardParcel("Книга", 12, "Москва", 10);
        assertEquals(24, parcel.calculateDeliveryCost());
    }

    @Test
    void standardCostForZeroWeight() {
        StandardParcel parcel = new StandardParcel("Книга", 0, "Москва", 10);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    void standardCostForWeightOne() {
        StandardParcel parcel = new StandardParcel("Книга", 1, "Москва", 10);
        assertEquals(2, parcel.calculateDeliveryCost());
    }

    @Test
    void fragileCostForWeightFive() {
        FragileParcel parcel = new FragileParcel("Ваза", 5, "Москва", 10);
        assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    void fragileCostForWeightTwelve() {
        FragileParcel parcel = new FragileParcel("Ваза", 12, "Москва", 10);
        assertEquals(48, parcel.calculateDeliveryCost());
    }

    @Test
    void fragileCostForZeroWeight() {
        FragileParcel parcel = new FragileParcel("Ваза", 0, "Москва", 10);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    void fragileCostForWeightOne() {
        FragileParcel parcel = new FragileParcel("Ваза", 1, "Москва", 10);
        assertEquals(4, parcel.calculateDeliveryCost());
    }

    @Test
    void perishableCostForWeightFive() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 5);
        assertEquals(15, parcel.calculateDeliveryCost());
    }

    @Test
    void perishableCostForWeightTwelve() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 12, "Москва", 10, 5);
        assertEquals(36, parcel.calculateDeliveryCost());
    }

    @Test
    void perishableCostForZeroWeight() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 0, "Москва", 10, 5);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    void perishableCostForWeightOne() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 1, "Москва", 10, 5);
        assertEquals(3, parcel.calculateDeliveryCost());
    }

}
