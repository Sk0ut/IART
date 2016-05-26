package cityparser;

public class GeographicCoordinates {

    public static double EARTH_RADIUS = 6371000;

    /*
     * Distance between two coordinates based on the Haversine formula (http://www.movable-type.co.uk/scripts/gis-faq-5.1.html)
     */
    public static double distanceBetween(double latitude1, double longitude1, double latitude2, double longitude2) {
        double lat1Radians = Math.toRadians(latitude1);
        double lat2Radians = Math.toRadians(latitude2);

        double long1Radians = Math.toRadians(longitude1);
        double long2Radians = Math.toRadians(longitude2);

        double latDelta = lat2Radians - lat1Radians;
        double longDelta = long2Radians - long1Radians;

        double a = Math.sin(latDelta/2) * Math.sin(latDelta/2) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) *
                Math.sin(longDelta/2) * Math.sin(longDelta/2);

        return EARTH_RADIUS * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    }
}
