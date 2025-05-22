package org.example;

import Controller.IngredientesController;
import model.Ingredientes;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IngredientesController ingredientesController = new IngredientesController();

        // Traer todos los ingredientes (filtro nulo)
        ArrayList<Ingredientes> listaIngredientes = ingredientesController.getIngredientes(null);

        if (listaIngredientes.isEmpty()) {
            System.out.println("No hay ingredientes en la base de datos.");
        } else {
            for (Ingredientes ingrediente : listaIngredientes) {
                System.out.println(ingrediente);
            }
        }
    }
}
