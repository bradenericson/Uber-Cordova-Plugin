/**
 * Created by braden on 5/9/15.
 */
window.echo = function(str, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to echo.');
    }, "Echo", "echo", [str]);
};

window.uber = function(uberAppId, callback){
    cordova.exec(callback, function(err){
        //they don't have the uber app
    }, "Uber", "login", [uberAppId])
}