# uber-cordova-plugin
Uber plugin for cordova/phonegap

## supported platforms
* Android
* iOS

## usage
```javascript

 var uberData = {
    toLatitude: "37.802374",
    toLongitude: "-122.405818",
    fromLatitude: "37.775818",
    fromLongitude: "-122.418028",
    productId: "a1111c8c-c720-46c3-8534-2fcdd730040d" // [optional]
};

window.uber(uberData, function(error) {
    console.log("Uber error: ", error);
});

```
