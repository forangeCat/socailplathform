package controller;

import app.generator.response.RpcResult;
import com.app.gramophone.service.GramophoneService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gramophone")
public class GramophoneController {
    @Reference
    GramophoneService gramophoneService;

    @GetMapping("/search/{key}")
    public RpcResult searchGP(@PathVariable String key) {
        RpcResult rpcResult;
        if (key.equals("time")) {
            rpcResult = gramophoneService.searchGpOrderByTime();
            return rpcResult;
        } else if (key.equals("star")) {
            rpcResult = gramophoneService.searchGpOrderByCount();
            return rpcResult;
        } else return RpcResult.not();
    }

    @PutMapping("/star/{gpId}")
    public RpcResult star(@PathVariable String gpId) {
        RpcResult rpcResult = gramophoneService.addStart(gpId);
        return rpcResult;
    }
}
