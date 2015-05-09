#import "Echo.h"
    #import <Cordova/CDV.h>

    @implementation Echo

    - (void)echo:(CDVInvokedUrlCommand*)command
    {       
    
           CDVPluginResult* pluginResult = nil;
           NSString* echo = [command.arguments objectAtIndex:0];
    
           UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Received Message" 
                                                    message:echo 
                                                    delegate:self 
                                                    cancelButtonTitle:@"OK" 
                                                    otherButtonTitles:nil];
           [alert show];
    
           if (echo != nil && [echo length] > 0) {
               pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
           } else {
               pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
           }
    
           [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }

    @end