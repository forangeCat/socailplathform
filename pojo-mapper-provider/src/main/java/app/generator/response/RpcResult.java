package app.generator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResult implements Serializable {
    private Integer code = 200;
    private String message = "OK";
    private Map<String, Object> data;

    public static RpcResult ok() {
        return new RpcResultBuilder()
                        .code(200)
                        .message("ok")
                        .build();
    }
    public static RpcResult not() {
        return new RpcResultBuilder()
                .code(400)
                .message("not found")
                .build();
    }


}
