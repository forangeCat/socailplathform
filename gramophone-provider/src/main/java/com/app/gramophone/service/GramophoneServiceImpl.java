package com.app.gramophone.service;

import app.generator.mapper.GramoPhoneMapper;
import app.generator.modol.GramoPhone;
import app.generator.modol.GramoPhoneExample;
import app.generator.modol.Station;
import app.generator.response.RpcResult;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Service
@Component
@CacheConfig(cacheNames = {"gramophone"},cacheManager = "redisCacheManager")
public class GramophoneServiceImpl implements GramophoneService {
    @Autowired
    GramoPhoneMapper gramoPhoneMapper;

    @Override
    public RpcResult createGramophone(Station station) {
        return null;
    }

    @Override
    public RpcResult searchGpOrderByTime() {
        GramoPhoneExample example = new GramoPhoneExample();
        example.setOrderByClause("time");
        List<GramoPhone> list = gramoPhoneMapper.selectByExample(example);
        System.out.println(list);
        if (list.size() != 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("gramophone", list);
            RpcResult ok = RpcResult.ok();
            ok.setData(map);
            return ok;
        }else {
            return RpcResult.not();
        }
    }

    @Override
    public RpcResult searchGpOrderByCount() {
        GramoPhoneExample example = new GramoPhoneExample();
        example.setOrderByClause("star");
        List<GramoPhone> list = gramoPhoneMapper.selectByExample(example);
        System.out.println(list);
        if (list.size() != 0) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("gramophone", list);
            RpcResult ok = RpcResult.ok();
            ok.setData(map);
            return ok;
        }else {
            return RpcResult.not();
        }
    }

    @Override
    public RpcResult addStart(String id) {
        GramoPhoneExample example = new GramoPhoneExample();
        example.or()
                .andIdEqualTo(Integer.parseInt(id));
        GramoPhone phone = new GramoPhone();
        List<GramoPhone> gramoPhones = gramoPhoneMapper.selectByExample(example);
        if (gramoPhones.size() != 0) {
            int star;
            star = new GramoPhone().getStar();
            star++;
            phone.setStar(star);
        }
        int i = gramoPhoneMapper.updateByExample(phone, example);
        if (i != 0) {
            return RpcResult.ok();
        }else {
            return RpcResult.not();
        }
    }
}
