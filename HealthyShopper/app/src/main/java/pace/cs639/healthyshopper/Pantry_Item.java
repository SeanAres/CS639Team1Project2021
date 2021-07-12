package pace.cs639.healthyshopper;

public class Pantry_Item {

    private String name;
    private String nutrition;
    private String max;
    private String total;


    Pantry_Item(){
        this.name = "default";
        this.total = "0";
        this.max = "5";
        this.nutrition = "default";
    }

    Pantry_Item(String name, String nutrition, String max, String total){
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
    public String getMax() {
        return max;
    }
    public String getTotal() {
        return total;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void  setNutrition(String nutrition){
       this.nutrition = nutrition;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}