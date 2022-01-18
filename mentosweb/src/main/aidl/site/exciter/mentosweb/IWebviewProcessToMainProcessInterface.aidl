// IWebviewProcessToMainProcessInterface.aidl
package site.exciter.mentosweb;

// Declare any non-default types here with import statements
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface;

interface IWebviewProcessToMainProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainprocessToWebViewProcessInterface callback);
}
