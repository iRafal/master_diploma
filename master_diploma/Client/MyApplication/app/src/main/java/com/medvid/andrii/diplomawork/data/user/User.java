package com.medvid.andrii.diplomawork.data.user;


import android.support.annotation.IntDef;

import com.medvid.andrii.diplomawork.data.ModelEntity;

import java.lang.annotation.Retention;

import static com.medvid.andrii.diplomawork.data.user.User.Gender.MAN;
import static com.medvid.andrii.diplomawork.data.user.User.Gender.WOMEN;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class User implements ModelEntity {


    @Retention(SOURCE)
    @IntDef({MAN, WOMEN})
    public @interface Gender {
        int MAN = 0;
        int WOMEN = 1;
    }

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private double age;
    private User.Gender gender; // 1
    private double growth; // 1.8
    private double weight; // 75
    private double bodyMassIndex; // 20

    public User(long id, String email, String firstName, String lastName, double age,
                Gender gender, double growth, double weight) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.growth = growth;
        this.weight = weight;
        setBodyMassIndex();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    /**
     * Calculates body mass index
     */
    public void setBodyMassIndex() {
        this.bodyMassIndex = weight / Math.pow(growth, 2);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", growth=" + growth +
                ", weight=" + weight +
                ", bodyMassIndex=" + bodyMassIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.age, age) != 0) return false;
        if (Double.compare(user.growth, growth) != 0) return false;
        if (Double.compare(user.weight, weight) != 0) return false;
        if (Double.compare(user.bodyMassIndex, bodyMassIndex) != 0) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
            return false;
        return gender != null ? gender.equals(user.gender) : user.gender == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        temp = Double.doubleToLongBits(age);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        temp = Double.doubleToLongBits(growth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bodyMassIndex);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
