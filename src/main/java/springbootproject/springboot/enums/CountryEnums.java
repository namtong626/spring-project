package springbootproject.springboot.enums;

import lombok.Getter;

@Getter
public enum CountryEnums {

    VIETNAM("Vietnamese"),

    THAILAND("Thai"),

    JAPAN("Japanese"),

    PHILIPPINES("Filipino"),

    CHINA("Chinese"),

    USA("English"),

    SPANISH("Spanish"),

    KOREA("Korean"),

    RUSSIA("Russian");

    private final String language;

    CountryEnums(String language) {
        this.language = language;
    }
}