package co.unicauca.restaurant.orientalApp;
import co.unicauca.restaurant.services.DishBuilder;
import co.unicauca.restaurant.accces.IProductRepository;
import co.unicauca.restaurant.accces.ProductRepositoryImplArrays;
import co.unicauca.restaurant.domain.Product;
import co.unicauca.restaurant.domain.Size;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julio Hurtado, Libardo Pantoja
 * 
 */

public class OrientalDishBuilder extends DishBuilder {
  IProductRepository myRepository;
  OrientalDish myOrientalDish;
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
      myDish = new OrientalDish(0.0);
      myOrientalDish = (OrientalDish) myDish;
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
        myOrientalDish.setBase(read(bases, "Base"));
        return this;
    }

    @Override
    public boolean addPart() {
        myOrientalDish.addOption(read(options, "Opcion"));
        return ("S".equals(read("Presione S para más opciones")));
     
    }

    @Override
    public DishBuilder setSize() {
      String tamano = read("Presione la letra correspondiente para el tamaño Personal(P), Doble (D), Familiar (F)");
      if(tamano.equals("F")) myOrientalDish.setSize(Size.FAMILY);
      if(tamano.equals("D")) myOrientalDish.setSize(Size.DOUBLE);
      if(tamano.equals("P")) myOrientalDish.setSize(Size.PERSONAL);
      return this;
    }
}
