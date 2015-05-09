#import "Echo.h"
    #import <Cordova/CDV.h>

    @implementation Echo

    - (void)echo:(CDVInvokedUrlCommand*)command
    {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Test Message" 
                                                  message:@"This is a test"
                                                 delegate:nil
                                        cancelButtonTitle:@"OK" 
                                        otherButtonTitles:nil];
        [alert show];
        [alert release];
    }

    @end