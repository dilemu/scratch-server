package com.example.server.service.impl;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.imageprocess.AipImageProcess;
import com.example.server.model.vo.FeatureVO;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.NormalImageVO;
import com.example.server.service.IImageService;
import com.example.server.utils.AIUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/10 16:02
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Override
    public JsonResult<NormalImageVO> classifyGeneralImage(byte[] image) {
        AipImageClassify client = AIUtils.getImageClient();
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.advancedGeneral(image, options);
        Object object = res.getJSONArray("result").toList().get(0);

        NormalImageVO resultVO = new NormalImageVO();
        Map entry = (Map) object;
        resultVO.setRoot((String) entry.get("root"));
        resultVO.setScore((Double) entry.get("score"));
        resultVO.setType((String) entry.get("keyword"));
        return JsonResult.success(resultVO);
    }

    @Override
    public JsonResult<FeatureVO> convertStyle(byte[] image, String style) {
        AipImageProcess client = AIUtils.getImageProcessClient();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("option", style);
        JSONObject res = client.styleTrans(image, options);

        FeatureVO resultVO = new FeatureVO();
        if (res.has("image"))
            resultVO.setImage((String) res.get("image"));
        resultVO.setLog_id((long) res.get("log_id"));
        return JsonResult.success(resultVO);
    }
}
