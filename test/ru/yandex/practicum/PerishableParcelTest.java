package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PerishableParcelTest {
    @Test
    void notExpiredDuringShelfLife() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 5);
        assertFalse(parcel.isExpired(12));
    }

    @Test
    void expiredAfterShelfLife() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 5);
        assertTrue(parcel.isExpired(20));
    }

    @Test
    void notExpiredOneDayBeforeDeadline() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 5);
        assertFalse(parcel.isExpired(14));
    }

    @Test
    void notExpiredOnDeadline() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 5);
        assertFalse(parcel.isExpired(15));
    }

    @Test
    void expiredOneDayAfterDeadline() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 5);
        assertTrue(parcel.isExpired(16));
    }

    @Test
    void zeroShelfLifeNotExpiredOnSendDay() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 0);
        assertFalse(parcel.isExpired(10));
    }

    @Test
    void zeroShelfLifeExpiredNextDay() {
        PerishableParcel parcel = new PerishableParcel("Продукты", 5, "Москва", 10, 0);
        assertTrue(parcel.isExpired(11));
    }

}
