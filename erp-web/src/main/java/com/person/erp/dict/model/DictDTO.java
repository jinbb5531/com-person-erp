package com.person.erp.dict.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>DictDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/21 9:21
 */
@Data
public class DictDTO {

    @NotNull(groups = Update.class, message = "[id] 字典主键不能为空")
    @Min(groups = Update.class, value = 1, message = "[id] 字典主键最小值为1")
    private Long id;

    @NotBlank(message = "[dictName] 字典名称不能为空")
    private String dictName;

    /**
     * 状态；0为启用，1为禁用
     */
    @NotNull(message = "[status] 状态不能为空")
    @Min(value = 0, message = "[status] 状态值最小为0")
    @Max(value = 1, message = "[status] 状态值最大为1")
    private Integer status;

    private String iconPath;

    @NotNull(message = "[typeId] 字典类型主键不能空")
    @Min(value = 1, message = "[typeId] 字典类型主键最小值为1")
    private Long typeId;

    private String typeName;

    @NotEmpty(groups = Delete.class, message = "[ids] 字典主键数组不能为空")
    private Long[] ids;
}
