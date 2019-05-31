package com.person.erp.system.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>ChildSystemDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/30 23:21
 */
@Data
public class ChildSystemDTO {

    @NotNull(groups = {Update.class, Delete.class}, message = "[id] 主键不能为空")
    private Long id;

    @NotBlank(message = "[name] 名称不能为空")
    private String name;

    @Min(value = 0, message = "[status] 状态值最小为0")
    @Max(value = 1, message = "[status] 状态值最大为1")
    private Integer status;

}
