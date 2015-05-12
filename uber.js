window.uber = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback(err);
    }, "Uber", "requestWithUber", [str]);
};