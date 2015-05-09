<<<<<<< HEAD:echo.js
window.echo = function(str, callback) {
    
    //str should be a JSON object with all of our key value pairs... 
    /*
        {
            toLatitude: 'toLat',
            toLongitude: 'toLong',
            fromLatitude: 'fromLat',
            fromLongitude: 'fromLong',
            productId: 'productId'
        }
    */
    
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "Echo", "requestWithUber", [str]);
=======
window.uber = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "Uber", "uber", [str]);
>>>>>>> a3cbf9d174b00172870f4fdb975c6704535c5c73:uber.js
};