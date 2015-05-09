    #import <Cordova/CDV.h>

    @interface Uber : CDVPlugin

    - (void)requestWithUber:(CDVInvokedUrlCommand*)command;
    - (void)getAuthenticationToken:(CDVInvokedUrlCommand*)command;
    @end