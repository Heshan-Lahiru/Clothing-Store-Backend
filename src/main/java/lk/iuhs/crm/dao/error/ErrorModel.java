package lk.iuhs.crm.dao.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {

    String msgerror;
    String status;
}
