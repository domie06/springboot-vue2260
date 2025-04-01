package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.ShenqingEntity;
import com.service.ShenqingService;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.TaanweiEntity;

import com.service.TaanweiService;
import com.entity.view.TaanweiView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 摊位信息
 * 后端接口
 * @author
 * @email
 * @date 2021-05-07
*/
@RestController
@Controller
@RequestMapping("/taanwei")
public class TaanweiController {
    private static final Logger logger = LoggerFactory.getLogger(TaanweiController.class);

    @Autowired
    private TaanweiService taanweiService;

    @Autowired
    private ShenqingService shenqingService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = taanweiService.queryPage(params);

        //字典表数据转换
        List<TaanweiView> list =(List<TaanweiView>)page.getList();
        for(TaanweiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TaanweiEntity taanwei = taanweiService.selectById(id);
        if(taanwei !=null){
            //entity转view
            TaanweiView view = new TaanweiView();
            BeanUtils.copyProperties( taanwei , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody TaanweiEntity taanwei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,taanwei:{}",this.getClass().getName(),taanwei.toString());
        Wrapper<TaanweiEntity> queryWrapper = new EntityWrapper<TaanweiEntity>()
            .eq("taanwei_name", taanwei.getTaanweiName())
            .eq("taanwei_types", taanwei.getTaanweiTypes())
            .eq("zhuangtai_types", taanwei.getZhuangtaiTypes())
            .eq("quyu_types", taanwei.getQuyuTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TaanweiEntity taanweiEntity = taanweiService.selectOne(queryWrapper);
        if(taanweiEntity==null){
            taanwei.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      taanwei.set
        //  }
            taanweiService.insert(taanwei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TaanweiEntity taanwei, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,taanwei:{}",this.getClass().getName(),taanwei.toString());
        //根据字段查询是否有相同数据
        Wrapper<TaanweiEntity> queryWrapper = new EntityWrapper<TaanweiEntity>()
            .notIn("id",taanwei.getId())
            .andNew()
            .eq("taanwei_name", taanwei.getTaanweiName())
            .eq("taanwei_types", taanwei.getTaanweiTypes())
            .eq("zhuangtai_types", taanwei.getZhuangtaiTypes())
            .eq("quyu_types", taanwei.getQuyuTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TaanweiEntity taanweiEntity = taanweiService.selectOne(queryWrapper);
        if(taanweiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      taanwei.set
            //  }
            taanweiService.updateById(taanwei);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/shenqing")
    public R shenqing(Integer ids, HttpServletRequest request){
        TaanweiEntity taanwei = taanweiService.selectById(ids);
        if(taanwei == null){
            return R.error();
        }
        ShenqingEntity shenqing = new ShenqingEntity();
        shenqing.setCreateTime(new Date());
        shenqing.setInsertTime(new Date());
        shenqing.setYonghuId((Integer)request.getSession().getAttribute("userId"));
        shenqing.setTaanweiId(taanwei.getId());
        shenqing.setShenheTypes(1);
        shenqing.setShenqingTypes(1);
        Wrapper<ShenqingEntity> queryWrapper = new EntityWrapper<ShenqingEntity>()
                .eq("yonghu_id", shenqing.getYonghuId())
                .eq("taanwei_id", shenqing.getTaanweiId())
                .eq("shenhe_types", shenqing.getShenheTypes())
                ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenqingEntity shenqingEntity = shenqingService.selectOne(queryWrapper);
        if(shenqingEntity!=null){
            return R.error("不能重复申请");
        }
        shenqingService.insert(shenqing);
        return R.ok();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        taanweiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



    /**
    * 前端列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = taanweiService.queryPage(params);

        //字典表数据转换
        List<TaanweiView> list =(List<TaanweiView>)page.getList();
        for(TaanweiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TaanweiEntity taanwei = taanweiService.selectById(id);
            if(taanwei !=null){
                //entity转view
        TaanweiView view = new TaanweiView();
                BeanUtils.copyProperties( taanwei , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody TaanweiEntity taanwei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,taanwei:{}",this.getClass().getName(),taanwei.toString());
        Wrapper<TaanweiEntity> queryWrapper = new EntityWrapper<TaanweiEntity>()
            .eq("taanwei_name", taanwei.getTaanweiName())
            .eq("taanwei_types", taanwei.getTaanweiTypes())
            .eq("zhuangtai_types", taanwei.getZhuangtaiTypes())
            .eq("quyu_types", taanwei.getQuyuTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    TaanweiEntity taanweiEntity = taanweiService.selectOne(queryWrapper);
        if(taanweiEntity==null){
            taanwei.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      taanwei.set
        //  }
        taanweiService.insert(taanwei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }





}

