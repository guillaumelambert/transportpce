/*
 * Copyright (C) 2019 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.restconf;

import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import org.onap.ccsdk.features.sdnr.wt.odlclient.data.OdlObjectMapperXml;
import org.onap.ccsdk.features.sdnr.wt.odlclient.http.BaseHTTPClient;
import org.onap.ccsdk.features.sdnr.wt.odlclient.http.BaseHTTPResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FutureRestRequest<T> implements ListenableFuture<Optional<T>> {

    private static final Logger LOG = LoggerFactory.getLogger(FutureRestRequest.class);

    public static <T> FluentFuture<Optional<T>> createFutureRequest(BaseHTTPClient client, String uri,
            String method, String data, Map<String, String> headers, Class<T> clazz) {
        return FluentFuture.from(new FutureRestRequest<T>(client, uri, method, data, headers, clazz));
    }

    private final BaseHTTPClient client;
    private final String uri;
    private final String method;
    private final String data;
    private final Map<String, String> headers;
    private final Class<T> clazz;
    private boolean isDone;
    private boolean isCancelled;

    public FutureRestRequest(BaseHTTPClient client, String uri, String method, String data,
            Map<String, String> headers, Class<T> clazz) {
        this.client = client;
        this.uri = uri;
        this.method = method;
        this.data = data;
        this.headers = headers;
        this.clazz = clazz;
        this.isDone = false;
        this.isCancelled = false;
    }

    @Override
    public boolean cancel(boolean arg0) {
        this.isCancelled = arg0;
        return this.isCancelled;
    }

    @Override
    public Optional<T> get() throws InterruptedException, ExecutionException {
        BaseHTTPResponse response;
        try {
            response = this.client.sendRequest(this.uri, this.method, this.data, this.headers,
                    Integer.MAX_VALUE);
            if (response.isSuccess()) {
                LOG.debug("request to {}", uri);
                LOG.debug("response({})" ,response.code);
                LOG.trace(":{}", response.body);
                OdlObjectMapperXml mapper = new OdlObjectMapperXml();
                return Optional.ofNullable(mapper.readValue(response.body, this.clazz));

            }
        } catch (IOException e) {
            LOG.warn("problem requesting data: {}", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> get(long arg0, TimeUnit arg1)
            throws InterruptedException, ExecutionException, TimeoutException {
        BaseHTTPResponse response;
        try {
            long timeoutMillis = arg1.toMillis(arg0);
            response = this.client.sendRequest(this.uri, this.method, this.data, this.headers,
                    new AtomicLong(timeoutMillis).intValue());
            if (response.isSuccess()) {
                LOG.debug("request to {}", uri);
                LOG.debug("response({}):{}", response.code, response.body);
                OdlObjectMapperXml mapper = new OdlObjectMapperXml();
                return Optional.ofNullable(mapper.readValue(response.body, this.clazz));

            }
        } catch (IOException e) {
            LOG.warn("problem requesting data: ", e);
        }
        this.isDone = true;
        return Optional.empty();
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {

    }

}
