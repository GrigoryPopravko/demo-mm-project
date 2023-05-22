package by.paprauka.database.util;

import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public final class PredicateBuilder {

    private final List<Predicate> predicates;

    private PredicateBuilder() {
        predicates = new ArrayList<>();
    }

    public static PredicateBuilder builder() {
        return new PredicateBuilder();
    }

    public <T> PredicateBuilder add(T value, BiFunction<T, T, Predicate> func) {
        if (value != null) {
            this.predicates.add(func.apply(value, value));
        }
        return this;
    }

    public Predicate[] build() {
        return this.predicates.toArray(Predicate[]::new);
    }
}
