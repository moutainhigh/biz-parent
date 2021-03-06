package cn.waynechu.dynamicdatasource.api.controller;

import cn.waynechu.facade.common.response.BizResponse;
import cn.waynechu.dynamicdatasource.domain.service.OrderService;
import cn.waynechu.dynamicdatasource.facade.response.OrderDetailResponse;
import cn.waynechu.dynamicdatasource.facade.response.OrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuwei
 * @date 2019/9/19 17:48
 */
@RestController
@Api(tags = "订单")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "获取订单列表", notes = "查询order从库")
    @GetMapping
    public BizResponse<List<OrderResponse>> listAll() {
        List<OrderResponse> orderResponses= orderService.listAll();
        return BizResponse.success(orderResponses);
    }

    @ApiOperation(value = "根据订单id获取订单信息", notes = "查询order从库")
    @GetMapping("/{orderId}")
    public BizResponse<OrderResponse> getById(@PathVariable Long orderId) {
        OrderResponse orderResponse = orderService.getById(orderId);
        return BizResponse.success(orderResponse);
    }

    @ApiOperation(value = "根据订单id获取订单详情", notes = "查询order主库和product从库")
    @GetMapping("/{orderId}/detail")
    public BizResponse<OrderDetailResponse> getDetailById(@PathVariable Long orderId) {
        OrderDetailResponse orderdetailResponse = orderService.getDetailById(orderId);
        return BizResponse.success(orderdetailResponse);
    }
}
