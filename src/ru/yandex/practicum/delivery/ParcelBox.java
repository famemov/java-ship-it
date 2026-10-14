package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final List<T> parcels = new ArrayList<>();
    private final int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        long fullWeight = 0;
        for (T existingParcel : parcels) {
            fullWeight += existingParcel.weight;
        }
        if (fullWeight + parcel.weight > maxWeight) {
            System.out.println("Суммарный вес превышает максимальный.");
            return;
        }
        parcels.add(parcel);
    }

    public List<T> getAllParcels() {
        return Collections.unmodifiableList(parcels);
    }
}
