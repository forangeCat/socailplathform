package com.app.station.service;

import app.generator.modol.Station;
import app.generator.response.RpcResult;

public interface StationService {
    RpcResult createStation(Station station);

    RpcResult searchStation(Station station);

    public RpcResult searchStationByBlurry(String key);
}
