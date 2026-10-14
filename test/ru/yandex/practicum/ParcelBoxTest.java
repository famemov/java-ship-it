package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {
    @Test
    void addsParcelBelowLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel first = new StandardParcel("Книга", 20, "Москва", 10);
        StandardParcel second = new StandardParcel("Одежда", 30, "Москва", 10);
        box.addParcel(first);
        box.addParcel(second);

        assertEquals(2, box.getAllParcels().size());
        assertSame(first, box.getAllParcels().get(0));
        assertSame(second, box.getAllParcels().get(1));
    }

    @Test
    void rejectsParcelAboveLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel first = new StandardParcel("Книга", 60, "Москва", 10);
        StandardParcel second = new StandardParcel("Одежда", 50, "Москва", 10);
        box.addParcel(first);
        box.addParcel(second);

        assertEquals(1, box.getAllParcels().size());
        assertSame(first, box.getAllParcels().get(0));
        assertFalse(box.getAllParcels().contains(second));
    }

    @Test
    void addsParcelOneBelowLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel first = new StandardParcel("Книга", 60, "Москва", 10);
        StandardParcel second = new StandardParcel("Одежда", 39, "Москва", 10);
        box.addParcel(first);
        box.addParcel(second);

        assertEquals(2, box.getAllParcels().size());
        assertSame(first, box.getAllParcels().get(0));
        assertSame(second, box.getAllParcels().get(1));
    }

    @Test
    void addsParcelExactlyAtLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel first = new StandardParcel("Книга", 60, "Москва", 10);
        StandardParcel second = new StandardParcel("Одежда", 40, "Москва", 10);
        box.addParcel(first);
        box.addParcel(second);

        assertEquals(2, box.getAllParcels().size());
        assertSame(first, box.getAllParcels().get(0));
        assertSame(second, box.getAllParcels().get(1));
    }

    @Test
    void rejectsParcelOneAboveLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel first = new StandardParcel("Книга", 60, "Москва", 10);
        StandardParcel second = new StandardParcel("Одежда", 41, "Москва", 10);
        box.addParcel(first);
        box.addParcel(second);

        assertEquals(1, box.getAllParcels().size());
        assertSame(first, box.getAllParcels().get(0));
        assertFalse(box.getAllParcels().contains(second));
    }

    @Test
    void rejectsOverweightParcelInEmptyBox() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel parcel = new StandardParcel("Книга", 101, "Москва", 10);
        box.addParcel(parcel);
        assertTrue(box.getAllParcels().isEmpty());
    }
}
