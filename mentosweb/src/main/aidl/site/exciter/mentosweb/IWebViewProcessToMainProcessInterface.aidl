package site.exciter.mentosweb;
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface;

interface IWebViewProcessToMainProcessInterface {
    void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainprocessToWebViewProcessInterface callback);
}
