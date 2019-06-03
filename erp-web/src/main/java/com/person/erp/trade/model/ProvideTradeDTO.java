package com.person.erp.trade.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>ProvideTradeDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/6/3 18:33
 */
@Data
public class ProvideTradeDTO {

    @NotEmpty(groups = {Delete.class}, message = "[ids] 主键数组不能为空")
    private Long[] ids;

    @NotNull(groups = {Update.class}, message = "[id] 供应商主键不能为空")
    private Long id;

    @NotBlank(message = "[name] 供应商名称不能为空")
    private String name;

    @NotBlank(message = "[contactPhone] 联系电话不能为空")
    private String contactPhone;

    private String contactAddress;

    private String mainBusiness;

    private String remark;

}
