package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() throws IOException {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            System.out.println(recipes);
            if (recipes == null) {
                System.out.println("No recipes available.");
            } else {
                
                System.out.println("Recipes:");
                for (Recipe recipe : recipes) {
                    System.out.println("-----------------------------------");
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.print("Main Ingredients: ");
                    // System.out.println(recipe.getIngredients().toString());
                    for (Ingredient ing: recipe.getIngredients()) {
                        System.out.println(ing.getName());
                    }
                    System.out.println("-----------------------------------");
                    
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
    }

    private void addNewRecipe() throws IOException {
        ArrayList<Ingredient> newIngredients = new ArrayList<>();
        System.out.println();
        System.out.println("Adding a new recipe.");
        System.out.print("Enter recipe name: ");
        String name = reader.readLine();
        System.out.println("Enter ingredients (type 'done' when finished):");

        while (true) {
            System.out.print("Ingredient: ");
            String ingredient = reader.readLine();
            newIngredients.add(new Ingredient(ingredient));

            if(ingredient.equals("done")) {
                System.out.println("successfully.");
                break;
            }
            dataHandler.writeData(new Recipe(name, newIngredients));
        }
    }
}
