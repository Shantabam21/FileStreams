/**
 * author - Askar Bolot
 */

import java.io.Serializable;
import java.util.Objects;


public class Product implements Serializable {
    private String IDNum;
    private String name;
    private String description;
    private double cost;

    public Product(String IDNum, String name, String description, double cost) {
        this.IDNum = IDNum;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    /**
     * creates a CSV data string that encodes all the data for this object instance
     * @return CSV data string containing ID number, first name, last name, title, and year of birth
     */
    public String toCVS() {
        return IDNum + ", " + name + ", " + description + ", " + cost;
    }

    /**
     * creates a JSON data string that encodes all the data for this object instance
     * @return JSON data string containing ID number, first name, last name, title, and year of birth
     */
    public String toJSON() {
        return "{" +
                "\"IDNum\":\"" + IDNum + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"description\":\"" + description + "\"," +
                "\"cost\":" + cost + "," +
                "}";
    }
    /**
     * creates an XML data string that encodes all the data for this object instance
     * @return XML data string containing ID number, first name, last name, title, and year of birth
     */
    public String toXML() {
        String retString = "<Product>";
        retString += "<IDNum>" + IDNum + "</IDNum>";
        retString += "<name>" + name + "</name>";
        retString += "<description>" + description + "</description>";
        retString += "<cost>" + cost + "</cost></Product>";
        return retString;
    }

    @Override
    public String toString() {
        return IDNum + ", " + name + ", " + description  + ", " + cost;
    }

    /**
     * Acceses the instance variable IDNum
     * @return IDNum instance variable (a string)
     */
    public String getIDNum() {
        return IDNum;
    }

    /**
     * Sets the instance variable IDNum to that of the parameter
     * @param IDNum - the ID number to assign to this object
     */
    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    /**
     * Acceses the instance variable firstName
     * @return firstName instance variable (a string)
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the instance variable name to that of the parameter
     * @param name - the name to assign to this object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Acceses the instance variable description
     * @return description instance variable (a string)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the instance variable description to that of the parameter
     * @param description - the description to assign to this object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Acceses the instance variable cost
     * @return cost instance variable (a double)
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the instance variable cost to that of the parameter
     * @param cost - the cost to assign to this object
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(cost, product.cost) == 0 && Objects.equals(IDNum, product.IDNum) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDNum, name, description, cost);
    }
}
