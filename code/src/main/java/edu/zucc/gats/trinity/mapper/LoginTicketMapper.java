package edu.zucc.gats.trinity.mapper;

import edu.zucc.gats.trinity.bean.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginTicketMapper {

    int addTicket(LoginTicket loginTicket);

    int setTicketDisabledByTicket(String ticket);

    int deleteTicket(LoginTicket loginTicket);

    LoginTicket loadTicketByManName(String manName);

    LoginTicket loadTicketById(int id);

    //ticket是否可用
    int getTicketStatus(LoginTicket loginTicket);

}
