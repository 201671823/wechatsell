package com.shawn.wechatsell.Controller;

/**
 * @Author: zxx
 * @Date: 2018/12/13 17:12
 * @Version 1.0
 */

import com.shawn.wechatsell.ViewObject.ProductInfoVO;
import com.shawn.wechatsell.ViewObject.ProductVO;
import com.shawn.wechatsell.ViewObject.ResultVO;
import com.shawn.wechatsell.dataObject.ProductCategory;
import com.shawn.wechatsell.dataObject.ProductInfo;
import com.shawn.wechatsell.service.ProductCategoryService;
import com.shawn.wechatsell.service.ProductInfoService;
import com.shawn.wechatsell.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 返回的是json格式，要添加@RestController
 * 给一个Url的前缀"/buyer/product"
 * 比如API中 GET /sell/buyer/product/list
 * sell:project name, 需要在application.yml文件中配置 Server：context-path:/sell
 * RequstMapping:/buyer/product
 *
 *
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * Get请求 /list
     * 添加 @GetMapping("/list")
     */
    @GetMapping("/list")
    public ResultVO list(){


        /**
         * 1、查询所有的上架商品：用到的类：ProductInfoService
         * 2、查询类目：一次性查询，不能查查询出10个商品，再进行10次循环去查询类目
         *  为了性能的提升一定要一次性查询。
         *  用到：ProductCategoryService
         * 3、数据拼装
         */

        //1
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2
        List<Integer> categoryTypeList;
        /**
         * List<ProductCategory> productCategoryList = productCategoryService.findAll();
         * 首先给了一个类型的list
         *
         */
        // categorytypeList 需要从所有的商品列表中进行抽取
        /**
         * 两种方法：
         * 1、传统的方法, 使用for in循环对productInfoList进行 categoryType的抽取
         *
         * for (ProductInfo productInfo : productInfoList){
         categoryTypeList.add(productInfo.getCategoryType());
         }
         * 2、java8 lambda表达式
         * 通过使用stream的map方法对数据进行筛选，并通过collect()输出集合
         */
        categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3：既有商品类目，又有商品详情，先遍历哪一个？

        /**
         * 必然是先遍历类目，因为类目下面又有详情list
         */
        //3.1遍历类目
        /**
         * 类目是不会重复的，这个要注意，因为数据库中定义了
         */
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            //需要后面再进行设置
            //productVO.setProductInfoVOList();

            //在这里要定义一个List<ProductInfoVO>
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            //在内部遍历商品详情
            for (ProductInfo productInfo : productInfoList){
                /**
                 * 相同类目的商品要输出在一个列表中
                 * 所以加一个if判断
                 */
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    /**
                     * productInfoVO.setProductDescription();
                     * 这边要进行五个属性的赋值，代码不够优雅，
                     * 可以使用BeanUtils这个类进行对象之间的数据拷贝
                     * 要确保拷贝的对象有相同的属性，否则是需要手动处理
                     *
                     */
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            //还需要加一个ProductVO的list
            productVOList.add(productVO);

        }

        //4、填入resultVO对象当中去
        //下面是一个正常的写法，但是有些麻烦，考虑封装一下

/*        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");*/
/*        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO(); */
        /**
         * 下面两个方法都需要传入对象列表List<>
         *
         *//*
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productVO));*/

        /*resultVO.setData(productVOList);
        return resultVO;*/
        return ResultVOUtil.success(productVOList);
    }

}
