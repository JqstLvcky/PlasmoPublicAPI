package eu.plasmo.api;

public enum Rank {

    PLAYER("Игрок"),
    VIP("VIP"),
    PREMIUM("Premium"),
    HOLY("Holy"),
    IMMORTAL("Immortal"),
    BUILDER("Билдер"),
    MAPLEAD("Главный билдер"),
    YOUTUBE("YouTube"),
    DEV("Разработчик"),
    ORGANIZER("Организатор"),
    MODER("Модератор"),
    WARDEN("Проверенный модератор"),
    CHIEF("Главный модератор"),
    ADMIN("Главный админ");

    private String name;

    Rank(String name) {

        this.name = name;

    }

    public String getName() {
        return name;
    }

    public static Rank get(String name) {

        for (Rank rank : values()) {

            if (rank.name().equalsIgnoreCase(name))
                return rank;

        }

        return Rank.PLAYER;

    }

}
