package com.person.erp.essay.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.person.erp.essay.constant.EssayConstant;
import com.person.erp.essay.entity.Essay;
import com.person.erp.essay.model.EssayDTO;

/**
 * <p>IEssayService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 19:28
 */
public interface IEssayService {

    /**
     * 新建文章
     * @author zhuwj
     * @since 2019/5/27 19:28
     * @param essayDTO
     * @return java.lang.Long
     */
    Long add(EssayDTO essayDTO);

    /**
     * 通过主键获取文章
     * @author zhuwj
     * @since 2019/5/27 19:57
     * @param id
     * @return com.person.erp.essay.model.EssayDTO
     */
    EssayDTO getById(Long id);

    /**
     * 更新文章
     * @author zhuwj
     * @since 2019/5/27 21:05
     * @param essayDTO
     * @return boolean
     */
    boolean update(EssayDTO essayDTO);

    /**
     * 批量删除文章
     * @author zhuwj
     * @since 2019/5/27 21:16
     * @param ids
     * @return boolean
     */
    boolean deletes(Long[] ids);

    /**
     * 条件分页文章列表
     * @author zhuwj
     * @since 2019/5/27 21:34
     * @param essayDTO
     * @param pager
     * @return com.github.pagehelper.PageInfo<com.person.erp.essay.entity.Essay>
     */
    PageInfo<Essay> findPage(EssayDTO essayDTO, Pager pager);

    /**
     * 上下架 草稿
     * @author zhuwj
     * @since 2019/5/27 23:06
     * @param ids
     * @param status
     * @return boolean
     */
    boolean updateStatus(Long[] ids, EssayConstant.Status status);

    /**
     * 修改文章排序
     * @author zhuwj
     * @since 2019/5/27 23:12
     * @param ids
     * @param queueNum
     * @return boolean
     */
    boolean updateQueueNum(Long[] ids, Integer queueNum);
}
