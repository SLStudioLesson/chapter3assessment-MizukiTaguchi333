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
        Recipe recipe;
        Ingredient ingredient;
        ArrayList<Ingredient> ingredients;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = reader.readLine()) != null) {
                ingredients = new ArrayList<>();
                String[] lists = line.split("," ,2);
                String[] ing = lists[1].split(",");
            
                for (String s : ing) {
                    
                    ingredients.add(new Ingredient(s));
                }
                recipes.add(new Recipe(lists[0], ingredients));

            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        return recipes;
    };

    public void writeData(Recipe recipe) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            out.println(recipe.getName() + recipe.getIngredients());

        }
    };
    
    public ArrayList<Recipe> searchData(String keyword) throws IOException{
        return null;
    }

    
}
