package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Price")
    private float Price;

    public Product(){}
    public Product(String name, float price) {
        this.Name = name;
        this.Price = price;
    }

    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    public float getPrice() { return Price; }
    public void setPrice(int price) { this.Price = price; }
}
