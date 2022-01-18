// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package site.exciter.mentosweb;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackname, String response);
}
