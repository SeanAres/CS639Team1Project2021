package pace.cs639.healthyshopper;

public class SL_FoodItem {

        private String Name;
        private int Qty;

        SL_FoodItem(){
            this.Name = "empty";
            this.Qty = 0;
        }

        SL_FoodItem(String name, int qty){
            this.Name = name;
            this.Qty = qty;
        }

        public String getName() {
            return Name;
        }

        public int getQty() {
            return Qty;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setQty(int qty) {
            this.Qty = qty;
        }
}
