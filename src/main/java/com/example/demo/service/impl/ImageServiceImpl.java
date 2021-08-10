package com.example.demo.service.impl;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.example.demo.model.vo.JsonResult;
import com.example.demo.model.vo.ResultVO;
import com.example.demo.service.IImageService;
import com.example.demo.utils.AIUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <功能描述>
 *
 * @author 20022436
 * @date 2021/8/10 16:02
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Override
    public JsonResult<ResultVO> classifyGeneralImage(byte[] image) {
        AipImageClassify client = AIUtils.getImageClient();
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.advancedGeneral(image, options);
        Object object = res.getJSONArray("result").toList().get(0);

        ResultVO resultVO = new ResultVO();
        Map entry = (Map)object;
        resultVO.setRoot((String) entry.get("root"));
        resultVO.setScore((Double) entry.get("score"));
        resultVO.setType((String) entry.get("keyword"));
        return JsonResult.success(resultVO);
    }
}
