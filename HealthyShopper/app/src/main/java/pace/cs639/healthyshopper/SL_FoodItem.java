package pace.cs639.healthyshopper;

import android.os.Parcel;
import android.os.Parcelable;

public class SL_FoodItem {

        private String Name;
        private int Qty;

    SL_FoodItem(String name, int qty){
            this.Name = name;
            this.Qty = qty;
        }

    protected SL_FoodItem(Parcel in) {
        Name = in.readString();
        Qty = in.readInt();
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
