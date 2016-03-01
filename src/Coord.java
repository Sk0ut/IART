/**
 * Created by up201304205 on 01-03-2016.
 */

public class Coord {
    double latitude;
    double longitude;

    public Coord(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static double parseAngle(String angle){
        String integerPart = "";
        String fractionalPart = "";

        char i = 0;
        while(Character.isDigit(angle.charAt(i))){
            integerPart += angle.charAt(i);
            ++i;
        }
        ++i;
        while(i < angle.length()){
            fractionalPart += angle.charAt(i);
            ++i;
        }

        /* Minutes to decimals conversion */
        return Integer.parseInt(integerPart) + (Integer.parseInt(fractionalPart) / 60.0);
    }

}
