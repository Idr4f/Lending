package co.com.ikitech.api.user.ikitech;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class IkiTechRestService<D, B> {

    protected String dtoName = "transferObject";

    protected String pathSuccess = "/";

    /**
     *
     * @param businessObject
     * @param , transform
     * @Return
     */

    protected ResponseEntity<Map<String, Object>> createResponseSuccess(B businessObject,
                                                                        Function<B, D> transform){

        Map<String, Object> response = new HashMap<>();

        response.put("Status", HttpStatus.CREATED.value());
        response.put("message", HttpStatus.CREATED);
        response.put("timestamp", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
        response.put(dtoName, transform.apply(businessObject));
        return ResponseEntity
                .created(URI.create(pathSuccess))
                .contentType(MediaType.valueOf((MediaType.APPLICATION_JSON_VALUE)))
                .body(response);
    }
}
