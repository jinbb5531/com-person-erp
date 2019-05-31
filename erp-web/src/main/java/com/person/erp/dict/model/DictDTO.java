package com.person.erp.dict.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Single;
import com.person.erp.common.valid.Update;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * <p>DictDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/21 9:21
 */
@Data
public class DictDTO {

    @NotBlank(groups = {Single.class, Update.class}, message = "[id] 字典主键不能为空")
    private String id;

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

    @NotBlank(message = "[typeName] 字典类型不能空")
    private String typeName;

    /**
     * 标识; 0：可以操作；1：不能操作
     */
    private Integer flag;

    @NotEmpty(groups = Delete.class, message = "[ids] 字典主键数组不能为空")
    private String[] ids;
}
