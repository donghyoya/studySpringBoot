package jpabook.jpashop.controller;

import jpabook.jpashop.domian.Item;
import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.Order;
import jpabook.jpashop.domian.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);
        return "order/orderForm";
    }

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){
        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders",orders);
        return "order/orderList";
    }

    @RequestMapping(value = "/orders/{orderId}/cancel", method = RequestMethod.POST)
    public String cancleOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
