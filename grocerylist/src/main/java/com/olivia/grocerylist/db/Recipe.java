package com.olivia.grocerylist.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long recipeId;
    private String name;
    private Integer servings;
    private Set<MealCategory> mealCategories;

    public enum MealCategory {
        BREAKFAST,
        LUNCH,
        DINNER,
        SNACK,
        DESSERT,
        BEVERAGE
    }

    @OneToMany(mappedBy = "recipe")
    @ToString.Exclude
    @JsonIgnoreProperties("recipe")
    Set<RecipeIngredient> recipeIngredients;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof  HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Recipe that = (Recipe) o;
        return getRecipeId() != null && Objects.equals(getRecipeId(), that.getRecipeId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
