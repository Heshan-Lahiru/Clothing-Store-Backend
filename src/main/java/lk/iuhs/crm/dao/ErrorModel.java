package lk.iuhs.crm.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {

    String msgerror;
    String status;
}