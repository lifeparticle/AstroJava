# AstroJava [development in progress]

AstroJava is a Java library for Android which provides access to Sunrise, Sunset, Moonrise, Moonset information.

## Dependencies
None
## Installation
Todo

## Documentation

```java
/*
* Latitude range from [-90, 90] 
* Longitude range from [-180, 180]
*/
// Sydney, Australia
double latitude = -33.92;
double longitude = 151.1852;
```
### Sun 
To access today's data in Local time zone
```java
Sun sun = new Sun();
// for a specific city
sun.getSunrise ("Sydney", "Australia", "LOCAL"); // 06:27:53 AM AEST
sun.getSunset ("Sydney", "Australia", "LOCAL");  // 05:17:08 PM AEST

// for latitude and longitude
sun.getSunrise(latitude, longitude, "LOCAL"); // 06:27:53 AM AEST
sun.getSunset(latitude, longitude, "LOCAL");  // 05:17:08 PM AEST
```
To access data for a specific date
```java
// format for date is dd-MM-yyyy
sun.getSunrise ("Sydney", "Australia", "29-04-2019", "LOCAL"); // 06:27:53 AM AEST
sun.getSunset ("Sydney", "Australia", "29-04-2019", "LOCAL");  // 05:17:08 PM AEST

sun.getSunrise (latitude, longitude, "29-04-2019", "LOCAL"); // 06:27:53 AM AEST
sun.getSunset (latitude, longitude, "29-04-2019", "LOCAL");  // 05:17:08 PM AEST
```

To access today's data in **UTC** (milliseconds)
```java
sun.getSunrise ("Dhaka", "Bangladesh", "29-04-2019", "UTC"); // 84381667
sun.getSunset ("Dhaka", "Bangladesh", "29-04-2019", "UTC");  // 44735390 
```

**Polar night and Polar day**

*Please keep in mind that whenever there is an event like no sunset or
no sunrise the associated sunrise or sunset information represents
the last time the event occurred, so it does'nt necessarily mean that
sunrise happened the day when the no sunrise happened. It may happen a few days
back at that specific time. Currently, there is no way to tell that.*
```java
sun.getSunrise("Kiruna", "Sweden", "29-06-2019", "LOCAL"); // 10:00:00 AM AEST
sun.getSunset("Kiruna", "Sweden", "29-06-2019", "LOCAL");  // no sunset

sun.getSunrise("Kiruna", "Sweden", "29-12-2019", "LOCAL"); // no sunrise
sun.getSunset("Kiruna", "Sweden", "29-12-2019", "LOCAL");  // 10:00:00 AM AEST
```

### Moon
To access today's data in Local time zone
```java
Moon moon = new Moon();
moon.getSunrise(latitude, longitude);
moon.getSunset(latitude, longitude);
```

### Helper Methods
```java
/* 
* get the the list of cities for
* latitude and longitude data
*/

```
```java
Cities cities = new Cities();
ArrayList<String> cityList = cities.getCities();
print(cityList.toString());
```


**Errors**
```java
/*
* `invalid latitude or longitude` if either value of latitude or longitude is out of range
*/
sun.getSunrise(-92, 151.1852, "LOCAL"); // invalid latitude or longitude

/* If you're getting error like 'city not found' that means it's not in the city list
* either use latitude and longitude or create an issue
*/
sun.getSunrise ("Avalon", "Earth", "29-04-2019", "LOCAL"); // city not found

/* If you're getting error like 'unparseable date' that means you're not
* providing the date in a correct format
*/
sun.getSunrise ("Sydney", "Australia", "29/04/2019", "LOCAL"); // unparseable date
```

## Bug Reports and Feature Requests
Please create an issue with as much information you can. Thank you.

## Author
Mahbub Zaman (https://mahbub.ninja)

## License
MIT License