package ru.yandex.practicum.delivery;

import java.util.*;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();
    private static final int BOX_MAX_WEIGHT = 100; // тут как мне кажется надо указывать заранее, а не вводить
    // с клавиатуры, если я правильно понял.
    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(BOX_MAX_WEIGHT);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(BOX_MAX_WEIGHT);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(BOX_MAX_WEIGHT);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportParcelsStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить местоположение посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки: 1 — обычная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());
        if (type < 1 || type > 3) {
            System.out.println("Неверный тип посылки.");
            return;
        }

        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Введите вес:");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Введите день отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                StandardParcel standardParcel =
                        new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standardBox.addParcel(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel =
                        new FragileParcel(description, weight, deliveryAddress, sendDay);

                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileBox.addParcel(fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок годности в днях:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel =
                        new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int costs = 0;
        for (Parcel parcel : allParcels) {
            costs += parcel.calculateDeliveryCost();
        }
        System.out.println(costs+" денег.");
    }

    private static void reportParcelsStatus() {
        System.out.println("Введите новое местоположение:");
        String newLocation = scanner.nextLine();

        for (Trackable parcel : trackableParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки: 1 — обычная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());
        switch (type) {
            case 1:
                printBoxContents(standardBox);
                break;
            case 2:
                printBoxContents(fragileBox);
                break;
            case 3:
                printBoxContents(perishableBox);
                break;
            default:
                System.out.println("Неверный тип коробки.");
        }
    }

    private static void printBoxContents(ParcelBox<? extends Parcel> box) {
        if (box.getAllParcels().isEmpty()) {
            System.out.println("Коробка пуста.");
            return;
        }
        for (Parcel parcel : box.getAllParcels()) {
            System.out.println(parcel.description);
        }
    }

}
