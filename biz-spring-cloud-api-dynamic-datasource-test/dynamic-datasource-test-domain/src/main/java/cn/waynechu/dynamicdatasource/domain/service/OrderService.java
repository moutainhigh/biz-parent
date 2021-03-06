package cn.waynechu.dynamicdatasource.domain.service;

import cn.waynechu.facade.common.enums.BizErrorCodeEnum;
import cn.waynechu.facade.common.exception.BizException;
import cn.waynechu.springcloud.common.util.BeanUtil;
import cn.waynechu.dynamicdatasource.dal.dataobject.order.OrderDO;
import cn.waynechu.dynamicdatasource.dal.dataobject.product.ProductDO;
import cn.waynechu.dynamicdatasource.domain.convert.OrderConvert;
import cn.waynechu.dynamicdatasource.domain.repository.OrderRepository;
import cn.waynechu.dynamicdatasource.domain.repository.ProductRepository;
import cn.waynechu.dynamicdatasource.facade.response.OrderDetailResponse;
import cn.waynechu.dynamicdatasource.facade.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuwei
 * @date 2019/9/19 18:13
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * 根据订单id获取订单信息
     *
     * @param orderId 订单id
     * @return 订单信息
     */
    public OrderResponse getById(Long orderId) {
        OrderDO order = orderRepository.getById(orderId);
        return OrderConvert.toOrderResponse(order);
    }

    /**
     * 根据订单id获取订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    public OrderDetailResponse getDetailById(Long orderId) {
        // 状态判断、时效性要求高的查询优先走主库，防止主从同步延迟导致读取脏数据
        OrderDO order = orderRepository.getByIdFromMaster(orderId);
        if (order == null) {
            throw new BizException(BizErrorCodeEnum.DATA_NOT_EXIST);
        }

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        BeanUtil.copyProperties(order, orderDetailResponse);

        ProductDO product = productRepository.getById(order.getProductId());
        if (product != null) {
            BeanUtil.copyProperties(product, orderDetailResponse);
        }
        return orderDetailResponse;
    }

    public List<OrderResponse> listAll() {
        List<OrderDO> orderDOList = orderRepository.listAll();
        return BeanUtil.beanListTransfer(orderDOList, OrderResponse.class);
    }
}
