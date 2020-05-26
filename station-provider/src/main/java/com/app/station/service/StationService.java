package com.app.station.service;

import app.generator.modol.Station;
import app.generator.response.RpcResult;

public interface StationService {
    public RpcResult createStation(Station station);

    public RpcResult searchStation(Station station);

    public RpcResult searchStationByBlurry(String key);
}
