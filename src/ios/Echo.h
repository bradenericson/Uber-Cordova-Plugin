    #import <Cordova/CDV.h>

    @interface Echo : CDVPlugin

    - (void)requestWithUber:(CDVInvokedUrlCommand*)command;
    - (void)getAuthenticationToken:(CDVInvokedUrlCommand*)command;
    @end