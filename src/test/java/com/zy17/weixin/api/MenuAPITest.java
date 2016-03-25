package com.zy17.weixin.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zy17.weixin.bean.BaseResult;
import com.zy17.weixin.bean.menu.Button;
import com.zy17.weixin.bean.menu.Matchrule;
import com.zy17.weixin.bean.menu.MenuButtons;
import com.zy17.weixin.bean.menu.selfmenu.CurrentSelfmenuInfo;
import com.zy17.weixin.bean.token.Token;

/**
 * Created by zy17 on 2016/3/15.
 */
public class MenuAPITest {

    @Test
    public void testMenuGet() throws Exception {
        Token token = TokenAPI.token
                ("", "");
        System.out.println(JSON.toJSON(token));
//        CurrentSelfmenuInfo current_selfmenu_info = MenuAPI.get_current_selfmenu_info(token.getAccess_token());
//        System.out.println(JSON.toJSONString(current_selfmenu_info));
        MenuButtons menuButtons = new MenuButtons();
        menuButtons.setMenuid("1");
        //        Matchrule matchrule = new Matchrule();
        //        menuButtons.setMatchrule(matchrule);
        List<Button> buttons = new ArrayList<>();
        Button button = new Button();
        button.setKey("key");
        button.setName("菜单");
        button.setType(Button.CLICK);
        buttons.add(button);

        menuButtons.setButton(buttons.toArray(new Button[0]));
        System.out.println(JSON.toJSONString(menuButtons));
        //        BaseResult baseResult = MenuAPI.menuCreate(token.getAccess_token(), menuButtons);
        //        System.out.println(JSON.toJSONString(baseResult));
    }
}