package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.ShenqingEntity;
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

import com.entity.WodeEntity;

import com.entity.view.WodeView;
import com.entity.TaanweiEntity;
import com.entity.YonghuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 我的摊位
 * 后端接口
 * @author
 * @email
 * @date 2021-05-06
*/
@RestController
@Controller
@RequestMapping("/wode")
public class WodeController {
    private static final Logger logger = LoggerFactory.getLogger(WodeController.class);

    @Autowired
    private WodeService wodeService;

    @Autowired
    private ShenqingService shenqingService;


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
        PageUtils page = wodeService.queryPage(params);

        //字典表数据转换
        List<WodeView> list =(List<WodeView>)page.getList();
        for(WodeView c:list){
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
        WodeEntity wode = wodeService.selectById(id);
        if(wode !=null){
            //entity转view
            WodeView view = new WodeView();
            BeanUtils.copyProperties( wode , view );//把实体数据重构到view中

            //级联表
            TaanweiEntity taanwei = taanweiService.selectById(wode.getTaanweiId());
            if(taanwei != null){
                BeanUtils.copyProperties( taanwei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setTaanweiId(taanwei.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(wode.getYonghuId());
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
    public R save(@RequestBody WodeEntity wode, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wode:{}",this.getClass().getName(),wode.toString());
        Wrapper<WodeEntity> queryWrapper = new EntityWrapper<WodeEntity>()
            .eq("yonghu_id", wode.getYonghuId())
            .eq("taanwei_id", wode.getTaanweiId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WodeEntity wodeEntity = wodeService.selectOne(queryWrapper);
        if(wodeEntity==null){
            wode.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      wode.set
        //  }
            wodeService.insert(wode);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WodeEntity wode, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,wode:{}",this.getClass().getName(),wode.toString());
        //根据字段查询是否有相同数据
        Wrapper<WodeEntity> queryWrapper = new EntityWrapper<WodeEntity>()
            .notIn("id",wode.getId())
            .andNew()
            .eq("yonghu_id", wode.getYonghuId())
            .eq("taanwei_id", wode.getTaanweiId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WodeEntity wodeEntity = wodeService.selectOne(queryWrapper);
        if(wodeEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      wode.set
            //  }
            wodeService.updateById(wode);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WodeEntity> wode = wodeService.selectBatchIds(Arrays.asList(ids));
        if(wode.size()<=0){
            return R.error();
        }
        for (WodeEntity wd:wode) {
            TaanweiEntity tw = taanweiService.selectById(wd.getTaanweiId());
            tw.setZhuangtaiTypes(2);
            boolean b = taanweiService.updateById(tw);
            if(!b){
                return R.error();
            }
        }
        wodeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

//    /**
//    * 删除
//    */
//    @RequestMapping("/shengji")
//    public R shengji(Integer ids){
//        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
//        WodeEntity wode = wodeService.selectById(ids);
//        if(wode == null){
//            return R.error();
//        }
//        TaanweiEntity tw = taanweiService.selectById(wode.getTaanweiId());
//        if(tw.getTaanweiTypes() == 2){
//            return R.error("已经是固定摊位了");
//        }
//        tw.setTaanweiTypes(2);
//        boolean b = taanweiService.updateById(tw);
//        if(b){
//            return R.ok();
//        }
//        return R.error();
//    }


    /**
     * 删除
     */
    @RequestMapping("/shengji")
    public R shengji(Integer ids, HttpServletRequest request){
        WodeEntity wode = wodeService.selectById(ids);
        if(wode == null){
            return R.error();
        }
        ShenqingEntity shenqing = new ShenqingEntity();
        shenqing.setCreateTime(new Date());
        shenqing.setInsertTime(new Date());
        shenqing.setYonghuId((Integer)request.getSession().getAttribute("userId"));
        shenqing.setShenheTypes(1);
        shenqing.setShenqingTypes(2);
        shenqing.setTaanweiId(wode.getTaanweiId());
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
        PageUtils page = wodeService.queryPage(params);

        //字典表数据转换
        List<WodeView> list =(List<WodeView>)page.getList();
        for(WodeView c:list){
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
        WodeEntity wode = wodeService.selectById(id);
            if(wode !=null){
                //entity转view
        WodeView view = new WodeView();
                BeanUtils.copyProperties( wode , view );//把实体数据重构到view中

                //级联表
                    TaanweiEntity taanwei = taanweiService.selectById(wode.getTaanweiId());
                if(taanwei != null){
                    BeanUtils.copyProperties( taanwei , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTaanweiId(taanwei.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(wode.getYonghuId());
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
    public R add(@RequestBody WodeEntity wode, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,wode:{}",this.getClass().getName(),wode.toString());
        Wrapper<WodeEntity> queryWrapper = new EntityWrapper<WodeEntity>()
            .eq("yonghu_id", wode.getYonghuId())
            .eq("taanwei_id", wode.getTaanweiId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    WodeEntity wodeEntity = wodeService.selectOne(queryWrapper);
        if(wodeEntity==null){
            wode.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      wode.set
        //  }
        wodeService.insert(wode);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }





}

