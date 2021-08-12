package com.example.server.controller;


import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UniversalRequest;
import com.example.server.service.ICharacterService;
import com.example.server.service.IUserService;
import com.example.server.utils.UserContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <功能描述>
 *
 * @author
 * @date 2021/8/12 15:41
 */
@Api("文字识别")
@RestController
@RequestMapping("/character")
public class CharacterController {

    private  final ICharacterService characterService;
    private  final IUserService userService;

    @Autowired
    public CharacterController(ICharacterService characterService, IUserService userService) {
        this.characterService = characterService;
        this.userService = userService;
    }

    @ApiOperation(value = "通用文字识别")
    @PostMapping("/normal/classify")
    private CharacterResult identifyNormalCharacter(@RequestParam("file") MultipartFile file, UniversalRequest universalRequest) throws Exception {
//        Integer code = userService.verifyTimes(UserContextUtils.getToken()).getCode();
//        if (code == 401) {
//            return JsonResult.error("账号余额不足");
//        } else if (code == 402) {
//            return JsonResult.error("token无效，请重新登录");
//        }

        if (null == file) {
            return CharacterResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyNormalCharacter(image, universalRequest);
    }

    @ApiOperation(value = "车牌识别")
    @PostMapping("/license_plate/classify")
    private CharacterResult identifyLicensePlate(@RequestParam("file") MultipartFile file, String multi_detect) throws Exception {
//        Integer code = userService.verifyTimes(UserContextUtils.getToken()).getCode();
//        if (code == 401) {
//            return JsonResult.error("账号余额不足");
//        } else if (code == 402) {
//            return JsonResult.error("token无效，请重新登录");
//        }

        if (null == file) {
            return CharacterResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyLicensePlate(image, multi_detect);
    }

    @ApiOperation(value = "二维码识别")
    @PostMapping("/qr/classify")
    private CharacterResult identifyQRCode(@RequestParam("file") MultipartFile file) throws Exception {
//        Integer code = userService.verifyTimes(UserContextUtils.getToken()).getCode();
//        if (code == 401) {
//            return JsonResult.error("账号余额不足");
//        } else if (code == 402) {
//            return JsonResult.error("token无效，请重新登录");
//        }

        if (null == file) {
            return CharacterResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyQRCode(image);
    }

    @ApiOperation(value = "手写文字识别")
    @PostMapping("/handwritten/classify")
    private CharacterResult identifyHandwritten(@RequestParam("file") MultipartFile file, UniversalRequest universalRequest) throws Exception {
//        Integer code = userService.verifyTimes(UserContextUtils.getToken()).getCode();
//        if (code == 401) {
//            return JsonResult.error("账号余额不足");
//        } else if (code == 402) {
//            return JsonResult.error("token无效，请重新登录");
//        }

        if (null == file) {
            return CharacterResult.error("文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyHandwritten(image, universalRequest);
    }
}
