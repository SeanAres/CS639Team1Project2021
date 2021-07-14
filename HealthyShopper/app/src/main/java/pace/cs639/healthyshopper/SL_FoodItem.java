package pace.cs639.healthyshopper;

import android.os.Parcel;
import android.os.Parcelable;

public class SL_FoodItem {

        private String Name;
        private int Qty;
        private int Max;

        private String Cal = "0";

    SL_FoodItem(){

    }
    SL_FoodItem(String name, int qty, int max, String cal){
            this.Name = name;
            this.Qty = qty;
            this.Cal = cal;
            this.Max = max;
        }

    protected SL_FoodItem(Parcel in) {
        Name = in.readString();
        Qty = in.readInt();
        Cal = in.readString();
        Max = in.readInt();
    }

        public String getName() {
            return Name;
        }

        public int getQty() {
            return Qty;
        }


        public String getCal() {return Cal;}

        public int getMax() {return Max;}

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setQty(int qty) {
            this.Qty = qty;
        }

        public void setCal(String cal) {
        this.Cal = cal;
    }

        public void setMax(int max) { this.Max = max; }


}
