package zero.programmer.data.kendaraan.auth;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import zero.programmer.data.kendaraan.error.UnauthorizedException;

@Component
public class ApiKeyInterceptor implements WebRequestInterceptor{

    private final String X_API_KEY = "X-Api-Key-OksiJkdB938D9D@lUlrou";

    @Override
    public void preHandle(WebRequest request) throws Exception {
        
        String apiKey = request.getHeader("X-Api-Key");
        if (apiKey == null){
            throw new UnauthorizedException();
        }

        if (!apiKey.equals(X_API_KEY)){
            throw new UnauthorizedException();
        }

        // valid
        
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        // Nothing
        
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        // Nothing
        
    }
    
}
