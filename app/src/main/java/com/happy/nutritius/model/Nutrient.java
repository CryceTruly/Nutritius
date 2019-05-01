package com.happy.nutritius.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Nutrient implements Parcelable {
    private int id;
    private String name,description,nutrients,agegroup;

    public Nutrient() {
    }

    public Nutrient(int id, String name, String description, String nutrients,String agegroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.agegroup=agegroup;
        this.nutrients = nutrients;
    }

    protected Nutrient(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        nutrients = in.readString();
        agegroup=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(nutrients);
        dest.writeString(agegroup);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Nutrient> CREATOR = new Creator<Nutrient>() {
        @Override
        public Nutrient createFromParcel(Parcel in) {
            return new Nutrient(in);
        }

        @Override
        public Nutrient[] newArray(int size) {
            return new Nutrient[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgegroup() {
        return agegroup;
    }

    public void setAgegroup(String agegroup) {
        this.agegroup = agegroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNutrients() {
        return nutrients;
    }

    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", nutrients='" + nutrients + '\'' +
                ", agegroup='" + agegroup + '\'' +
                '}';
    }
}
