package pace.cs639.healthyshopper;

public class Pantry_Item {

    private String name;
    private String nutrition;
    private int max;
    private int total;


    Pantry_Item(){
        this.name = "default";
        this.total = 0;
        this.max = 5;
        this.nutrition = "default";
    }

    Pantry_Item(String name, String nutrition, int max, int total){
        this.name = name;
        this.nutrition = nutrition;
        this.max = max;
        this.total = total;
    }

    public String getName() {
        return name;
    }
    public String getNutrition() {
        return nutrition;
    }
    public int getMax() {
        return max;
    }
    public int getTotal() {
        return total;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void  setNutrition(String nutrition){
       this.nutrition = nutrition;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}