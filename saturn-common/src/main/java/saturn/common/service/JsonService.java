package saturn.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Service;


@Service
public class JsonService extends ObjectMapper {
    private final FilterProvider filterProvider;

    public JsonService(){
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        registerModule(new JodaModule());
        filterProvider = new SimpleFilterProvider().addFilter("sessionIdHide",
                SimpleBeanPropertyFilter.serializeAllExcept("sessionId"));
        setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
    }


    public byte[] writeAsOuter(Object object) throws JsonProcessingException {
        return this.writer(filterProvider).writeValueAsBytes(object);
    }

    public String writeAsOuterString(Object object) throws JsonProcessingException {
        return new String(writeAsOuter(object));
    }

}
