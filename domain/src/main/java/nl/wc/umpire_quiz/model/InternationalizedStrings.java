package nl.wc.umpire_quiz.model;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

import java.util.Objects;

@Embeddable
public class InternationalizedStrings {
    @Lob
    @JsonbProperty("EN_US")
    private String enUS;
    @Lob
    @JsonbProperty("NL_NL")
    private String nlNL;

    public InternationalizedStrings() {
        //Needed for JPA
    }

    public InternationalizedStrings(String nlNL, String enUS) {
        this.nlNL = nlNL;
        this.enUS = enUS;
    }

    public String getEnUS() {
        return enUS;
    }

    public void setEnUS(String enUS) {
        this.enUS = enUS;
    }

    public String getNlNL() {
        return nlNL;
    }

    public void setNlNL(String nlNL) {
        this.nlNL = nlNL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InternationalizedStrings that = (InternationalizedStrings) o;
        return Objects.equals(getEnUS(), that.getEnUS()) && Objects.equals(getNlNL(), that.getNlNL());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getEnUS());
        result = 31 * result + Objects.hashCode(getNlNL());
        return result;
    }
}
