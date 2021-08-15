package com.toyproject.lol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import com.toyproject.lol.service.ChampionLotationService;

@RestController
public class ChampionLotationController {

    @Autowired
    private ChampionLotationService championLotaionService;

    private static final String LOL_ENDPOINT = "https://kr.api.riotgames.com/lol/platform";

    @RequestMapping(value = "/champion/rotaion",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getApiTestGet(final HttpServletRequest Request) throws Exception {
        String urlPath = LOL_ENDPOINT + "/v3/champion-rotations";
        championLotaionService.getChampionRotationsObject(LOL_ENDPOINT+LOL_ENDPOINT);
        return "{\"result\":\"get\"}";
    }

}
