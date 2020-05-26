package app.generator.response.websockt.entiry;

import lombok.*;
import org.springframework.web.socket.WebSocketMessage;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MyWebSockeMessage<T> implements WebSocketMessage {
    private T payLoad;
    private int byteCount;
    private boolean last;
    @Override
    public T getPayload() {
        return payLoad;
    }

    @Override
    public int getPayloadLength() {
        return byteCount;
    }
    @Override
    public boolean isLast() {
        return last;
    }
}
