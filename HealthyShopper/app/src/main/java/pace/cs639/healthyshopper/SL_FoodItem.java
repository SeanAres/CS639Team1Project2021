package pace.cs639.healthyshopper;

import android.os.Parcel;
import android.os.Parcelable;

public class SL_FoodItem {

        private String Name;
        private int Qty;
        private String Cal;

    SL_FoodItem(String name, int qty, String cal){
            this.Name = name;
            this.Qty = qty;
            this.Cal = cal;
        }

    protected SL_FoodItem(Parcel in) {
        Name = in.readString();
        Qty = in.readInt();
        Cal = in.readString();
    }

        public String getName() {
            return Name;
        }

        public int getQty() {
            return Qty;
        }

        public String getCal() {return Cal;}

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setQty(int qty) {
            this.Qty = qty;
        }

        public void setCal(String Cal) { this.Cal = Cal; }
}
