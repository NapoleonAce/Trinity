package edu.zucc.gats.trinity.service;


import edu.zucc.gats.trinity.bean.LoginTicket;
import edu.zucc.gats.trinity.mapper.LoginTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginTicketService {

    @Autowired
    public LoginTicketMapper loginTicketMapper;

    public int addTicket(LoginTicket loginTicket){
        return loginTicketMapper.addTicket(loginTicket);
    }

    public int setTicketDisabledByTicket(String ticket){
        return loginTicketMapper.setTicketDisabledByTicket(ticket);
    }

    public int deleteTicket(LoginTicket loginTicket){
        return loginTicketMapper.deleteTicket(loginTicket)
    }
    public LoginTicket loadTicketByManName(String manName){
        return loginTicketMapper.loadTicketByManName(manName);
    }

    public LoginTicket loadTicketById(int id){
        return loginTicketMapper.loadTicketById(id);
    }

    //ticket是否可用
    public int getTicketStatus(LoginTicket loginTicket){
        return loginTicketMapper.getTicketStatus(loginTicket);
    }

    //根据新的名字,生成ticket
//    public String creatNewToken(){
//
//    }

}
