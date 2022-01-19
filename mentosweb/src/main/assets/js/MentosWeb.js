var mentosWeb = {};
mentosWeb.os = {};
mentosWeb.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
mentosWeb.os.isAndroid = !mentosWeb.os.isIOS;
mentosWeb.callbacks = {}

mentosWeb.invokeNativeAction = function(commandName, parameters){
    console.log("MentosWeb invokeNativeAction")
    var request = {};
    // 为了保持ios和android的一致性，所以将请求封装成一个String
    request.name = commandName;
    request.param = parameters;
    if(window.mentosWeb.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        //此处调用实现调用native方法，注意"MentosJsBridge"和"invokeNativeAction"要和native保持一致才行
        window.MentosJsBridge.invokeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.MentosJsBridge.postMessage(JSON.stringify(request))
    }
}

mentosWeb.invokeNativeActionWithCallback = function(commandName, parameters, callback) {
    var callbackId = "nativeToJs_callbackId_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    mentosWeb.callbacks[callbackId] = callback;

    var request = {};
    request.name = commandName;
    request.param = parameters;
    request.param.callbackId = callbackId;
    if(window.mentosWeb.os.isAndroid){
        window.MentosJsBridge.invokeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.MentosJsBridge.postMessage(JSON.stringify(request))
    }
}

mentosWeb.callback = function (callbackId, response) {
   var callbackObject = mentosWeb.callbacks[callbackId];
   if (callbackObject !== undefined){
       var ret = callbackObject(response);
       if(ret === false){
           return
       }
       delete mentosWeb.callbacks[callbackId];
   }
}