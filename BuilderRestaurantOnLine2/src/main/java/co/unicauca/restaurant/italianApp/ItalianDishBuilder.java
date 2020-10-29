/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.restaurant.italianApp;

import co.unicauca.restaurant.accces.IProductRepository;
import co.unicauca.restaurant.accces.ProductRepositoryImplArrays;
import co.unicauca.restaurant.domain.Product;
import co.unicauca.restaurant.domain.Size;
import co.unicauca.restaurant.services.DishBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jefferson Eduardo Campo
 */
public class ItalianDishBuilder extends DishBuilder {
    IProductRepository myRepository;
    ItalianDish myItalianDish;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Product> allProducts;
    List<Product>  bases;
    List<Product>  options;
    
    private Product read(List<Product> myProducts, String foodPart) {
    String input = "";
    try {
        int i =1;
        System.out.println("Seleccione una "+foodPart);
       for (Product each: myProducts){
           System.out.println(""+i+". "+ each.getName() + ":"+each.getPrice());
           i++;
       }   
      System.out.println("Ingrese el código de la "+ foodPart);
      input = br.readLine();
    } catch (IOException e) {
    }
    return myProducts.get(Integer.parseInt(input)-1);
  }
  
  private String read(String message) {
    String input = "";
    try {
      System.out.println(message);
      input = br.readLine();
    } catch (IOException e) {
    }
    return input;
  }
    @Override
    public DishBuilder init() {
      myRepository = new ProductRepositoryImplArrays();
      myDish = new ItalianDish(0.0);
      myItalianDish = (ItalianDish) myDish;
      //Obtenemos todos los productos
      allProducts = myRepository.findAll();
      // Obtenemos las bases y las opciones
      bases = new ArrayList<Product>();
      options = new ArrayList<Product>();
      for(Product each: allProducts){
          if(each.getId()<4)
              bases.add(each);
          else
              options.add(each);
      }
      return this ;
    }

    @Override
    public DishBuilder setCore() {
        myItalianDish.setBase(read(bases, "Base"));
        return this;
    }

    @Override
    public boolean addPart() {
        myItalianDish.addOption(read(options, "Opcion"));
        return ("S".equals(read("Presione S para más opciones")));
     }

    @Override
    public DishBuilder setSize() {
      String tamano = read("Presione la letra correspondiente para el tamaño Personal(P), Doble (D), Familiar (F)");
      if(tamano.equals("F")) myItalianDish.setSize(Size.FAMILY);
      if(tamano.equals("D")) myItalianDish.setSize(Size.DOUBLE);
      if(tamano.equals("P")) myItalianDish.setSize(Size.PERSONAL);
      return this;
    }
    
}
