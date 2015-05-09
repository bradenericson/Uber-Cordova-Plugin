window.uber = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "Uber", "requestWithUber", [str]);
};