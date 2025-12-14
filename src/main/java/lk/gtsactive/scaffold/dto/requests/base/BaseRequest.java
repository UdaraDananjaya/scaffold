package lk.gtsactive.scaffold.dto.requests.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {
    private String requestId;
    private Long timestamp;
}
