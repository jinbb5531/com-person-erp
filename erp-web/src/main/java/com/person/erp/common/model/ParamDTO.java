package com.person.erp.common.model;

import com.person.erp.common.valid.Delete;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>ParamDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/7/25 21:22
 */
@Data
public class ParamDTO {

    @NotEmpty(groups = {Delete.class}, message = "[ids] 不能为空")
    private Long[] ids;

}
