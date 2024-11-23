package com.controller.admin;

import com.entity.Keep;
import com.dto.Constants;
import com.dto.ResponseDto;
import com.github.pagehelper.PageHelper;
import com.service.KeepService;
import com.dto.PageDto;
import com.dto.KeepDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/keep/")
public class KeepController {
    @Resource
    private KeepService keepService;

    public static final String TABLE_NAME = "收藏";

    /**
     * ====后台收藏管理====
     *
     * @param keep
     * @return
     */

    /*保存*/
    @RequestMapping("save")
    public String save(@RequestBody Keep keep) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("id=="+keep.getId());

        if (keep.getId()==null){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        keepService.save(keep);
        }else {
        keepService.updateById(keep);
        }
        return "success";
    }

    /*分页列表*/
    @RequestMapping("list")
   public KeepDto list(KeepDto keepDto) {
        Map<String, Object> bMap = new HashMap<String, Object>();
        QueryWrapper<Keep> wrapper = new QueryWrapper<>();
        if (keepDto.getUid() != null && !keepDto.getUid().equals("")) {
            wrapper.like("uid",keepDto.getUid());
        }
        if (keepDto.getCid() != null && !keepDto.getCid().equals("")) {
            wrapper.like("cid",keepDto.getCid());
        }
        if (keepDto.getCtype() != null && !keepDto.getCtype().equals("")) {
            wrapper.like("ctype",keepDto.getCtype());
        }
        wrapper.orderByDesc("id");
        List<Keep> list = keepService.list(wrapper);
        keepDto.setTotal(list.size());
		PageHelper.startPage(keepDto.getPage(),keepDto.getSize());
		list = keepService.list(wrapper);
        keepDto.setList(list);
		return keepDto;
    }

    /*收藏搜索列表*/
	@RequestMapping("search")
	public KeepDto search(KeepDto keepDto) {
		Map<String, Object> bMap = new HashMap<String, Object>();
        QueryWrapper<Keep> wrapper = new QueryWrapper<>();
        if (keepDto.getUid() != null && !keepDto.getUid().equals("")) {
            wrapper.like("uid",keepDto.getUid());
        }
        if (keepDto.getCid() != null && !keepDto.getCid().equals("")) {
            wrapper.like("cid",keepDto.getCid());
        }
        if (keepDto.getCtype() != null && !keepDto.getCtype().equals("")) {
            wrapper.like("ctype",keepDto.getCtype());
        }
        wrapper.orderByDesc("id");
        List<Keep> list = keepService.list(wrapper);
        keepDto.setTotal(list.size());
		PageHelper.startPage(keepDto.getPage(),keepDto.getSize());
		list = keepService.list(wrapper);
        keepDto.setList(list);
		return keepDto;
	}

    /*删除*/
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        keepService.removeById(id);
        return "success";
    }

    /*获取单个对象*/
    @RequestMapping("getById")
    public Keep getById(@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Keep keep1 = keepService.getById(id);
        return keep1;
    }

    /*获取所有*/
    @RequestMapping("all")
    public List<Keep> all() {
        Map<String, Object> bMap = new HashMap<String, Object>();
        QueryWrapper<Keep> wrapper = new QueryWrapper<>();
        wrapper.allEq(bMap);
        List<Keep> list = keepService.list(wrapper);
        return list;
    }

}
