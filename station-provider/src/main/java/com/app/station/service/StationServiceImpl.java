package com.app.station.service;

import app.generator.mapper.StationMapper;
import app.generator.modol.Station;
import app.generator.modol.StationExample;
import app.generator.response.RpcResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
@CacheConfig(cacheNames = {"station"},cacheManager = "redisCacheManager")
public class StationServiceImpl implements StationService {
    @Autowired
    StationMapper stationMapper;

    @Override
    @Cacheable(key = "#key")
    public RpcResult searchStationByBlurry(String key) {
        StationExample example = new StationExample();
        example.or()
                .andCategoryLike("%" + key + "%");
        example.or()
                .andStationIntroductionLike("%" + key + "%)");
        example.or()
                .andStationNameLike("%" + key + "%");
        List<Station> stations = stationMapper.selectByExample(example);
        if (stations.size() != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("stationList", stations);
            RpcResult ok = RpcResult.ok();
            ok.setData(map);
            return ok;
        } else {

            return RpcResult.not();
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(allEntries = true)
            }
    )
    public RpcResult createStation(Station station) {
        station.setCreateTime(new Date(System.currentTimeMillis()));
        int result = stationMapper.insert(station);
        if (result != 0) {
            return RpcResult.ok();
        }else {
            return RpcResult.not();
        }

    }

    @Override
    @Cacheable
    public RpcResult searchStation(Station station) {
        String category = station.getCategory();
        StationExample example = new StationExample();
        if (category.equals("推荐")) {
            //推荐算法，前三个人数排名，后三个
        }else {
            example.or().andCategoryEqualTo(category);
        }
        List<Station> list = stationMapper.selectByExample(example);
        if (list.size() != 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("stationList", list);
            RpcResult rpcResult = RpcResult.ok();
            rpcResult.setData(map);
            return rpcResult;
        }else {
            return RpcResult.not();
        }
    }


}
