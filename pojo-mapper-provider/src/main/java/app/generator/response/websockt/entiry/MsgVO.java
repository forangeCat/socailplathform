package app.generator.response.websockt.entiry;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MsgVO {

    private String userId;

    private String msg;

    private int count;

}
