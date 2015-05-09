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
};