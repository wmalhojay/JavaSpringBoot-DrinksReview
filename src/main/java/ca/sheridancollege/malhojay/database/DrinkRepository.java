package ca.sheridancollege.malhojay.database;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.malhojay.beans.Drink;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DrinkRepository {
    protected NamedParameterJdbcTemplate jdbc;

    public void addDrink(Drink drink) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO easy_drinks (name, main1, amount1, main2, amount2, directions) VALUES " +
                "(:name, :m1, :a1, :m2, :a2, :dir)";
        parameters.addValue("name", drink.getName());
        parameters.addValue("m1", drink.getMain1());
        parameters.addValue("a1", drink.getAmount1());
        parameters.addValue("m2", drink.getMain2());
        parameters.addValue("a2", drink.getAmount2());
        parameters.addValue("dir", drink.getDirections());
        jdbc.update(query, parameters);
    }

    public ArrayList<Drink> getDrinks() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks";
        ArrayList<Drink> drinks = (ArrayList<Drink>) jdbc.query(query, parameters,
                new BeanPropertyRowMapper<>(Drink.class));
        return drinks;
    }

    public Drink getDrinkById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks WHERE id=:id";
        parameters.addValue("id", id);
        ArrayList<Drink> drinks = (ArrayList<Drink>) jdbc.query(query, parameters,
                new BeanPropertyRowMapper<>(Drink.class));
        if (drinks.size() > 0)
            return drinks.get(0);
        return null;
    }
}
