package com.app.gramophone.service;

import app.generator.modol.Station;
import app.generator.response.RpcResult;

public interface GramophoneService {
    public RpcResult createGramophone(Station station);

    public RpcResult searchGpOrderByTime();

    public RpcResult searchGpOrderByCount();

    public RpcResult addStart(String id);
}
