package com.example.server.controller;


import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.CharacterResult;
import com.example.server.model.vo.JsonResult;
import com.example.server.model.vo.UniversalRequest;
import com.example.server.service.ICharacterService;
import com.example.server.service.IUserService;
import com.example.server.utils.StringUtils;
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

    private final ICharacterService characterService;

    @Autowired
    public CharacterController(ICharacterService characterService) {
        this.characterService = characterService;
    }

//    @ApiOperation(value = "通用文字识别")
//    @PostMapping("/normal/classify")
//    private JsonResult<CharacterResult> identifyNormalCharacter(@RequestParam("file") MultipartFile file, UniversalRequest universalRequest) throws Exception {
//        if (StringUtils.isBlank(file.getOriginalFilename())) {
//            throw new BizBaseException(401, "文件不能为空");
//        }
//
//        byte[] image = file.getBytes();
//        return characterService.classifyNormalCharacter(image, universalRequest);
//    }

    @ApiOperation(value = "车牌识别")
    @PostMapping("/license_plate/classify")
    private JsonResult<CharacterResult> identifyLicensePlate(@RequestParam("file") MultipartFile file, UniversalRequest universalRequest) throws Exception {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyLicensePlate(image, universalRequest.getMulti_detect());
    }

    @ApiOperation(value = "二维码识别")
    @PostMapping("/qr/classify")
    private JsonResult<CharacterResult> identifyQRCode(@RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyQRCode(image);
    }

    @ApiOperation(value = "手写文字识别")
    @PostMapping("/handwritten/classify")
    private JsonResult<CharacterResult> identifyHandwritten(@RequestParam("file") MultipartFile file, UniversalRequest universalRequest) throws Exception {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            throw new BizBaseException(401, "文件不能为空");
        }

        byte[] image = file.getBytes();
        return characterService.classifyHandwritten(image, universalRequest);
    }
}
