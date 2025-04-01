package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.WodeEntity;
import com.service.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.ShenqingEntity;

import com.entity.view.ShenqingView;
import com.entity.TaanweiEntity;
import com.entity.YonghuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 摊位申请
 * 后端接口
 * @author
 * @email
 * @date 2021-05-06
*/
@RestController
@Controller
@RequestMapping("/shenqing")
public class ShenqingController {
    private static final Logger logger = LoggerFactory.getLogger(ShenqingController.class);

    @Autowired
    private ShenqingService shenqingService;


    @Autowired
    private WodeService wodeService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private TaanweiService taanweiService;
    @Autowired
    private YonghuService yonghuService;


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
        PageUtils page = shenqingService.queryPage(params);

        //字典表数据转换
        List<ShenqingView> list =(List<ShenqingView>)page.getList();
        for(ShenqingView c:list){
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
        ShenqingEntity shenqing = shenqingService.selectById(id);
        if(shenqing !=null){
            //entity转view
            ShenqingView view = new ShenqingView();
            BeanUtils.copyProperties( shenqing , view );//把实体数据重构到view中

            //级联表
            TaanweiEntity taanwei = taanweiService.selectById(shenqing.getTaanweiId());
            if(taanwei != null){
                BeanUtils.copyProperties( taanwei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setTaanweiId(taanwei.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shenqing.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody ShenqingEntity shenqing, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shenqing:{}",this.getClass().getName(),shenqing.toString());
        Wrapper<ShenqingEntity> queryWrapper = new EntityWrapper<ShenqingEntity>()
            .eq("yonghu_id", shenqing.getYonghuId())
            .eq("taanwei_id", shenqing.getTaanweiId())
            .eq("shenhe_types", shenqing.getShenheTypes())
            .eq("shenqing_types", shenqing.getShenqingTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenqingEntity shenqingEntity = shenqingService.selectOne(queryWrapper);
        if(shenqingEntity==null){
            shenqing.setInsertTime(new Date());
            shenqing.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      shenqing.set
        //  }
            shenqingService.insert(shenqing);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShenqingEntity shenqing, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shenqing:{}",this.getClass().getName(),shenqing.toString());
        //根据字段查询是否有相同数据
        Wrapper<ShenqingEntity> queryWrapper = new EntityWrapper<ShenqingEntity>()
            .notIn("id",shenqing.getId())
            .andNew()
            .eq("yonghu_id", shenqing.getYonghuId())
            .eq("taanwei_id", shenqing.getTaanweiId())
            .eq("shenhe_types", shenqing.getShenheTypes())
            .eq("shenqing_types", shenqing.getShenqingTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenqingEntity shenqingEntity = shenqingService.selectOne(queryWrapper);
        if(shenqingEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      shenqing.set
            //  }
            shenqingService.updateById(shenqing);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/shenhe")
    public R shenhe(Integer ids, Integer shenhe){
        ShenqingEntity shenqing = shenqingService.selectById(ids);
        if(shenhe == null){
            return R.error();
        }
        if(shenhe == null){
            return R.error("审核结果为空");
        }
        shenqing.setShenheTypes(shenhe);
        boolean b = shenqingService.updateById(shenqing);
        if(b){
            if(shenhe == 2){
                if(shenqing.getShenqingTypes() == 1){
                    WodeEntity wode = new WodeEntity();
                    wode.setCreateTime(new Date());
                    wode.setTaanweiId(shenqing.getTaanweiId());
                    wode.setYonghuId(shenqing.getYonghuId());
                    wode.setCreateTime(new Date());
                    boolean insert = wodeService.insert(wode);
                    if(!insert){
                        return R.error();
                    }
                    TaanweiEntity taanwei = taanweiService.selectById(shenqing.getTaanweiId());
                    taanwei.setZhuangtaiTypes(1);
                    if(!taanweiService.updateById(taanwei)){
                        return R.error();
                    }
                }else{
                    TaanweiEntity tw = taanweiService.selectById(shenqing.getTaanweiId());
                    if(tw.getTaanweiTypes() == 2){
                        return R.error("已经是固定摊位了");
                    }
                    tw.setTaanweiTypes(2);
                    if(!taanweiService.updateById(tw)){
                        return R.error();
                    }
                }
            }
            return R.ok();
        }
        return R.error();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shenqingService.deleteBatchIds(Arrays.asList(ids));
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
        PageUtils page = shenqingService.queryPage(params);

        //字典表数据转换
        List<ShenqingView> list =(List<ShenqingView>)page.getList();
        for(ShenqingView c:list){
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
        ShenqingEntity shenqing = shenqingService.selectById(id);
            if(shenqing !=null){
                //entity转view
        ShenqingView view = new ShenqingView();
                BeanUtils.copyProperties( shenqing , view );//把实体数据重构到view中

                //级联表
                    TaanweiEntity taanwei = taanweiService.selectById(shenqing.getTaanweiId());
                if(taanwei != null){
                    BeanUtils.copyProperties( taanwei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTaanweiId(taanwei.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shenqing.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody ShenqingEntity shenqing, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shenqing:{}",this.getClass().getName(),shenqing.toString());
        Wrapper<ShenqingEntity> queryWrapper = new EntityWrapper<ShenqingEntity>()
            .eq("yonghu_id", shenqing.getYonghuId())
            .eq("taanwei_id", shenqing.getTaanweiId())
            .eq("shenhe_types", shenqing.getShenheTypes())
            .eq("shenqing_types", shenqing.getShenqingTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    ShenqingEntity shenqingEntity = shenqingService.selectOne(queryWrapper);
        if(shenqingEntity==null){
            shenqing.setInsertTime(new Date());
            shenqing.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      shenqing.set
        //  }
        shenqingService.insert(shenqing);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }





}

