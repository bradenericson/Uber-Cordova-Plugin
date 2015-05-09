#import "Uber.h"
    #import <Cordova/CDV.h>

    @implementation Uber

    - (void)requestWithUber:(CDVInvokedUrlCommand*)command
    {       
           CDVPluginResult* pluginResult = nil;
           
          //[NSJSONSerialization JSONObjectWithData:jsonData options:kNilOptions error:&error];
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Progress"
                                                        message:@"About to create payload"
                                                       delegate:self
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles:nil];
        [alert show];
        
        NSDictionary *payloadDictionary = [command.arguments objectAtIndex:0];
        
            if ([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"uber://"]]) {
                // Do something awesome - the app is installed! Launch App.
                
                //The URL for NSURL will contain additional parameters with predetermined address from and to
                
                NSString *toLatitude = payloadDictionary[@"toLatitude"];
                NSString *toLongitude = payloadDictionary[@"toLongitude"];
                NSString *fromLatitude;
                NSString *fromLongitude;
                
                NSString *queryString = [NSString stringWithFormat:@"uber://?action=setPickup&dropoff[latitude]=%@&dropoff[longitude]=%@", 
                                                    toLatitude, 
                                                    toLongitude];
                
                if (payloadDictionary[@"fromLatitude" ] != nil && payloadDictionary[@"fromLatitude" ] != nil) {
                    queryString = [queryString stringByAppendingString:@""];
                }
                
                if (payloadDictionary[@"fromLatitude" ] != nil) {
                    fromLatitude = payloadDictionary[@"toLatitude"];
                }
                
                if (payloadDictionary[@"fromLatitude" ] != nil) {
                    fromLongitude = payloadDictionary[@"fromLongitude"];
                }
                
                [[UIApplication sharedApplication] openURL:[NSURL URLWithString:queryString]];
            }
            else {
                // No Uber app! Open Mobile Website.
                UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Uber Problem..." 
                                        message:@"Looks like you've got an Uber problem. Uber app could not be found!"
                                        delegate:self 
                                        cancelButtonTitle:@"OK" 
                                        otherButtonTitles:nil];
                [alert show];
                
            }
    
           if (payloadDictionary != nil) {
               pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
           } else {
               pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
               
               UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error"
                                                               message:@"Found an error in the payload. CDVCommandStatus_ERROR!"
                                                              delegate:self
                                                     cancelButtonTitle:@"OK"
                                                     otherButtonTitles:nil];
               [alert show];
           }
    
           [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }

    - (void)getAuthenticationToken:(CDVInvokedUrlCommand*)command
    {
        
    }
    @end