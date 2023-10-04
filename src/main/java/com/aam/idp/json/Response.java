package com.aam.idp.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private String userIdentity;
    private boolean requireSOARegistration;
    private boolean isActiveInSOA;
    private boolean mfaEnabledInSOA;

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errorMessage")
    private String errorMessage;

    public Response() {
    }

    public Response(String userIdentity, boolean requireSOARegistration, boolean isActiveInSOA, boolean mfaEnabledInSOA) {
        this.userIdentity = userIdentity;
        this.requireSOARegistration = requireSOARegistration;
        this.isActiveInSOA = isActiveInSOA;
        this.mfaEnabledInSOA = mfaEnabledInSOA;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response( String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
