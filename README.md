# AstroJava [development in progress]

AstroJava is a Java library for Android which provides access to Sunrise, Sunset, Moonrise, Moonset information.

```java
// Sydney, Australia
double latitude = -33.868;
double longitude = 151.21;
```

To access today's data for Sun (UTC in milliseconds)
```java
Sun sun = new Sun();
sun.getSunrise ("Sydney", "Australia", "28-04-2019", "LOCAL");
sun.getSunset ("Sydney", "Australia", "28-04-2019", "LOCAL");
```

To access today's data for Sun in Local time zone
```java
Sun sun = new Sun();
sun.getSunrise(latitude, longitude, "LOCAL");
sun.getSunset(latitude, longitude, "LOCAL");
```

To access today's data for Moon
```java
Moon moon = new Moon();
moon.getSunrise(latitude, longitude);
moon.getSunset(latitude, longitude);
```

To get data for a specific date
```java
// format for date is dd-MM-yyyy
sun.getSunrise(latitude, longitude, "20-10-1995");
```

To get data for a specific city
```java
sun.getSunrise("Melbourne", "Australia");