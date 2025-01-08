package com.gallery.websoket.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gallery.websoket.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/*
 * 接口返回对象
 *
 * @date 2021/6/8 16:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageR<M> extends R {

    /**
     * 数据对象
     **/
//    @Schema(description = "业务数据")
    private PageBean<M> data;


    /**
     * 业务处理成功， 封装分页数据， 仅返回必要参数
     **/
    public static <M> PageR<M> pages(IPage<M> iPage) {

        PageBean<M> innerPage = new PageBean<>();
        innerPage.setRecords(iPage.getRecords());  //记录明细
        innerPage.setTotal(iPage.getTotal()); //总条数
        innerPage.setCurrent(iPage.getCurrent()); //当前页码
        innerPage.setHasNext(iPage.getPages() > iPage.getCurrent()); //是否有下一页

        PageR result = new PageR();
        result.setData(innerPage);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMsg());

        return result;
    }


    @Data
//    @Schema
    public static class PageBean<M> {

        /**
         * 数据列表
         */
//        @Schema(description = "数据列表")
        private List<M> records;

        /**
         * 总数量
         */
//        @Schema(description = "总数量")
        private Long total;

        /**
         * 当前页码
         */
//        @Schema(description = "当前页码")
        private Long current;

        /**
         * 是否包含下一页， true:包含 ，false: 不包含
         */
//        @Schema(description = "是否包含下一页， true:包含 ，false: 不包含")
        private boolean hasNext;

    }
}