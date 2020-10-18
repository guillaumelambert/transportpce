package com.orange.onap.tpce.manager.common.util.mapping;

public interface Mapper<REQ, RESP> {

    REQ toRequest(RESP resp);
    RESP toResponse(REQ req);
}
