package com.person.erp.identity.model;

import com.person.erp.common.valid.UserDelete;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>ListDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 14:14
 */
@Getter
@Setter
public class ListDTO {

    @NotEmpty(groups = { UserDelete.class }, message = "集合不能为空")
    @Valid
    private List<UserDTO> users;
}
