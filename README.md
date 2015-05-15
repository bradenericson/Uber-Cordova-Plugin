# uber-cordova-plugin
Apache Cordova/PhoneGap plugin for the Uber app.

This plugin provides a service for deep linking with the
Uber app from a convenient, easy to use JavaScript interface.
>We also recommend using this plugin in conjunction with our [angular module] (https://github.com/bradenericson/Uber-Angular-Module)

## how to install
```
cordova plugin add com.jabb.plugin.uber
```

## supported platforms
* Android
* iOS

## example usage
```javascript
var uberData = {
    clientId: "YOUR_CLIENT_ID",
    toLatitude: "37.802374", 
    toLongitude: "-122.405818",
    toAddress: "1 Telegraph Hill Blvd, San Francisco, CA 94133",
    toNickname: "Coit Tower",
    fromLatitude: "37.775818",
    fromLongitude: "-122.418028",
    fromNickname: "UberHQ",
    fromAddress: "1455 Market St, San Francisco, CA 94103",
    productId: "a1111c8c-c720-46c3-8534-2fcdd730040d"
};

window.uber(uberData, function(error) {
    console.log("Uber error: ", error);
});
```
## api
For detailed information about these fields, see the [Uber developers website] 
(https://developer.uber.com/v1/deep-linking/)

**clientId**
> Identifies the requesting application

**productId**
> Product to populate in pickup

**fromLatitude**
> Pickup latitude

**fromLongitude**
> Pickup longitude

**fromAddress**
> Fully qualified address of pickup

**fromNickname**
> Nickname of pickup location

**toLatitude**
> Dropoff latitude

**toLongitude**
> Dropoff longitude

**toAddress**
> Fully qualified address of dropoff

**toNickname**
> Nickname of dropoff location



## coming soon

* Redirect to [m.uber.com] (https://m.uber.com/) when the mobile app isn't found on the users device.
