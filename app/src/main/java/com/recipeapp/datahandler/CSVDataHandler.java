package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }
    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public String getMode() {
        return "CSV";
    }

    // `recipes.csv`からレシピデータを読み込み、それをリスト形式で返します。
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Ingredient> ingredients;
        Ingredient ingredient;
        Recipe recipe;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String l;
            while ((l = reader.readLine()) != null) {

                ingredients = new ArrayList<>();
                String recipeName = l.split(",", 2)[0];
                String[] ingredientsNames = l.split("," , 2)[1].split(",");
                for (String n : ingredientsNames) {
                    ingredient = new Ingredient(n);
                    ingredients.add(ingredient);
                }
                recipe = new Recipe(recipeName, ingredients);
                recipes.add(recipe);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
        return recipes;
    };

    public void writeData(Recipe recipe) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            out.print(recipe.getName() + ",");
            for(int i = 0; i < recipe.getIngredients().size() - 1; i++) {
                out.print(recipe.getIngredients().get(i).getName() + ", ");
            }
            out.print(recipe.getIngredients().get(recipe.getIngredients().size() - 1).getName());

        }
    };
    
    public ArrayList<Recipe> searchData(String keyword) throws IOException{
        return null;
    }

    
}
