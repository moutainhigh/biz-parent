package cn.waynechu.app.web.controller;

import cn.waynechu.common.annotation.ControllerLogAspectAnnotation;
import cn.waynechu.app.core.service.HouseService;
import cn.waynechu.app.dal.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuwei
 * @date 2018/11/14 16:35
 */
@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping("/{id}")
    @ControllerLogAspectAnnotation(description = "获取房屋信息")
    public House getById(@PathVariable Long id, @RequestParam("city") String city) {
        return houseService.getById(id);
    }
}